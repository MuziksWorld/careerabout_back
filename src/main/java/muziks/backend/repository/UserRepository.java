package muziks.backend.repository;

import lombok.RequiredArgsConstructor;
import muziks.backend.domain.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public List<User> findByName(String name) {
        return em.createQuery(
                "select u from User u" +
                        " where u.name = :name", User.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<User> findById(String id) {
        return em.createQuery(
                        "select u from User u" +
                                " where u.userId = :id", User.class)
                .setParameter("id", id)
                .getResultList();
    }
}
