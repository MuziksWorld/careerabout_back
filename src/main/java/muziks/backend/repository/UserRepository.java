package muziks.backend.repository;

import lombok.RequiredArgsConstructor;
import muziks.backend.domain.entity.Users;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public void save(Users users) {
        em.persist(users);
    }

    public List<Users> findByName(String name) {
        return em.createQuery(
                "select u from Users u" +
                        " where u.userName = :name", Users.class)
                .setParameter("name", name)
                .getResultList();
    }
}
