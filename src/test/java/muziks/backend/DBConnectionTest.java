package muziks.backend;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import muziks.backend.domain.dto.signdtos.SignDto;
import muziks.backend.repository.UserRepositoryCustom;
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
    private UserRepositoryCustom userRepositoryCustom;

    @Test
    void connectionTest() {
        SignDto signDto = SignDto.builder()
                .userId("test")
                .password("Test123!!")
                .userName("테스트")
                .phoneNumber("01012341234")
                .build();
        userRepositoryCustom.save(signDto.toEntity(signDto));
        log.info("findUser={}", userRepositoryCustom.findById("test"));
    }
}
