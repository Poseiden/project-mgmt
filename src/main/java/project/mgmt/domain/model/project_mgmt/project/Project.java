package project.mgmt.domain.model.project_mgmt.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.mgmt.domain.model.project_mgmt.location.Location;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    private String id;
    private String name;
    private Location location;
    private List<SubProject> subProjects;
    private String projectManager;
    private String contractId;
    private String clientId;
}
