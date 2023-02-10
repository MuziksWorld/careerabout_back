package muziks.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import muziks.backend.domain.dto.logindtos.LoginDto;
import muziks.backend.domain.dto.logindtos.LoginErrorDto;
import muziks.backend.jwt.JwtTokenProvider;
import muziks.backend.jwt.Token;
import muziks.backend.service.LoginService;
import muziks.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final LoginService loginService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDto loginDto,
                                        HttpServletRequest request,
                                        BindingResult result) {
        validateLoginId(loginDto, result);
        validateLoginPassword(loginDto, result);

        if (result.hasErrors()) {
            return getErrors(result);
        }
        Token token = loginService.createAndGetToken(loginDto.getId());
        request.setAttribute("token", token);
        log.info("refreshToken= {}", token.getRefreshToken());
        log.info("accessToken= {}", token.getAccessToken());
        return ResponseEntity.ok()
                .body("로그인 완료");
    }

    @PostMapping("/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request) {

        Token token = (Token) request.getAttribute("token");
        log.info("refreshToken= {}", token.getRefreshToken());
        log.info("accessToken= {}", token.getAccessToken());

        String findToken = request.getHeader("Authorization");
        loginService.logout(findToken);
        return ResponseEntity.ok()
                .body("로그아웃 완료");
    }

    private ResponseEntity<Object> getErrors(BindingResult result) {
        log.info("result= {}", result.getFieldErrors());
        List<Object> errors = new ArrayList<>();
        result.getFieldErrors()
                .forEach(e -> errors.add(new LoginErrorDto(e.getField(), e.getDefaultMessage())));
        return ResponseEntity.badRequest()
                .body(errors);
    }

    private void validateLoginPassword(LoginDto loginDto, BindingResult result) {
        if (!userService.isMatches(loginDto.getId(), loginDto.getPassword())) {
            result.addError(new FieldError("loginForm", "password", "비밀번호가 올바르지 않습니다."));
        }
    }

    private void validateLoginId(LoginDto loginDto, BindingResult result) {
        if (userService.findById(loginDto.getId()).size() == 0) {
            result.addError(new FieldError("loginForm", "id", "아이디가 존재하지 않습니다."));
        }
    }
}