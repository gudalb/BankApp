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

    public Account() {
    }

    public Account(User user, double balance) {
        this.user = user;
        this.balance = balance;
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

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", user=" + user +
                ", balance=" + balance +
                '}';
    }
}
