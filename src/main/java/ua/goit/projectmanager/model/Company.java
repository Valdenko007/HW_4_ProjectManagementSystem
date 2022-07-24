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
@Table(name = "companies")
public class Company implements BaseEntity<Long>, Serializable {

    @Serial
    private static final long serialVersionUID = -7810157399176088175L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name_company")
    private String name;

}
