package ru.stqa.pft.mantis.model;

public class User {

    private static String username;
    private static String email;
    private static String password;

    public static String getUsername() {
        return username;
    }
    public User withUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }
    public User withPassword(String password) {
        this.password = password;
        return this;
    }

    public static String getEmail() {
        return email;
    }
    public User withEmail(String email) {
        this.email = email;
        return this;
    }
}

