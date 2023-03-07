package entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "deposits")
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "deposit_group", length = 20)
    private String group;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "deposit_amount")
    private Double amount;

    @Column(name = "deposit_interest")
    private Double interest;

    @Column(name = "deposit_charge")
    private Double charge;

    @Column(name = "deposit_expiration_date")
    private LocalDate expirationDate;

    @Column(name = "is_deposit_expired")
    private boolean isExpired;

    public Deposit() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public Double getCharge() {
        return charge;
    }

    public void setCharge(Double charge) {
        this.charge = charge;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }
}
