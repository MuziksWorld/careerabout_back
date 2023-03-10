package muziks.backend.jwt;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import muziks.backend.BackendApplication;
import muziks.backend.controller.JoinController;
import muziks.backend.controller.LoginController;
import muziks.backend.domain.dto.jwtdtos.TokenDto;
import muziks.backend.domain.dto.logindtos.LoginDto;
import muziks.backend.domain.dto.signdtos.SignDto;
import muziks.backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@RequiredArgsConstructor
@SpringBootTest(classes = BackendApplication.class)
@ExtendWith(MockitoExtension.class)
@Transactional
class JwtTokenProviderTest {

    @InjectMocks
    private JoinController joinController;

    @InjectMocks
    private LoginController loginController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    // refresh token 유효시간 5초
    private JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();

    @BeforeEach
    public void initData() throws Exception {
        // refresh token 유효시간 5초
        jwtTokenProvider.setRefreshTokenValidTime(1000L * 5);
        // Http 호출을 위한 셋업
        mockMvc = MockMvcBuilders.standaloneSetup(joinController).build();

        SignDto request = signUpRequest();
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/sign")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(request))
        );
    }

    @Test
    void initTest() throws Exception {
        // given
        LoginDto loginRequest = loginRequest();
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/login")
        );

        // then
        MvcResult mvcResult = resultActions.andExpect(status().isOk()).andReturn();
        TokenDto tokenDto = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), TokenDto.class);

        log.info("### tokenDto={} ###", tokenDto);

    }

    private SignDto signUpRequest() {
        return SignDto.builder()
                .userId("test12")
                .password("!!Test123")
                .userName("테스트")
                .phoneNumber("01012341234")
                .build();
    }

    private LoginDto loginRequest() {
        return LoginDto.builder()
                .userId("test12")
                .password("!!Test123")
                .build();
    }
}