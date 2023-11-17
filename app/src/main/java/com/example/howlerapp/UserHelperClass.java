package com.example.howlerapp;

public class UserHelperClass {

    String names, usernames, emails, passwords;


    public UserHelperClass() {

    }

    public UserHelperClass(String name, String username, String email, String password) {
        this.names = name;
        this.usernames = username;
        this.emails = email;
        this.passwords = password;
    }

    public String getName() {
        return names;
    }

    public void setName(String name) {
        this.names = name;
    }

    public String getUsername() {
        return usernames;
    }

    public void setUsername(String username) {
        this.usernames = username;
    }

    public String getEmail() {
        return emails;
    }

    public void setEmail(String email) {
        this.emails = email;
    }

    public String getPassword() {
        return passwords;
    }

    public void setPassword(String password) {
        this.passwords = password;
    }
}
