package se.nackademin.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.nackademin.bank.persistence.Account;
import se.nackademin.bank.persistence.User;
import se.nackademin.bank.service.AccountService;
import se.nackademin.bank.service.LoanService;
import se.nackademin.bank.service.UserService;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/allusers")
    public List<User> getUserList(){
        return userService.getAllUsers();
    }

    @RequestMapping("/registeruser/{username}/{password}")
    public boolean addUser(@PathVariable String username, @PathVariable String password) {
        boolean usernameExists = userService.existsUserByUsername(username);
        if(!usernameExists) {
            User user = new User(username, password, false);
            userService.saveUser(user);
            return true;
        }
        return false;
    }

    @RequestMapping("/usernameexists/{username}")
    public boolean existsUserByUsername(@PathVariable String username) {
        return userService.existsUserByUsername(username);
    }
}
