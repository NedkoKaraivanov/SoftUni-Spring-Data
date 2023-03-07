package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "credit_card")
public class CreditCard extends BillingDetail {

    @Column(length = 30)
    private String cardType;

    @Column(name = "expiration_month")
    private byte expirationMonth;

    @Column(name = "expiration_year")
    private short expirationYear;

    public CreditCard() {
    }
}
