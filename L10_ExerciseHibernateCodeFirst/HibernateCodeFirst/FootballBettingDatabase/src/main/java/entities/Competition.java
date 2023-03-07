package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "competitions")
public class Competition extends BaseEntity {

    @Column
    private String name;

    @ManyToOne
    //@Column(name = "competition_type")
    private CompetitionType Type;

    public Competition() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CompetitionType getType() {
        return Type;
    }

    public void setType(CompetitionType type) {
        Type = type;
    }
}
