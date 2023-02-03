package muziks.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import muziks.backend.domain.form.SignForm;
import muziks.backend.domain.utils.PasswordUtils;
import muziks.backend.service.UserService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;

@Slf4j
@RestController
@RequiredArgsConstructor
public class JoinController {

    private final UserService userService;

    @PostMapping("/sign")
    public String sign(@RequestBody SignForm signForm,
                       BindingResult result) {

        String id = signForm.getId();
        String password = signForm.getPassword();
        validateId(result, id);
        validatePassword(result, password);

        if (result.hasErrors()) {
            log.info("errors= {}", result);
        }
        if (!result.hasErrors()) {
            userService.sign(signForm);
            return "회원가입 완료";
        }
        return result.getFieldError().toString();
    }

    private void validatePassword(BindingResult result, String password) {
        if (password.length() < 8 || password.length() >= 16) {
            result.addError(new FieldError("user", "password", "비밀번호는 8자 이상, 16자 이하로 입력해주세요."));
        }
        Pattern passwordPattern = PasswordUtils.passwordPattern;
        boolean isValidPassword = passwordPattern.matcher(password).matches();
        if (!isValidPassword) {
            result.addError(new FieldError("user", "password", "하나 이상의 특수문자를 포함해주세요."));
        }
    }

    private void validateId(BindingResult result, String id) {
        if (userService.findById(id).size() >= 1) {
            result.addError(new FieldError("user", "id", "중복된 아이디가 존재합니다."));
        }
        if (id.length() < 4 || id.length() >= 20) {
            result.addError(new FieldError("user", "id", "아이디는 4자 이상, 20자 이하로 입력해주세요."));
        }
    }
}
