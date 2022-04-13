package project.mgmt.domain.model.project_mgmt.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Period {
    private LocalDate startDate;
    private LocalDate endDate;
}
