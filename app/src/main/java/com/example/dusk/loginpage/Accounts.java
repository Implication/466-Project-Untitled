package com.example.dusk.loginpage;

/**
 * Created by roy on 2/27/18.
 */

public class Accounts {
    private String username;
    private String password;
    private String email;

    public Accounts(String user, String pass, String em) {
        username = user;
        password = pass;
        email = em;
    }

    public String get_username() {
        return username;
    }

    public String get_password() {
        return password;
    }

    public String get_email() {
        return email;
    }
}
