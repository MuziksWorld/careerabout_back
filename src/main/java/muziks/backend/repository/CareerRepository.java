package muziks.backend.repository;

import lombok.RequiredArgsConstructor;
import muziks.backend.domain.entity.Career;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class CareerRepository {

    private final EntityManager em;

    public void save(Career career) {
        em.persist(career);
    }
}
