package muziks.backend;

import lombok.RequiredArgsConstructor;
import muziks.backend.domain.dto.logindtos.LoginDto;
import muziks.backend.domain.dto.signdtos.SignDto;
import muziks.backend.domain.entity.User;
import muziks.backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import java.util.Optional;

@RequiredArgsConstructor
@SpringBootTest
public class test {

    @Autowired
    private UserRepository userRepository;

    @Test
    void optionalTest() {
    }
}
