package muziks.backend.domain.service;

import lombok.RequiredArgsConstructor;
import muziks.backend.domain.entity.Edu;
import muziks.backend.repository.EduRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EduService {

    private final EduRepository eduRepository;

    public void save(Edu edu) {
        eduRepository.save(edu);
    }
}
