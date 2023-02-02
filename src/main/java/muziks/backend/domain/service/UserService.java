package muziks.backend.domain.service;

import lombok.RequiredArgsConstructor;
import muziks.backend.domain.entity.Users;
import muziks.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void save(Users users) {
        userRepository.save(users);
    }
}
