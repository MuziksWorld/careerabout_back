package muziks.backend.repository;

import lombok.RequiredArgsConstructor;
import muziks.backend.domain.entity.Project;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ProjectRepository {

    private final EntityManager em;

    public void save(Project project) {
        em.persist(project);
    }
}
