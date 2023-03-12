package muziks.backend;

import lombok.extern.slf4j.Slf4j;
import muziks.backend.jwt.JwtTokenProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO
	// Controlller에서 Entity 사용하지 않고 service단에서 dto로 뿌려줄 수 있도록 리팩토링
	// dto를 파라미터로 받아 entity로 만들어주는 toEntity 메서드 구현 (service에서)
@Slf4j
@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
		log.info("##### ACCECCTOKEN = {} #######", System.getenv("ACCESS_TOKEN_KEY"));
		log.info("##### REFRESHTOKEN = {} #######", System.getenv("REFRESH_TOKEN_KEY"));
		log.info("##### JASYPT_PASSWORD = {} #######", System.getenv("JASYPT_PASSWORD"));

		JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
		log.info("##### Provider refresh = {} #####", jwtTokenProvider.getRefreshTokenKey());
		log.info("##### Provider access = {} #####", jwtTokenProvider.getAccessTokenKey());
	}
}
