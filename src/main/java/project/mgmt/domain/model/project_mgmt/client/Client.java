package project.mgmt.domain.model.project_mgmt.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import project.mgmt.domain.model.project_mgmt.contract.Contract;
import project.mgmt.domain.model.project_mgmt.project.Project;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Generated(GenerationTime.INSERT)
    private String id;
    private String clientManager;
    @OneToMany(mappedBy = "client")
    private List<Contract> contracts;
    @OneToMany(mappedBy = "client")
    private List<Project> projects;
}
