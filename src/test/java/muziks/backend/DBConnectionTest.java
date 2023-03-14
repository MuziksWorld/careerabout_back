package muziks.backend;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import muziks.backend.domain.dto.signdtos.SignDto;
import muziks.backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@SpringBootTest(classes = BackendApplication.class)
@Transactional
public class DBConnectionTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void connectionTest() {
        SignDto signDto = SignDto.builder()
                .userId("test")
                .password("Test123!!")
                .name("테스트")
                .phoneNumber("01012341234")
                .build();
        userRepository.save(signDto.toEntity(signDto));
        log.info("findUser={}", userRepository.findById("test"));
    }
}
