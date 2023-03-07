package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "bank_account")
public class BankAccount extends BillingDetail {

    @Column(name = "bank", length = 30)
    private String name;

    @Column(name = "swift", length = 30)
    private String swiftCode;

    public BankAccount() {
    }
}
