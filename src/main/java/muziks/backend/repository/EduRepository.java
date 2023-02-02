package muziks.backend.repository;

import lombok.RequiredArgsConstructor;
import muziks.backend.domain.entity.Edu;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class EduRepository {

    private final EntityManager em;

    public void save(Edu edu) {
        em.persist(edu);
    }
}
