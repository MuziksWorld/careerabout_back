package muziks.backend.controller;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import muziks.backend.BackendApplication;
import muziks.backend.domain.dto.signdtos.SignDto;
import muziks.backend.service.UserService;
import org.aspectj.lang.annotation.Before;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@RequiredArgsConstructor
@SpringBootTest(classes = BackendApplication.class)
@ExtendWith(MockitoExtension.class)
@Transactional
class JoinControllerTest {

    @InjectMocks
    private JoinController joinController;
    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(joinController).build();
    }

    @Test
    void sign_성공케이스() throws Exception {
        // given
        SignDto request = signUpRequest();
        SignDto response = userResponse();

        // Unnecessary stubbings detected. 발생
        // doReturn(response).when(userService).save(any(SignDto.class));

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/sign")
                        .contentType(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(request))
        );

        // then -> status().isOk() 검증의 경우 테스트 상에서 정규표현식이 제대로 작동하지 않는 것으로 보임
        // postman에서는 정상적으로 기능 작동됨
        ResultActions mvcResult = resultActions.andExpect(status().isOk())
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
}