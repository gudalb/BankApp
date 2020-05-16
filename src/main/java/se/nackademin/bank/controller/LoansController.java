package se.nackademin.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.nackademin.bank.persistence.Account;
import se.nackademin.bank.persistence.Loan;
import se.nackademin.bank.persistence.User;
import se.nackademin.bank.service.LoanService;
import se.nackademin.bank.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LoansController {

    LoanService loanService;
    UserService userService;

    @Autowired
    public LoansController(LoanService loanService, UserService userService) {
        this.loanService = loanService;
        this.userService = userService;
    }

    @RequestMapping("/loans/{username}/{password}")
    public List<Map<String,String>> getUserAccounts(@PathVariable String username, @PathVariable  String password) {

        List<User> userMatch = userService.getUserByUsernameAndPassword(username,password);
        User user = userMatch.get(0);
        List<Loan> loans = loanService.getLoanByUser(user);

        List<Map<String,String>> accountsMap = new ArrayList<>();

        for (Loan a: loans
        ) {
            Map<String,String> map = new HashMap<>();
            map.put("loanId", a.getId().toString());
            map.put("loanAmount", Double.toString(a.getAmount()));
            map.put("isApproved", Boolean.toString(a.isApproved()));
            accountsMap.add(map);
        }

        return accountsMap;
    }
}
