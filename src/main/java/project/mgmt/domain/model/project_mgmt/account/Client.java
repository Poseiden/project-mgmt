package project.mgmt.domain.model.project_mgmt.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    private String id;
    private String manager;
    private List<String> contractIdList;
}
