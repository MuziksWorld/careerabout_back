package muziks.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import muziks.backend.domain.entity.User;
import muziks.backend.jwt.JwtTokenProvider;
import muziks.backend.jwt.Token;
import muziks.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public Token createAndGetToken(String id) {
        User user = findById(id);
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getUserId(), user.getRole());
        String accessToken = jwtTokenProvider.createAccessToken(user.getUserId(), user.getRole());
        user.setAuthorization(refreshToken);
        userRepository.save(user);
        return Token.builder()
                .userId(id)
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .build();
    }

    public User findById(String id) {
        return userRepository.findById(id).get(0);
    }

    public User findByAuthorization(String authorization) {
        return userRepository.findByAuthorization(authorization);
    }

    public void logout(String findAuthorization) {
        log.info("authorization= {} ",findAuthorization);
        String authorization = findAuthorization.substring(7);
        User findUser = findByAuthorization(authorization);
        findUser.setAuthorization(null);
//        save(findUser);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
