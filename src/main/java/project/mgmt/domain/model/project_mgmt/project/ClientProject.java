package project.mgmt.domain.model.project_mgmt.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.mgmt.domain.model.project_mgmt.util.Period;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("client")
public class ClientProject extends Project {
    @Embedded
    private Period period;
}
