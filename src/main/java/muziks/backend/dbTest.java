package muziks.backend;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import muziks.backend.domain.entity.Careers;
import muziks.backend.domain.entity.Users;
import muziks.backend.repository.CareerRepository;
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
        initService.initCareers();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class initService {
        private final UserRepository userRepository;
        private final CareerRepository careerRepository;

        public void initUsers() {
            for (int i = 1; i < 11; i++) {
                Users users = new Users();
                String userInfo = "user" + i;
                users.setUserId(userInfo);
                users.setUserPw(userInfo);
                users.setUserAddress(userInfo);
                users.setUserBirth(userInfo);
                users.setUserEmail(userInfo);
                users.setUserEnName(userInfo);
                users.setUserName(userInfo);
                users.setUserPhone(userInfo);
                users.setUserProfileImg(userInfo);
                users.setUserResumeBool(1);
                userRepository.save(users);
            }
        }

        public void initCareers() {
            for (int i = 1; i < 11; i++) {
                String userName = "user" + i;

                for (int j = 1; j < 3; j++) {
                    Users user = userRepository.findByName(userName).get(0);
                    Careers career = new Careers();
                    career.setUser(user);
                    String careerNum = "career" + j;
                    career.setCareersName(careerNum);
                    careerRepository.save(career);
                }
            }
        }
    }
}
