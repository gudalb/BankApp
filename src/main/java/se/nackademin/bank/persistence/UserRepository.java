package se.nackademin.bank.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsernameAndPassword(String username, String password);
    User getUserById(Long id);

    boolean existsUserByUsername(String username);
    boolean existsUserByUsernameAndPassword(String username, String password);
}
