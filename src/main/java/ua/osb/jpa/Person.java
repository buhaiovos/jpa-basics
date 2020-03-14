package ua.osb.jpa;

import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "PERSON")
@ToString
public class Person {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name")
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
