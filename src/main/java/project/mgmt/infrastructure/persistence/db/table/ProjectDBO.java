package project.mgmt.infrastructure.persistence.db.table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project")
public class ProjectDBO {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Generated(GenerationTime.INSERT)
    private String id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private LocationDBO locationId;
    @OneToMany(mappedBy = "projectDBO")
    private List<SubProjectDBO> subProjectDBOList;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    private ContractDBO contractDBO;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientDBO clientDBO;
}
