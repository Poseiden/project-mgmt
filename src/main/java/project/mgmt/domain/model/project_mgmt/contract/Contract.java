package project.mgmt.domain.model.project_mgmt.contract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contract {
    private String id;
    private String accountId;
    private List<String> projectIdList;
}
