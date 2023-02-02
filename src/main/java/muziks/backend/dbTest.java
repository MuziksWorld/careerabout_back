package muziks.backend;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import muziks.backend.domain.entity.Career;
import muziks.backend.domain.entity.Letter;
import muziks.backend.domain.entity.Social;
import muziks.backend.domain.entity.User;
import muziks.backend.repository.CareerRepository;
import muziks.backend.repository.LetterRepository;
import muziks.backend.repository.SocialRepository;
import muziks.backend.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@RequiredArgsConstructor
public class dbTest {

    private final initService initService;

    @PostConstruct
    private void init() {
        log.info("testDB 작업 실행 완료");
        initService.initUsers();
        initService.initData();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class initService {
        private final UserRepository userRepository;
        private final CareerRepository careerRepository;
        private final SocialRepository socialRepository;
        private final LetterRepository letterRepository;

        public void initUsers() {
            for (int i = 1; i < 11; i++) {
                User user = new User();
                String userInfo = "user" + i;
                user.setUserId(userInfo);
                user.setPassword(userInfo);
                user.setAddress(userInfo);
                user.setBirth(userInfo);
                user.setEmail(userInfo);
                user.setEnglishName(userInfo);
                user.setName(userInfo);
                user.setPhone(userInfo);
                user.setProfileImg(userInfo);
                user.setResumeBool(1);
                userRepository.save(user);
            }
        }

        public void initData() {
            for (int i = 1; i < 11; i++) {
                String userName = "user" + i;

                for (int j = 1; j < 3; j++) {
                    String dataNum = "career" + j;

                    User user = userRepository.findByName(userName).get(0);
                    Career career = new Career();
                    career.setUser(user);
                    career.setName(dataNum);
                    careerRepository.save(career);

                    Social social = new Social();
                    social.setUser(user);
                    social.setSocial(dataNum);
                    socialRepository.save(social);

                    Letter letter = new Letter();
                    letter.setUser(user);
                    letterRepository.save(letter);
                }
            }
        }
    }
}
