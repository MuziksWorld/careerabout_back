package muziks.backend.repository;

import lombok.RequiredArgsConstructor;
import muziks.backend.domain.entity.Award;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class AwardRepository {

    private final EntityManager em;

    public void save(Award award) {
        em.persist(award);
    }
}
