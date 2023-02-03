package muziks.backend.service;

import muziks.backend.domain.entity.Social;
import muziks.backend.repository.SocialRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SocialService {

    SocialRepository socialRepository;

    public void save(Social social) {
        socialRepository.save(social);
    }
}
