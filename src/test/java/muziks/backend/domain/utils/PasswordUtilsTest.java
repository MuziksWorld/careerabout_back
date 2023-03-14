package muziks.backend.domain.utils;

import lombok.extern.slf4j.Slf4j;
import muziks.backend.domain.dto.logindtos.LoginDto;
import muziks.backend.domain.dto.signdtos.SignDto;
import muziks.backend.repository.UserRepository;
import muziks.backend.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * JwtTokenProviderTest에서 비밀번호 올바르게 입력했는데
 * 비밀번호 관련해서 오류 발생.
 * 비밀번호 정규식 테스트 해서 통과 되는 경우에 적용할 것
 */
@Slf4j
class PasswordUtilsTest {

    @Autowired
    private PasswordUtils passwordUtils;

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository repository;

    private MockMvc mockMvc;

    @Test
    void passwordTest_통과케이스() {
        String password = "Test123!!";
        Pattern passwordPattern = passwordUtils.passwordPattern;
        boolean isPasswordMatches = passwordPattern.matcher(password).matches();
        assertThat(isPasswordMatches).isTrue();
    }

    @Test
    void passwordTest_매칭되지않을때() {
        String password = "Test123";
        Pattern passwordPattern = passwordUtils.passwordPattern;
        boolean isPasswordMatches = passwordPattern.matcher(password).matches();
        assertThat(isPasswordMatches).isFalse();
    }

    @Test
    void userService에서_passwordUtils테스트() throws Exception {
        // given
        LoginDto request = loginRequest();
        String password = "!!Test123";

        // when
        boolean validPassword = userService.isValidPassword(password);

        // then
        assertThat(validPassword).isTrue();
    }

    private LoginDto loginRequest() {
        return LoginDto.builder()
                .userId("test12")
                .password("!!Test123")
                .build();
    }
}