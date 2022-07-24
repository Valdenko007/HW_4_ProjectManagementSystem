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
@Table(name = "developers_skills")
public class DeveloperSkill implements BaseEntity<Long> {

    @Id
    @Column(name = "id_developer")
    private Long developerId;
    @Column(name = "id_skill")
    private Long skillId;

    public Long getId() {
        return developerId;
    }
}
