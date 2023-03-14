package muziks.backend.jwt;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import muziks.backend.BackendApplication;
import muziks.backend.controller.JoinController;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@RequiredArgsConstructor
@SpringBootTest(classes = BackendApplication.class)
@ExtendWith(MockitoExtension.class)
@Transactional
class JwtTokenProviderTest {

    @InjectMocks
    private JoinController joinController;
    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    // refresh token 유효시간 5초
    private JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();

    @BeforeEach
    public void initData() {
        // refresh token 유효시간 5초
        jwtTokenProvider.setRefreshTokenValidTime(1000L * 5);
        // Http 호출을 위한 셋업
        mockMvc = MockMvcBuilders.standaloneSetup(joinController).build();
    }

    @Test
    void mockJoinTest() throws Exception {
        // given
        SignDto request = signUpRequest();
        SignDto response = userResponse();

        doReturn(response).when(userService).save(any(SignDto.class));

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/sign")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(request))
        );

        // then
        ResultActions mvcResult = resultActions.andExpect(status().is4xxClientError())
                .andExpect(jsonPath("userId", response.getUserId()).exists())
                .andExpect(jsonPath("userName", response.getUserName()).exists())
                .andExpect(jsonPath("phoneNumber", response.getPhoneNumber()).exists());
    }

    private SignDto userResponse() {
        return SignDto.builder()
                .userId("test12")
                .password("!!Test123")
                .userName("테스트")
                .phoneNumber("01012341234")
                .build();
    }

    private SignDto signUpRequest() {
        return SignDto.builder()
                .userId("test12")
                .password("!!Test123")
                .userName("테스트")
                .phoneNumber("01012341234")
                .build();
    }

//    @Test
//    void initTest() {
//        User findUser = userService.findById("test3").get(0);
//        System.out.println("findUser = " + findUser);
//
//        String refreshToken = findUser.getRefreshToken().getRefreshToken();
//        Claims refresh_token_key = jwtTokenProvider.getAuthentication(refreshToken, System.getenv("REFRESH_TOKEN_KEY"));
//        System.out.println("refresh_token_key = " + refresh_token_key);
//    }


}