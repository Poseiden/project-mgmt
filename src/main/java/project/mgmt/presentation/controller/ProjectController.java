package project.mgmt.presentation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.mgmt.application.dto.VerifyProjectExistDTO;
import project.mgmt.application.service.ProjectApplicationService;

import java.util.List;

@RestController
public class ProjectController {
    private final ProjectApplicationService projectApplicationService;

    public ProjectController(ProjectApplicationService projectApplicationService) {
        this.projectApplicationService = projectApplicationService;
    }

    @GetMapping("/projects/invalid-project-ids")
    public List<VerifyProjectExistDTO> checkProjectExists(@RequestParam("projects") String projectAndSubprojectIds) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<VerifyProjectExistDTO>> typeRef
                = new TypeReference<>() {};

        return this.projectApplicationService.checkProjectExists(objectMapper.readValue(projectAndSubprojectIds, typeRef));
    }
}
