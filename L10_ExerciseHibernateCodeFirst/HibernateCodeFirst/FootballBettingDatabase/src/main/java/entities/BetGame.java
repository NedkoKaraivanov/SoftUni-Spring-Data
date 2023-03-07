package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "bet_game")
public class BetGame {

    @Id
    @OneToOne
    private Game game;

    @Id
    @OneToOne
    private Bet bet;

    @OneToOne
    @JoinColumn
    private ResultPrediction resultPrediction;

    public BetGame() {}

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    public ResultPrediction getResultPrediction() {
        return resultPrediction;
    }

    public void setResultPrediction(ResultPrediction resultPrediction) {
        this.resultPrediction = resultPrediction;
    }
}
