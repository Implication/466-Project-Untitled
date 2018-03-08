package com.example.dusk.loginpage;

/**
 * Created by roy on 2/27/18.
 */

public class Accounts {
    private String username;
    private String password;
    private String email;
    private String fullname;

    public Accounts(Accounts acc){
        this.username = acc.username;
        this.password = acc.password;
        this.email = acc.email;
        this.fullname = acc.fullname;
    }

    public Accounts(String user, String pass, String em, String fn) {
        username = user;
        password = pass;
        email = em;
        fullname = fn;
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

    public String get_fullname(){return fullname; }

}
