package ua.goit.projectmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "companies_projects")
public class CompanyProject implements BaseEntity<Long> {
    @Id
    @Column(name = "id_company")
    private Long companyId;
    @Column(name = "id_project")
    private Long projectId;

    public Long getId() {
        return companyId;
    }

}
