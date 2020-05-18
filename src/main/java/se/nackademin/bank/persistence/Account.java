package se.nackademin.bank.persistence;

import javax.persistence.*;
import java.util.Optional;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private User user;
    private double balance;
    private double interestRate;

    public Account() {
    }

    public Account(User user, double balance, double interestRate) {
        this.user = user;
        this.balance = balance;
        this.interestRate = interestRate;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public double getBalance() {
        return balance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", user=" + user +
                ", balance=" + balance +
                ", interestRate=" + interestRate +
                '}';
    }
}
