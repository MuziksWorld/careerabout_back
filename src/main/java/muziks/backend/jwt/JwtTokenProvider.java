package muziks.backend.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Component
@Getter
public class JwtTokenProvider {
    //    @Value("${jwt.refresh-token-key}")
    private String refreshTokenKey = System.getenv("REFRESH_TOKEN_KEY");
    //    @Value("${jwt.access-token-key}")
    private String accessTokenKey = System.getenv("ACCESS_TOKEN_KEY");

    private Long accessTokenValidTime = 30 * 60 * 1000L; // 토큰 유효시간 30분
    //    private Long refreshTokenValidTime = 14 * 24 * 60 * 60 * 1000L; // 토큰 유효시간 2주
    private Long refreshTokenValidTime = 1000L * 10; // 토큰 유효시간 10초

    /**
     * 객체 초기회, secretKey를 Base64로 인코딩한다.
     * 이렇게 하면 디코딩할 때도 시크릿키를 똑같이 인코딩해서 주어야 함
     */
    @PostConstruct
    protected void init() {
        Base64.getEncoder().encodeToString(refreshTokenKey.getBytes());
        Base64.getEncoder().encodeToString(accessTokenKey.getBytes());
    }

    // Jwt 토큰 생성
    public String createRefreshToken(String userId, String role) {
        Claims claims = Jwts.claims().setSubject(userId); // jwt payload에 저장되는 정보 단위
        claims.put("roles", role); // 정보는 key, value 쌍으로 저장된다.
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발생 시간 정보
                .setExpiration(new Date(now.getTime() + refreshTokenValidTime)) // 만료 시간 설정
                .signWith(SignatureAlgorithm.HS256, refreshTokenKey) // 사용할 암호 알고리즘과 signature에 들어갈 secretKey 설정
                .compact();
    }

    public String createAccessToken(String userPk, String role) {
        Claims claims = Jwts.claims().setSubject(userPk); // jwt payload에 저장되는 정보 단위
        claims.put("roles", role); // 정보는 key, value 쌍으로 저장된다.
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발생 시간 정보
                .setExpiration(new Date(now.getTime() + accessTokenValidTime)) // 만료 시간 설정
                .signWith(SignatureAlgorithm.HS256, accessTokenKey) // 사용할 암호 알고리즘과 signature에 들어갈 secretKey 설정
                .compact();
    }

    // Jwt 토큰에서 인증 정보 조회 (원래 Authentication을 반환)
    public Claims getAuthentication(String token, String tokenKey) {
        Claims claims = Jwts.parser()
                .setSigningKey(tokenKey)
                .parseClaimsJws(token).getBody();

        return claims;
    }

    /**
     * 토큰에서 회원 아이디를 추출
     * return: userId
     * 만약 secretkey (tokenKey)를 64비트로 인코딩해서 토큰을 생성했다면 똑같이 변환해 주어야 함
     */
    public String getUserPk(String token, String tokenKey) {
        return Jwts.parser().setSigningKey(tokenKey).parseClaimsJws(token).getBody().getSubject();
    }

    // token의 유효성, 만료일자 확인
    public boolean validateToken(String token, String tokenKey) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(tokenKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }


}