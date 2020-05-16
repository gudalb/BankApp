package se.nackademin.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.nackademin.bank.persistence.Account;
import se.nackademin.bank.persistence.User;
import se.nackademin.bank.service.AccountService;
import se.nackademin.bank.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AccountsController {

    private UserService userService;
    private AccountService accountService;

    @Autowired
    public AccountsController(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @RequestMapping("/accounts/{username}/{password}")
    public List<Map<String,String>> getUserAccounts(@PathVariable String username, @PathVariable  String password) {

        List<User> userMatch = userService.getUserByUsernameAndPassword(username,password);
        User user = userMatch.get(0);
        List<Account> accounts = accountService.getAccountByUser(user);

        List<Map<String,String>> accountsMap = new ArrayList<>();

        for (Account a: accounts
             ) {
            Map<String,String> map = new HashMap<>();
            map.put("accountId", a.getId().toString());
            map.put("accountBalance", Double.toString(a.getBalance()));
            accountsMap.add(map);
        }

        return accountsMap;
    }
}
