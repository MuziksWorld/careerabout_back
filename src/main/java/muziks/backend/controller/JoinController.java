package muziks.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import muziks.backend.domain.dto.signdtos.OverlapVO;
import muziks.backend.domain.dto.signdtos.SignDto;
import muziks.backend.domain.dto.signdtos.SignErrorDto;
import muziks.backend.domain.dto.signdtos.SignErrorDtos;
import muziks.backend.domain.entity.User;
import muziks.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class JoinController {

    private final UserService userService;

    @PostMapping("/sign")
    public ResponseEntity<Object> sign(@RequestBody @Valid SignDto signDto,
                                       BindingResult bindingResult) {
        validateId(bindingResult, signDto.getUserId());
        validatePasswordPattern(bindingResult, signDto.getPassword());

        if (bindingResult.hasErrors()) {
//            return getErrors(bindingResult);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(signDto);
        }
        userService.save(signDto);
        userService.createAndSaveToken(signDto.getUserId());
        return ResponseEntity.status(HttpStatus.OK)
                .body(true);
    }

    @PostMapping("/idCheck")
    public ResponseEntity<Object> isOverlappedUser(@RequestBody OverlapVO overlapVO) {
//        HashMap<String, String> result = new HashMap<>();
        if (userService.findById(overlapVO.getUser_id()).size() > 0) {
//            result.put("data", "중복된 아이디가 존재합니다.");
            return ResponseEntity.ok()
                    .body(false);
        }
//        result.put("data", "사용 가능한 아이디입니다.");
        return ResponseEntity.ok()
                .body(true);
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
            FieldError fieldError = new FieldError("signDto", "password", "알바벳 대문자, 소문자, 숫자, 그리고 하나 이상의 특수문자를 포함해주세요.");
            result.addError(fieldError);
            log.info("message={}", fieldError.getDefaultMessage());
        }
    }

    private void validateId(BindingResult result, String id) {
        List<User> findUser = userService.findById(id);
        if (userService.findById(id).size() >= 1) {
            FieldError fieldError = new FieldError("signForm", "id", "중복된 아이디가 존재합니다.");
            result.addError(fieldError);
            log.info("message={}", fieldError.getDefaultMessage());
        }
    }
}
