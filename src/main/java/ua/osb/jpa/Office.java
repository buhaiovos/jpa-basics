package ua.osb.jpa;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "OFFICE")
@ToString
@Getter
@Setter
public class Office {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String location;

    @OneToMany(mappedBy = "office")
    private Set<Person> officeWorkers;
}
