package muziks.backend.service;

import lombok.RequiredArgsConstructor;
import muziks.backend.domain.entity.Award;
import muziks.backend.repository.AwardRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AwardService {

    private final AwardRepository awardRepository;

    public void save(Award award) {
        awardRepository.save(award);
    }
}
