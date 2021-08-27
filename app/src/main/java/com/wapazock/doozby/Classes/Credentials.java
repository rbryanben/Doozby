package com.wapazock.doozby.Classes;

public class Credentials {

    // Variables
    private String username, password ;

    //constructor
    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
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

}
