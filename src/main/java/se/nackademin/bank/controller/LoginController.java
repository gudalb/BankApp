package se.nackademin.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.nackademin.bank.model.LoginInfo;
import se.nackademin.bank.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login/{username}/{password}")
    public Map<String,String> verifyLogin(@PathVariable String username, @PathVariable  String password) {

        HashMap<String,String> map = new HashMap<>();
        boolean loginVerified = userService.existsUserByUsernameAndPassword(username, password);

        map.put("username", username);
        map.put("password", password);
        map.put("isVerified", Boolean.toString(loginVerified));

        return map;
    }

}
