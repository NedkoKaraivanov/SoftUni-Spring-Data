package entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "games")
public class Game extends BaseEntity {

    @OneToOne
    @JoinColumn
    private Team homeTeam;

    @OneToOne
    @JoinColumn
    private Team awayTeam;

    @Column(name = "home_goals")
    private Short homeGoals;

    @Column(name = "away_goals")
    private Short awayGoals;

    @Column(name = "date_of_game")
    private Date dateAndTime;

    @Column
    private double homeTeamWinBetRate;

    @Column
    private double awayTeamWinBetRate;

    @Column
    private double drawGameBetRate;

    @ManyToOne
    @JoinColumn
    private Round round;

    @ManyToOne
    @JoinColumn
    private Competition competition;

}
