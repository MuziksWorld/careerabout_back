package muziks.backend.jwt;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * HandlerInterceptor를 구현한 인터셉터 클래스
 */
@Slf4j
@Component
public class BearerAuthInterceptor implements HandlerInterceptor {

    private AuthorizationExtractor authorizationExtractor;
    private JwtTokenProvider jwtTokenProvider;

    public BearerAuthInterceptor(AuthorizationExtractor authorizationExtractor, JwtTokenProvider jwtTokenProvider) {
        this.authorizationExtractor = authorizationExtractor;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * true가 반환되어야만 컨트롤러에 도달한다.
     * 헤더에 있는 토큰을 디코딩해 id를 확인한다.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info(">>> interceptor.preHandle 호출");
        String token = authorizationExtractor.extract(request, "Bearer");
        if (token.isEmpty()) {
            throw new IllegalArgumentException("토큰이 존재하지 않습니다.");
        }
        if (!jwtTokenProvider.validateToken(token, jwtTokenProvider.getRefreshTokenKey())) {
            throw new IllegalArgumentException("리프레쉬 토큰이 유효하지 않습니다.");
        }
        if (!jwtTokenProvider.validateToken(token, jwtTokenProvider.getAccessTokenKey())) {
            throw new IllegalArgumentException("엑세스 토큰이 유효하지 않습니다.");
        }

        String id = jwtTokenProvider.getAuthentication(token, jwtTokenProvider.getAccessTokenKey()).getSubject();
        request.setAttribute("id", id);
        return true;
    }
}
