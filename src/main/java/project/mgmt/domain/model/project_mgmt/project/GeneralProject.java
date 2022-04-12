package project.mgmt.domain.model.project_mgmt.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@Entity
@DiscriminatorValue("general")
public class GeneralProject extends Project {
}
