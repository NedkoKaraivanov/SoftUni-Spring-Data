package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "continents")
public class Continent extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany
    private Set<Country> countries;

    public Continent() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
