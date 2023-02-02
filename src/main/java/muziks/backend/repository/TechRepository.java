package muziks.backend.repository;

import lombok.RequiredArgsConstructor;
import muziks.backend.domain.entity.Tech;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class TechRepository {

    private final EntityManager em;

    public void save(Tech tech) {
        em.persist(tech);
    }
}
