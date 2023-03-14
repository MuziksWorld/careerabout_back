package muziks.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import muziks.backend.domain.dto.jwtdtos.TokenDto;
import muziks.backend.domain.dto.logindtos.LoginDto;
import muziks.backend.domain.entity.RefreshToken;
import muziks.backend.domain.entity.User;
import muziks.backend.jwt.JwtTokenProvider;
import muziks.backend.repository.JwtRepository;
import muziks.backend.repository.UserRepositoryCustom;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

    private final UserRepositoryCustom userRepositoryCustom;
    private final JwtRepository jwtRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public void login(LoginDto loginDto) {
        User user = findById(loginDto.getUserId()).get(0);
        if (!jwtTokenProvider.validateToken(user.getRefreshToken().getRefreshToken(), jwtTokenProvider.getRefreshTokenKey())) {
            String refreshToken = jwtTokenProvider.createRefreshToken(user.getUserId(), user.getRole());
            RefreshToken existingToken = jwtRepository.findByTokenId(user.getRefreshToken().getId());
            existingToken.setRefreshToken(refreshToken);
        }
    }

    public void logout(String refreshToken) {
        log.info("authorization= {} ", refreshToken);
        RefreshToken refreshTokenEntity = findByRefreshToken(refreshToken);
        refreshTokenEntity.setRefreshToken(null);
    }

    public TokenDto getTokenDto(LoginDto loginDto) {
        User user = findById(loginDto.getUserId()).get(0);
        return TokenDto.builder()
                        .userId(user.getUserId())
                        .refreshToken(user.getRefreshToken().getRefreshToken())
                        .accessToken(jwtTokenProvider.createAccessToken(user.getUserId(), user.getRole()))
                        .build();

    }

    private List<User> findByName(String name) {
        return userRepositoryCustom.findByName(name);
    }

    public void save(User user) {
        userRepositoryCustom.save(user);
    }

    public List<User> findById(String id) {
        return userRepositoryCustom.findById(id);
    }

    public RefreshToken findByRefreshToken(String refreshToken) {
        return userRepositoryCustom.findRefreshTokenById(refreshToken);
    }
}
