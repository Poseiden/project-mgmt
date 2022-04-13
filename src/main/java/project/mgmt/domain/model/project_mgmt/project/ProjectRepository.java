package project.mgmt.domain.model.project_mgmt.project;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository {

    Optional<Project> findById(String key);
}
