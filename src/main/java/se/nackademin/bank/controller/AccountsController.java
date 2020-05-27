package se.nackademin.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.nackademin.bank.persistence.Account;
import se.nackademin.bank.persistence.User;
import se.nackademin.bank.service.AccountService;
import se.nackademin.bank.service.UserService;

import java.util.*;

@RestController
public class AccountsController {

    private UserService userService;
    private AccountService accountService;

    @Autowired
    public AccountsController(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @RequestMapping("/accounts/all")
    public List<Account> getAllAccounts () {
        return accountService.getAllAccounts();
    }

    @RequestMapping("/accounts/{userId}")
    public List<Account> getUserAccounts(@PathVariable Long userId) {

        Optional<User> user = userService.findById(userId);

        if (user.isPresent())
            return accountService.getAccountByUser(user.get());

        return null;
    }

    @RequestMapping("/addaccount/{userId}")
    public void addUserAccount(@PathVariable Long userId) {
        Optional<User> user = userService.findById(userId);

        if(user.isPresent())
            accountService.saveAccount(new Account(user.get(), 15000, 5));
    }

    @RequestMapping("/account/{accountId}")
    public Account getAccount(@PathVariable Long accountId) {

        Optional<Account> account = accountService.getAccount(accountId);

        if (account.isPresent())
            return account.get();

        return null;
    }

    @RequestMapping("/accounts/setInterestRate/{accountId}/{interestRate}")
    public boolean setApproved (@PathVariable Long accountId, @PathVariable Double interestRate) {


        Account account = accountService.getAccountById(accountId);


        if (account != null) {
            account.setInterestRate(interestRate);
            accountService.saveAccount(account);
            return true;
        }

        return false;
    }


    @RequestMapping("/accounts/transferfunds/{fromAccountId}/{toAccountId}/{amount}")
    public boolean transferFunds (@PathVariable Long fromAccountId, @PathVariable Long toAccountId, @PathVariable Double amount) {

        Optional<Account> fromAccount = accountService.getAccount(fromAccountId);
        Optional<Account> toAccount = accountService.getAccount(toAccountId);


        if (fromAccount.isPresent() && toAccount.isPresent() && fromAccount.get().getBalance() >= amount) {
            fromAccount.get().setBalance(fromAccount.get().getBalance() - amount);
            toAccount.get().setBalance(toAccount.get().getBalance() + amount);
            accountService.saveAccount(fromAccount.get());
            accountService.saveAccount(toAccount.get());
            return true;
        }

        return false;
    }
}
