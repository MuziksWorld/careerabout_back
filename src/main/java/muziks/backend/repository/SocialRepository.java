package muziks.backend.repository;

import lombok.RequiredArgsConstructor;
import muziks.backend.domain.entity.Social;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class SocialRepository {

    private final EntityManager em;

    public void save(Social social) {
        em.persist(social);
    }
}
