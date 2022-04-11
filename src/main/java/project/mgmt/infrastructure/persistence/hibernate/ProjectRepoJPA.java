package project.mgmt.infrastructure.persistence.hibernate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.mgmt.domain.model.project_mgmt.project.Project;

@Repository
public interface ProjectRepoJPA extends JpaRepository<Project, String> {
}
