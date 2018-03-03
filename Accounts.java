package com.example.roy.to_do_list;

/**
 * Created by roy on 2/27/18.
 */

public class Accounts {
    public Accounts(String user, String pass, String em){
        username = user;
        password = pass;
        email = em;
    }

    public String get_username() { return username;}
    public String get_password() { return password;}
    public String get_email()    { return email;}

    private String username;
    private String password;
    private String email;
}
