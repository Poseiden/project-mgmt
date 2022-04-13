package project.mgmt.presentation.controller;

import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
public class ProjectController {

    //todo change to restful uri
    @GetMapping("/projects/batch")
    public Map<String, Set<String>> checkProjectExists(@RequestBody Map<String, Set<String>> projectIds) {
        return Maps.newHashMap();
    }
}
