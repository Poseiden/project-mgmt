package project.mgmt.infrastructure.persistence;

import org.springframework.stereotype.Repository;
import project.mgmt.domain.model.project_mgmt.project.Project;
import project.mgmt.domain.model.project_mgmt.project.ProjectRepository;
import project.mgmt.infrastructure.persistence.hibernate.ProjectRepoJPA;

import java.util.Optional;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {
    private ProjectRepoJPA projectRepoJPA;


    @Override
    public Optional<Project> findById(String id) {
        return this.projectRepoJPA.findById(id);
    }
}
