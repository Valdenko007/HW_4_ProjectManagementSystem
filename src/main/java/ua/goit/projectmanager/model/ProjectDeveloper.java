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
@Table(name = "projects_developers")
public class ProjectDeveloper implements BaseEntity<Long> {

    @Id
    @Column(name = "id_project")
    private Long projectId;
    @Column(name = "id_developer")
    private Long developerId;

    public Long getId() {
        return projectId;
    }

}
