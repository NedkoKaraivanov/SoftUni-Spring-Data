package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "medicaments")
public class Medicament extends BaseEntity {

    @Column(nullable = false, length = 40)
    private String name;

    @ManyToMany(mappedBy = "prescriptions")
    private Set<Patient> patients;

    public Medicament() {

    }
}
