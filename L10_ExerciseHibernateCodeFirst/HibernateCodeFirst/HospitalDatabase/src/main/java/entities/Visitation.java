package entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "visitations")
public class Visitation extends BaseEntity {

    @Column(nullable = false)
    private Date date;

    @Column(length = 100)
    private String comments;

    @ManyToOne(optional = false)
    @JoinColumn(referencedColumnName = "id")
    private Patient patient;

    public Visitation() {
    }
}
