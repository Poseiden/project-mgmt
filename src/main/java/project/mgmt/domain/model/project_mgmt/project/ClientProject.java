package project.mgmt.domain.model.project_mgmt.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.mgmt.domain.model.project_mgmt.assignment.Assignment;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ClientProject extends Project {
    @OneToMany(mappedBy = "clientProject")
    private List<Assignment> assignments;
    @Embedded
    private Period period;
}
