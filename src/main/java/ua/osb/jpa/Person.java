package ua.osb.jpa;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "PERSON")
@ToString
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Office office;
}
