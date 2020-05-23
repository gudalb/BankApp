package se.nackademin.bank.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByUserId(Long id);
    List<Loan> findByUser(User user);
    Loan getLoanById(Long id);

}
