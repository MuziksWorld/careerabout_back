package muziks.backend.domain.service;


import lombok.RequiredArgsConstructor;
import muziks.backend.domain.entity.Careers;
import muziks.backend.repository.CareerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CareerService {

    private final CareerRepository careerRepository;

    public void save(Careers careers) {
        careerRepository.save(careers);
    }
}