package muziks.backend;


import muziks.backend.jwt.JwtTokenProvider;
import org.junit.jupiter.api.Test;

public class JwtTest {

    JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();

    @Test
    void keyTest() {
        String refreshTokenKey = jwtTokenProvider.getRefreshTokenKey();
        System.out.println("refreshTokenKey = " + refreshTokenKey);
        String accessTokenKey = jwtTokenProvider.getAccessTokenKey();
        System.out.println("accessTokenKey = " + accessTokenKey);
    }
}
