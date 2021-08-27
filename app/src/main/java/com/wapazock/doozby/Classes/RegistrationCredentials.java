package com.wapazock.doozby.Classes;

public class RegistrationCredentials {

    // Variables
    private String username, password, email ;

    //constructor
    public RegistrationCredentials(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Methods
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
