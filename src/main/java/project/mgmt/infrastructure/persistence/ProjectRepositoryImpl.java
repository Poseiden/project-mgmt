package project.mgmt.infrastructure.persistence;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Repository;
import project.mgmt.domain.model.project_mgmt.project.Project;
import project.mgmt.domain.model.project_mgmt.project.ProjectRepository;
import project.mgmt.domain.model.project_mgmt.project.SubProject;
import project.mgmt.infrastructure.persistence.hibernate.ProjectRepoJPA;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {
    private final ProjectRepoJPA projectRepoJPA;

    public ProjectRepositoryImpl(ProjectRepoJPA projectRepoJPA) {
        this.projectRepoJPA = projectRepoJPA;
    }

    @Override
    public Optional<Project> findById(String id) {
        return this.projectRepoJPA.findById(id);
    }

    @Override
    public Map<String, Set<String>> getProjectSubProjectIdMappingByIds(Set<String> keySet) {
        Map<String, Set<String>> result = Maps.newHashMap();
        List<Project> allById = this.projectRepoJPA.findAllById(keySet);
        allById.forEach(project -> {
            Set<String> subProjectIds = project.getSubProjects().stream()
                    .map(SubProject::getId)
                    .collect(toSet());
            result.put(project.getId(), subProjectIds);
        });
        return result;
    }
}
