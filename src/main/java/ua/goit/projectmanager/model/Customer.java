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
@Table(name = "customers")
public class Customer implements BaseEntity<Long>, Serializable {


    @Serial
    private static final long serialVersionUID = -6812139878686887183L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name_customer")
    private String name;
}
