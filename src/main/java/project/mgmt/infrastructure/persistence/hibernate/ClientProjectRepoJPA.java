package project.mgmt.infrastructure.persistence.hibernate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.mgmt.domain.model.project_mgmt.project.ClientProject;

@Repository
public interface ClientProjectRepoJPA extends JpaRepository<ClientProject, String> {
}
