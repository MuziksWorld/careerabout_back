package muziks.backend.service;

import lombok.RequiredArgsConstructor;
import muziks.backend.domain.entity.Project;
import muziks.backend.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public void save(Project project) {
        projectRepository.save(project);
    }
}
