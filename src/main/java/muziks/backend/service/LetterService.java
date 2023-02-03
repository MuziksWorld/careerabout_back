package muziks.backend.service;

import lombok.RequiredArgsConstructor;
import muziks.backend.domain.entity.Letter;
import muziks.backend.repository.LetterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LetterService {

    private final LetterRepository letterRepository;

    public void save(Letter letter) {
        letterRepository.save(letter);
    }
}
