package project.mgmt.domain.model.project_mgmt.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import project.mgmt.domain.model.project_mgmt.location.Location;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static javax.persistence.InheritanceType.SINGLE_TABLE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@Table
public abstract class Project {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Generated(GenerationTime.INSERT)
    private String id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Location location;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<SubProject> subProjects;
    private String contractId;
    private String projectManagerId;
    private String clientId;

    public Set<String> getSubProjectIds() {
        return this.subProjects.stream()
                .map(SubProject::getId)
                .collect(toSet());
    }
}
