package project.mgmt.application.service;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;
import project.mgmt.application.dto.VerifyProjectExistDTO;
import project.mgmt.domain.model.project_mgmt.project.ProjectRepository;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjectApplicationService {
    private final ProjectRepository projectRepository;

    public ProjectApplicationService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<VerifyProjectExistDTO> checkProjectExists(List<VerifyProjectExistDTO> projectAndSubprojectIdParams) {
        Map<String, Set<String>> existInDB = this.projectRepository.
                getProjectSubProjectIdMappingByIds(getProjectIdSet(projectAndSubprojectIdParams));

        Map<String, Set<String>> result = Maps.newHashMap();
        projectAndSubprojectIdParams.forEach(param -> compareToExistInDB(existInDB, result, param));

        return result.entrySet()
                .stream()
                .map(e -> new VerifyProjectExistDTO(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    private void compareToExistInDB(Map<String, Set<String>> existInDB, Map<String, Set<String>> result, VerifyProjectExistDTO param) {
        String projectId = param.getProjectId();
        Set<String> subprojectIds = param.getSubprojectIds();

        if (!existInDB.containsKey(projectId)) {
            result.put(projectId, subprojectIds);
        } else {
            Set<String> subProjectExistInDB = existInDB.get(projectId);
            if (!subprojectIds.equals(subProjectExistInDB)) {
                subprojectIds.removeIf(subProjectExistInDB::contains);
                result.put(projectId, subprojectIds);
            }
        }
    }

    private Set<String> getProjectIdSet(List<VerifyProjectExistDTO> projectAndSubprojectIdParams) {
        return projectAndSubprojectIdParams.stream().map(VerifyProjectExistDTO::getProjectId).collect(Collectors.toSet());
    }
}
