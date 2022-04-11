package project.mgmt.domain.model.project_mgmt.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import project.mgmt.domain.model.project_mgmt.client.Client;
import project.mgmt.domain.model.project_mgmt.contract.Contract;
import project.mgmt.domain.model.project_mgmt.location.Location;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

import static javax.persistence.InheritanceType.SINGLE_TABLE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = SINGLE_TABLE)
public class Project {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Generated(GenerationTime.INSERT)
    private String id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Location location;
    @OneToMany(mappedBy = "project")
    private List<SubProject> subProjects;
    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;
    private String projectManager;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
