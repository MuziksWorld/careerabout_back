package muziks.backend.repository;

import muziks.backend.domain.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    private User user1;
    private User user2;

    @BeforeEach
    public void initData() {
        user1 = User.builder()
                .userId("testUser1")
                .name("테스트1")
                .password("!!Test123")
                .phoneNumber("01012341234")
                .build();

        user2 = User.builder()
                .userId("testUser2")
                .name("테스트2")
                .password("!!Test123")
                .phoneNumber("01012341234")
                .build();

        userRepository.save(user1);
        userRepository.save(user2);
    }

    @Test
    void 단건조회테스트() {
        User findUser = userRepository.findByUserId("testUser1");
        assertThat(findUser.getUserId()).isEqualTo(user1.getUserId());
        assertThat(findUser.getName()).isEqualTo(user1.getName());
        assertThat(findUser.getPassword()).isEqualTo(user1.getPassword());
        assertThat(findUser.getPhoneNumber()).isEqualTo(user1.getPhoneNumber());
    }

    @Test
    void 삭제테스트() {
        userRepository.deleteByUserId("testUser1");
        userRepository.deleteByUserId("testUser2");
        assertThat(userRepository.findByUserId("testUser1")).isNull();
        assertThat(userRepository.findByUserId("testUser2")).isNull();
    }
}