package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bets")
public class Bet extends BaseEntity {

    @Column(name = "bet_money")
    private BigDecimal betMoney;

    @Column(name = "date_time_of_bet")
    private LocalDateTime dateTimeOfBet;

    @ManyToOne
    private User user;

    public Bet() {
    }

    public BigDecimal getBetMoney() {
        return betMoney;
    }

    public void setBetMoney(BigDecimal betMoney) {
        this.betMoney = betMoney;
    }

    public LocalDateTime getDateTimeOfBet() {
        return dateTimeOfBet;
    }

    public void setDateTimeOfBet(LocalDateTime dateTimeOfBet) {
        this.dateTimeOfBet = dateTimeOfBet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
