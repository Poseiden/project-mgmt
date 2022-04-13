package project.mgmt.application.service;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;
import project.mgmt.domain.model.project_mgmt.project.ProjectRepository;

import java.util.Map;
import java.util.Set;

@Service
public class ProjectApplicationService {
    private final ProjectRepository projectRepository;

    public ProjectApplicationService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Map<String, Set<String>> checkProjectExists(Map<String, Set<String>> projectIds) {
        //planB: project and subproject mapping data can be stored in redis

        Map<String, Set<String>> result = Maps.newHashMap();
        Map<String, Set<String>> existInDB = this.projectRepository.getProjectSubProjectIdMappingByIds(projectIds.keySet());

        projectIds.forEach((projectId, subProjectIds) -> {
            if (!existInDB.containsKey(projectId)) {
                result.put(projectId, subProjectIds);
            }
        });
        return result;
    }
}
