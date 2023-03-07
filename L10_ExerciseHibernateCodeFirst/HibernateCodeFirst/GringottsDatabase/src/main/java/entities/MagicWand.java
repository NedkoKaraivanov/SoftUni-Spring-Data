package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "magic_wands")
public class MagicWand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "magic_wand_creator", length = 100)
    private String magicWandCreator;

    @Column(name = "magic_wand_size")
    private int magicWandSize;

    public MagicWand() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMagicWandCreator() {
        return magicWandCreator;
    }

    public void setMagicWandCreator(String magicWandCreator) {
        this.magicWandCreator = magicWandCreator;
    }

    public int getMagicWandSize() {
        return magicWandSize;
    }

    public void setMagicWandSize(int magicWandSize) {
        this.magicWandSize = magicWandSize;
    }
}
