package se.nackademin.bank.model;

public class LoginInfo {
    String username;
    String password;
    boolean loginVerified;

    public LoginInfo(String username, String password, boolean loginVerified) {
        this.username = username;
        this.password = password;
        this.loginVerified = loginVerified;
    }
}
