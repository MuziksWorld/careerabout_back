package muziks.backend;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import muziks.backend.domain.entity.*;
import muziks.backend.repository.*;
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
        private final EduRepository eduRepository;
        private final TechRepository techRepository;
        private final AwardRepository awardRepository;
        private final ProjectRepository projectRepository;

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
                    String dataNum = "data" + j;

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

                    Edu edu = new Edu();
                    edu.setUser(user);
                    edu.setName(dataNum);
                    eduRepository.save(edu);

                    Tech tech = new Tech();
                    tech.setUser(user);
                    tech.setTech(dataNum);
                    techRepository.save(tech);

                    Award award = new Award();
                    award.setUser(user);
                    award.setName(dataNum);
                    awardRepository.save(award);

                    Project project = new Project();
                    project.setUser(user);
                    project.setName(dataNum);
                    projectRepository.save(project);
                }
            }
        }
    }
}
