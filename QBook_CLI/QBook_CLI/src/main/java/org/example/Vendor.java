package org.example;

public class Vendor extends User{
    private Thread thread;

    public Vendor(int userId, String username, String password, String role, String email) {
        super(userId, username, password, role, email);
    }


}
