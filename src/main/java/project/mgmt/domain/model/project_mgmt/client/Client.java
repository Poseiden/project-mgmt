package project.mgmt.domain.model.project_mgmt.client;

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
    private String clientManager;
    private List<String> contractIdList;
    private List<String> projectIdList;
}
