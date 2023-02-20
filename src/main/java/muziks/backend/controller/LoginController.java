package muziks.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import muziks.backend.domain.dto.logindtos.LoginDto;
import muziks.backend.domain.dto.logindtos.LoginErrorDto;
import muziks.backend.jwt.JwtTokenProvider;
import muziks.backend.domain.dto.jwtdtos.TokenDto;
import muziks.backend.service.LoginService;
import muziks.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final LoginService loginService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDto loginDto,
                                        BindingResult bindingResult) {
        validateLoginId(loginDto, bindingResult);
        validateLoginPassword(loginDto, bindingResult);

        if (bindingResult.hasErrors()) {
            return getErrors(bindingResult);
        }
        loginService.login(loginDto);
        TokenDto tokenDto = loginService.getTokenDto(loginDto);

        Map<String, Object> result = new HashMap<>();
        result.put("accessToken", tokenDto.getAccessToken());
        result.put("refreshToken", tokenDto.getRefreshToken());
        result.put("userInfo", jwtTokenProvider.getUserPk(tokenDto.getAccessToken(), jwtTokenProvider.getAccessTokenKey()));
        result.put("accessTokenInfo", jwtTokenProvider.getAuthentication(tokenDto.getAccessToken(), jwtTokenProvider.getAccessTokenKey()));

        return ResponseEntity.ok()
                .body(result);
    }

    @PostMapping("/logout")
    public ResponseEntity<Object> logout(@RequestBody String refreshToken) {
        try {
            loginService.logout(refreshToken);
        } catch (IndexOutOfBoundsException e) {
            Map<String, Object> result = new HashMap<>();
            result.put("error", e.getClass());
            result.put("errorMessage", e.getMessage());
            return ResponseEntity.badRequest()
                    .body(result);
        }
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
        if (userService.findByName(loginDto.getId()).size() == 0) {
            result.addError(new FieldError("loginForm", "id", "아이디가 존재하지 않습니다."));
        }
    }
}