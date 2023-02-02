package muziks.backend.domain.service;

import lombok.RequiredArgsConstructor;
import muziks.backend.domain.entity.User;
import muziks.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }
}
