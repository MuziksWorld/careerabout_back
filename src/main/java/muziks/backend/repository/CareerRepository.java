package muziks.backend.repository;

import lombok.RequiredArgsConstructor;
import muziks.backend.domain.entity.Careers;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class CareerRepository {

    private final EntityManager em;

    public void save(Careers careers) {
        em.persist(careers);
    }
}
