package muziks.backend.service;

import lombok.RequiredArgsConstructor;
import muziks.backend.domain.entity.Tech;
import muziks.backend.repository.TechRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TechService {

    private final TechRepository techRepository;

    public void save(Tech tech) {
        techRepository.save(tech);
    }
}
