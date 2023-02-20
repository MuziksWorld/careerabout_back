package muziks.backend.service;

import lombok.RequiredArgsConstructor;
import muziks.backend.domain.dto.jwtdtos.TokenDto;
import muziks.backend.jwt.JwtTokenProvider;
import muziks.backend.repository.JwtRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class JwtService {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtRepository jwtRepository;

    public void login(TokenDto tokenDto) {
    }
}
