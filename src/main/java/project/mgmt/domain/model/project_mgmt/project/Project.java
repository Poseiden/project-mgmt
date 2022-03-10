package project.mgmt.domain.model.project_mgmt.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    private String id;
    private String name;
    private String locationId;
    private List<SubProject> subProjects;
    private String projectManager;
    private String contractId;
}
