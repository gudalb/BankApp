package se.nackademin.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.nackademin.bank.persistence.User;
import se.nackademin.bank.persistence.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserByUsernameAndPassword(String username, String password) {
        return userRepository.findUserByUsernameAndPassword(username, password);
    }

    public boolean existsUserByUsernameAndPassword(String username, String password) {
        return userRepository.existsUserByUsernameAndPassword(username, password);
    }

    public boolean existsUserByUsername(String username) {
        return userRepository.existsUserByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    public Optional<User> findById(Long id){ return userRepository.findById(id);}
}
