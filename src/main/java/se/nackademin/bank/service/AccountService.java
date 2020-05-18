package se.nackademin.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.nackademin.bank.persistence.Account;
import se.nackademin.bank.persistence.AccountRepository;
import se.nackademin.bank.persistence.Loan;
import se.nackademin.bank.persistence.User;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAccountByUserId(Long id) {
        return accountRepository.findByUserId(id);
    }

    public List<Account> getAccountByUser(User user) {
        return accountRepository.findByUser(user);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> getAccount(Long id) {
        return accountRepository.findById(id);
    }

    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    public Account getAccountById(Long id){
        return accountRepository.getAccountById(id);
    }
}
