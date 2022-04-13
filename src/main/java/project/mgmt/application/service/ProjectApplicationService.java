package project.mgmt.application.service;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;
import project.mgmt.domain.model.project_mgmt.project.Project;
import project.mgmt.domain.model.project_mgmt.project.ProjectRepository;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class ProjectApplicationService {
    private final ProjectRepository projectRepository;

    public ProjectApplicationService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Map<String, Set<String>> checkProjectExists(Map<String, Set<String>> projectIds) {
        Map<String, Set<String>> result = Maps.newHashMap();
        for (Map.Entry<String, Set<String>> entry : projectIds.entrySet()) {
            String key = entry.getKey();
            Optional<Project> opProject = this.projectRepository.findById(key);
            if (opProject.isEmpty()) {
                result.put(key, entry.getValue());
            }
        }
        return result;
    }
}
