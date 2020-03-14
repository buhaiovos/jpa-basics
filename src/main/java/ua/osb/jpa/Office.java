package ua.osb.jpa;

import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "OFFICE")
@ToString
public class Office {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String location;

    @OneToMany
    @JoinColumn(name = "office_id")
    private Set<Person> officeWorkers;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Person> getOfficeWorkers() {
        return officeWorkers;
    }

    public void setOfficeWorkers(Set<Person> officeWorkers) {
        this.officeWorkers = officeWorkers;
    }
}
