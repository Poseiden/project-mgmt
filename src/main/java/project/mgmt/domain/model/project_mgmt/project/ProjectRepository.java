package project.mgmt.domain.model.project_mgmt.project;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ProjectRepository {

    Optional<Project> findById(String key);

    Map<String, Set<String>> getProjectSubProjectIdMappingByIds(Set<String> keySet);
}
