package project.mgmt.application.service;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;
import project.mgmt.application.dto.VerifyProjectExistResponse;
import project.mgmt.domain.model.project_mgmt.project.ProjectRepository;

import java.util.Map;
import java.util.Set;

@Service
public class ProjectApplicationService {
    private final ProjectRepository projectRepository;

    public ProjectApplicationService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public VerifyProjectExistResponse checkProjectExists(Map<String, Set<String>> projectIdParam) {
        //planB: project and subproject mapping data can be stored in redis

        Map<String, Set<String>> result = Maps.newHashMap();
        Map<String, Set<String>> existInDB = this.projectRepository.getProjectSubProjectIdMappingByIds(projectIdParam.keySet());

        projectIdParam.forEach((projectId, subProjectIds) -> {

            if (!existInDB.containsKey(projectId)) {
                result.put(projectId, subProjectIds);
            } else {
                Set<String> subProjectExistInDB = existInDB.get(projectId);
                if (!subProjectIds.equals(subProjectExistInDB)) {
                    subProjectIds.removeIf(subProjectExistInDB::contains);
                    result.put(projectId, subProjectIds);
                }
            }
        });

        return new VerifyProjectExistResponse(result);
    }
}
