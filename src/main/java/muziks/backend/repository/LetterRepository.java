package muziks.backend.repository;

import lombok.RequiredArgsConstructor;
import muziks.backend.domain.entity.Letter;
import muziks.backend.domain.entity.Social;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class LetterRepository {

    private final EntityManager em;

    public void save(Letter letter) {
        em.persist(letter);
    }
}
