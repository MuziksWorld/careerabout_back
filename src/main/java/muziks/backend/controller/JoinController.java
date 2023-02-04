package muziks.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import muziks.backend.domain.form.LoginForm;
import muziks.backend.domain.form.SignDto;
import muziks.backend.service.UserService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class JoinController {

    private final UserService userService;

    @PostMapping("/sign")
    public String sign(@RequestBody @Valid SignDto signDto,
                       BindingResult result) {

        String id = signDto.getId();
        String password = signDto.getPassword();
        validateId(result, id);
        validatePasswordPattern(result, password);

        if (result.hasErrors()) {
            log.info("errors= {}", result);
        }
        if (!result.hasErrors()) {
            userService.sign(signDto);
            return "회원가입 완료";
        }
        return result.getFieldError().toString();
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginForm loginForm,
                        BindingResult result) {
        String id = loginForm.getId();
        String password = loginForm.getPassword();
        validateLoginId(result, id);
        validateLoginPassword(loginForm, result, password);

        if (!result.hasErrors()) {
            return "로그인 완료";
        }
        return result.getFieldError().toString();
    }

    private void validateLoginPassword(LoginForm loginForm, BindingResult result, String password) {
        if (!userService.isMatches(loginForm.getId(), password)) {
            result.addError(new FieldError("loginForm", "password", "비밀번호가 올바르지 않습니다."));
        }
    }

    private void validatePasswordPattern(BindingResult result, String password) {
        if (!userService.isValidPassword(password)) {
            result.addError(new FieldError("signDto", "password", "알바벳 대문자, 소문자, 숫자, 그리고 하나 이상의 특수문자를 포함해주세요."));
        }
    }

    private void validateLoginId(BindingResult result, String id) {
        if (userService.findById(id).size() == 0) {
            result.addError(new FieldError("loginForm", "id", "아이디가 존재하지 않습니다."));
        }
    }

    private void validateId(BindingResult result, String id) {
        if (userService.findById(id).size() >= 1) {
            result.addError(new FieldError("signForm", "id", "중복된 아이디가 존재합니다."));
        }
    }
}
