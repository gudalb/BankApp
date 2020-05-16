package se.nackademin.bank.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findUserByUsernameAndPassword(String username, String password);
    User getUserById(Long id);
    boolean existsUserByUsername(String username);
    boolean existsUserByUsernameAndPassword(String username, String password);
}
