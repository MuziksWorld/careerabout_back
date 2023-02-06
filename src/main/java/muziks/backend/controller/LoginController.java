package muziks.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import muziks.backend.domain.entity.User;
import muziks.backend.domain.form.LoginForm;
import muziks.backend.jwt.JwtTokenProvider;
import muziks.backend.service.UserService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public String login(@RequestBody LoginForm loginForm,
                        HttpServletRequest request,
                        BindingResult result) {
        String id = loginForm.getId();
        String password = loginForm.getPassword();
        validateLoginId(result, id);
        validateLoginPassword(loginForm, result, password);

        User user = userService.findById(loginForm.getId()).get(0);
        if (!result.hasErrors()) {
            String token = jwtTokenProvider.createToken(id, user.getRole());
            request.setAttribute("jwtToken", token);
            log.info("jwtToken= {}", token);
            user.setAuthorization(token);
            userService.save(user);
            return "로그인 완료";
        }
        log.info("result= {}", result.getFieldError().toString());
        return "로그인 실패";
    }

    private void validateLoginPassword(LoginForm loginForm, BindingResult result, String password) {
        if (!userService.isMatches(loginForm.getId(), password)) {
            result.addError(new FieldError("loginForm", "password", "비밀번호가 올바르지 않습니다."));
        }
    }

    private void validateLoginId(BindingResult result, String id) {
        if (userService.findById(id).size() == 0) {
            result.addError(new FieldError("loginForm", "id", "아이디가 존재하지 않습니다."));
        }
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        String findAuthorization = request.getHeader("Authorization");
        log.info("authorization= {} ",findAuthorization);
        String authorization = findAuthorization.substring(7, findAuthorization.length());
        User findUser = userService.findByAuthorization(authorization);
        findUser.setAuthorization(null);
        userService.save(findUser);

        return "로그아웃 완료";
    }
}