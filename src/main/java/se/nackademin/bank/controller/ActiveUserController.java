package se.nackademin.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;
import se.nackademin.bank.persistence.User;
import se.nackademin.bank.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ActiveUserController {
    User activeUser;
    UserService userService;

    @Autowired
    public ActiveUserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/setactiveuser/{username}/{password}")
    public void setActiveUser(@PathVariable String username, @PathVariable  String password) {
        this.activeUser = userService.getUserByUsernameAndPassword(username, password).get(0);
    }

    @RequestMapping("/getactiveuser")
    public Map<String,String> getActiveUser() {

        if(activeUser != null) {
            Map<String, String> map = new HashMap<>();
            map.put("username", activeUser.getUsername());
            map.put("password", activeUser.getPassword());
            map.put("userId", activeUser.getId().toString());
            map.put("isAdmin", Boolean.toString(activeUser.isAdmin()));
            return map;
        } else
            return null;
    }

    @RequestMapping("/resetactiveuser")
    public void resetActiveUser() {
        this.activeUser = null;
    }

}
