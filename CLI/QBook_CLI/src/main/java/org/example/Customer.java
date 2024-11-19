package org.example;
public class Customer extends User {
    private Thread thread;

    public Customer(int userId, String username, String password, String role, String email) {
        super(userId, username, password, role, email);
    }


}