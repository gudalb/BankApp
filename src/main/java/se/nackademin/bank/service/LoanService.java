package se.nackademin.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.nackademin.bank.persistence.Loan;
import se.nackademin.bank.persistence.LoanRepository;
import se.nackademin.bank.persistence.User;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    private LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<Loan> findLoanByUserId(Long id) {
        return loanRepository.findByUserId(id);
    }

    public List<Loan> findLoanByUser(User user) {
        return loanRepository.findByUser(user);
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public List<Loan> getLoanByUser(User user) {
        return loanRepository.findByUser(user);
    }

    public Optional<Loan> getLoan(Long id) {
        return loanRepository.findById(id);
    }

    public Loan getLoanById(Long id) {
        return loanRepository.getLoanById(id);
    }

    public void saveLoan(Loan loan) {
        loanRepository.save(loan);
    }




}
