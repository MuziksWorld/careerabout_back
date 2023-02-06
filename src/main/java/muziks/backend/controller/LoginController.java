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
            log.info("jwtToken= {}", jwtTokenProvider.createToken(id, user.getRole()));
            request.setAttribute("jwtToken", jwtTokenProvider.createToken(id, user.getRole()));
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

    // TODO
    // 로그아웃 요청이 왔을 때 어떻게 헤더에서 토큰 값을 꺼내고 검증하는지?
    @PostMapping("/logout")
    public HttpServletRequest logout(HttpServletRequest request) {
        return request;
    }
}