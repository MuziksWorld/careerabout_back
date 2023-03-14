package muziks.backend.domain.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Pattern;


/**
 * JwtTokenProviderTest에서 비밀번호 올바르게 입력했는데
 * 자꾸 비밀번호 관련해서 오류 발생.
 * 비밀번호 정규식 테스트 해서 통과 되는 경우에 적용할 것
 */
@Slf4j
class PasswordUtilsTest {

    @Autowired
    private PasswordUtils passwordUtils;

    @Test
    void passwordTest_통과케이스() {
        String password = "Test123!!";
        Pattern passwordPattern = passwordUtils.passwordPattern;
        boolean isPasswordMatches = passwordPattern.matcher(password).matches();
        Assertions.assertThat(isPasswordMatches).isTrue();
    }

    @Test
    void passwordTest_매칭되지않을때() {
        String password = "Test123";
        Pattern passwordPattern = passwordUtils.passwordPattern;
        boolean isPasswordMatches = passwordPattern.matcher(password).matches();
        Assertions.assertThat(isPasswordMatches).isFalse();
    }
}