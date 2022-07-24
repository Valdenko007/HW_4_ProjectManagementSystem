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
@Table(name = "projects")
public class Project implements BaseEntity<Long>, Serializable {


    @Serial
    private static final long serialVersionUID = 2373092481741317883L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name_project")
    private String name;

    @Column(name = "cost")
    private int cost;

    @Column(name = "create_date")
    private String createDate;
}
