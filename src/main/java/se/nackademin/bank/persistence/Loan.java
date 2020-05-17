package se.nackademin.bank.persistence;

import javax.persistence.*;

@Entity
public class Loan {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private User user;
    private double amount;
    private boolean approved;

    public Loan() {
    }

    public Loan(User user, double amount, boolean approved) {
        this.user = user;
        this.amount = amount;
        this.approved = approved;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", user=" + user +
                ", amount=" + amount +
                ", approved=" + approved +
                '}';
    }
}
