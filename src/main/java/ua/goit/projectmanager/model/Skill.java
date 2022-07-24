package ua.goit.projectmanager.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "skills")
public class Skill implements BaseEntity<Long>, Serializable {


    @Serial
    private static final long serialVersionUID = 6287238992467177733L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "language")
    private String language;

    @Column(name = "level", columnDefinition = "ENUM('Junior', 'Middle', 'Senior')")
    @Enumerated(EnumType.STRING)
    private Level level;

}
