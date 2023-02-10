package muziks.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import muziks.backend.domain.entity.User;
import muziks.backend.domain.logindtos.LoginDto;
import muziks.backend.jwt.JwtTokenProvider;
import muziks.backend.jwt.Token;
import muziks.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDto loginDto,
                                HttpServletRequest request,
                                BindingResult result) {
        String id = loginDto.getId();
        String password = loginDto.getPassword();
        validateLoginId(result, id);
        validateLoginPassword(loginDto, result, password);
        User user = userService.findById(loginDto.getId()).get(0);

        if (result.hasErrors()) {
            log.info("result= {}", result.getFieldError().toString());
            List<>

            return "로그인 실패";
        }

        Token token = jwtTokenProvider.createToken(id, user.getRole());
        request.setAttribute("jwtToken", token);
        log.info("jwtToken= {}", token);
        user.setAuthorization(token.getRefreshToken());
        userService.save(user);
        return "로그인 완료";
    }

    private void validateLoginPassword(LoginDto loginDto, BindingResult result, String password) {
        if (!userService.isMatches(loginDto.getId(), password)) {
            result.addError(new FieldError("loginForm", "password", "비밀번호가 올바르지 않습니다."));
        }
    }

    private void validateLoginId(BindingResult result, String id) {
        if (userService.findById(id).size() == 0) {
            result.addError(new FieldError("loginForm", "id", "아이디가 존재하지 않습니다."));
        }
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request,
                         @ModelAttribute Model model) {
        String findAuthorization = request.getHeader("Authorization");
        log.info("authorization= {} ",findAuthorization);
        String authorization = findAuthorization.substring(7, findAuthorization.length());
        User findUser = userService.findByAuthorization(authorization);
        findUser.setAuthorization(null);
        userService.save(findUser);

        return "로그아웃 완료";
    }
}