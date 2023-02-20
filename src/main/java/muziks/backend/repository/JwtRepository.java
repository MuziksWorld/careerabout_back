package muziks.backend.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import muziks.backend.domain.entity.RefreshToken;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JwtRepository {

    private final EntityManager em;

    public void save(RefreshToken refreshToken) {
        em.persist(refreshToken);
    }

    public RefreshToken findByTokenId(Long tokenId) {
        String query = "select r from RefreshToken r" +
                        " where r.id = :tokenId";

        return (RefreshToken) em.createQuery(query)
                .setParameter("tokenId", tokenId)
                .getSingleResult();
    }
}
