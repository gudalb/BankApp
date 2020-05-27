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

import java.util.*;

@RestController
public class LoansController {

    LoanService loanService;
    UserService userService;

    @Autowired
    public LoansController(LoanService loanService, UserService userService) {
        this.loanService = loanService;
        this.userService = userService;
    }

    @RequestMapping("/loans/{userId}")
    public List<Loan> getUserLoans(@PathVariable Long userId) {


        Optional<User> user = userService.findById(userId);

        if(user.isPresent())
            return loanService.getLoanByUser(user.get());
        return null;
    }


    @RequestMapping("/approvedloans")
    public List<Loan> getApprovedLoans() {


        List<Loan> loans = loanService.getAllLoans();
        List<Loan> approvedLoans = new ArrayList<>();
        for(Loan loan : loans) {
            if (loan.isApproved())
                approvedLoans.add(loan);
        }

        return  approvedLoans;
    }

    @RequestMapping("/unapprovedloans")
    public List<Loan> getUnapprovedLoans() {


        List<Loan> loans = loanService.getAllLoans();
        List<Loan> unapprovedLoans = new ArrayList<>();
        for(Loan loan : loans) {
            if (!loan.isApproved())
                unapprovedLoans.add(loan);
        }

        return  unapprovedLoans;
    }


    @RequestMapping("/loans/setApproved/{loanId}/{approved}")
    public boolean setApproved (@PathVariable Long loanId, @PathVariable Boolean approved) {


        Loan loan = loanService.getLoanById(loanId);


        if (loan != null) {
            loan.setApproved(approved);
            loanService.saveLoan(loan);
            return true;
            }

        return false;

    }

    @RequestMapping("/loans/apply/{userId}/{amount}")
    public boolean applyForLoan(@PathVariable Long userId, @PathVariable Double amount) {
        User user = userService.getUserById(userId);

        if (user != null) {
            Loan loan = new Loan(user,amount);
            loanService.saveLoan(loan);
            return true;
        }

        return false;
    }
}
