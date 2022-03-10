package project.mgmt.domain.model.project_mgmt.sub_project_type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubProjectType {
    private String id;
    private List<String> subProjectIdList;
}
