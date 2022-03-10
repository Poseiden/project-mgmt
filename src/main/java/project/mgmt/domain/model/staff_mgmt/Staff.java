package project.mgmt.domain.model.staff_mgmt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
    private String id;
    private List<String> assignmentIdList;
    private List<String> projectIdList;
    private List<String> accountIdList;
}
