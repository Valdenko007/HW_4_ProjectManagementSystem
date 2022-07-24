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
@Table(name = "projects_customers")
public class ProjectCustomer implements BaseEntity<Long> {

    @Id
    @Column(name = "id_project")
    private Long projectId;
    @Column(name = "id_customer")
    private Long customerId;

    public Long getId() {
        return projectId;
    }

}
