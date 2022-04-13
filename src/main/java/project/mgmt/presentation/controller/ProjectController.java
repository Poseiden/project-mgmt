package project.mgmt.presentation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.mgmt.application.service.ProjectApplicationService;

import java.util.Map;
import java.util.Set;

@RestController
public class ProjectController {
    private final ProjectApplicationService projectApplicationService;

    public ProjectController(ProjectApplicationService projectApplicationService) {
        this.projectApplicationService = projectApplicationService;
    }

    //todo change to restful uri
    @GetMapping("/projects/batch")
    public Map<String, Set<String>> checkProjectExists(@RequestBody Map<String, Set<String>> projectIds) {
        return this.projectApplicationService.checkProjectExists(projectIds);
    }
}
