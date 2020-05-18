package se.nackademin.bank;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import se.nackademin.bank.persistence.*;
import se.nackademin.bank.service.AccountService;
import se.nackademin.bank.service.LoanService;
import se.nackademin.bank.service.UserService;
import static se.nackademin.bank.LoggerBean.log2;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class BankApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankApplication.class, args);
    }

    @Bean
    public CommandLineRunner setup(AccountRepository accountRepository, AccountService accountService,
                                   LoanRepository loanRepository, LoanService loanService,
                                   UserRepository userRepository, UserService userService) {

        return args -> {
            userRepository.save(new User("abbe", "123", true));
            userRepository.save(new User("alf", "123", false));
            User user1 = userRepository.getUserById((long) 1);
            User user2 = userRepository.getUserById((long) 2);

            accountRepository.save(new Account(user1, 30000, 3));
            accountRepository.save(new Account(user2, 15000, 4));
            accountRepository.save(new Account(user1, 30000, 5));

            loanRepository.save(new Loan(user1, 30000.5, true ));
            loanRepository.save(new Loan(user1, 15000, false ));

            List<User> result = userService.getAllUsers();
            log2.info("" + result);
            result = userService.getUserByUsernameAndPassword("abbe", "123");
            log2.info("rätt pass " + result);
            result = userService.getUserByUsernameAndPassword("abbe", "321");
            log2.info("fel pass " + result);
            List<Account> result2 = accountService.getAccountByUser(user1);
            log2.info("accounts by user " + result2);
            boolean result3 = userService.existsUserByUsername("abbe");
            log2.info("user exists with username true " + result3);
            result3 = userService.existsUserByUsername("abwadawdbe");
            log2.info("user exists with username false " + result3);

        };

    }

}
