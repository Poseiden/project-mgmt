package project.mgmt.presentation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.mgmt.application.dto.VerifyProjectExistResponse;
import project.mgmt.application.service.ProjectApplicationService;
import project.mgmt.infrastructure.persistence.hibernate.ProjectRepoJPA;

import java.util.Map;
import java.util.Set;

@RestController
public class ProjectController {
    private final ProjectApplicationService projectApplicationService;
    private final ProjectRepoJPA projectRepoJPA;

    public ProjectController(ProjectApplicationService projectApplicationService, ProjectRepoJPA projectRepoJPA) {
        this.projectApplicationService = projectApplicationService;
        this.projectRepoJPA = projectRepoJPA;
    }

    //todo change to restful uri
    @GetMapping("/projects/batch")
    public VerifyProjectExistResponse checkProjectExists(@RequestParam("projects") String projects) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<Map<String, Set<String>>> typeRef
                = new TypeReference<>() {};
        Map<String, Set<String>> stringStringHashMap = objectMapper.readValue(projects, typeRef);
        return this.projectApplicationService.checkProjectExists(stringStringHashMap);
    }
}
