package muziks.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import muziks.backend.domain.dto.signdtos.SignDto;
import muziks.backend.domain.dto.signdtos.SignErrorDtos;
import muziks.backend.domain.dto.signdtos.SignErrorDto;
import muziks.backend.service.UserService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> sign(@RequestBody @Valid SignDto signDto,
                                                    BindingResult bindingResult) {
        validateId(bindingResult, signDto.getId());
        validatePasswordPattern(bindingResult, signDto.getPassword());

        if (bindingResult.hasErrors()) {
            return getErrors(bindingResult);
        }
        userService.sign(signDto);
        return ResponseEntity.ok()
                .body("회원가입 완료");
    }

    private ResponseEntity<Object> getErrors(BindingResult bindingResult) {
        log.info("errors= {}", bindingResult);
        SignErrorDtos signErrorDtos = new SignErrorDtos();
        bindingResult.getFieldErrors()
                .forEach(e -> signErrorDtos.add(new SignErrorDto(e.getField(), e.getDefaultMessage())));
        return ResponseEntity.badRequest()
                .body(signErrorDtos);
    }

    private void validatePasswordPattern(BindingResult result, String password) {
        if (!userService.isValidPassword(password)) {
            result.addError(new FieldError("signDto", "password", "알바벳 대문자, 소문자, 숫자, 그리고 하나 이상의 특수문자를 포함해주세요."));
        }
    }

    private void validateId(BindingResult result, String id) {
        if (userService.findById(id).size() >= 1) {
            result.addError(new FieldError("signForm", "id", "중복된 아이디가 존재합니다."));
        }
    }
}
