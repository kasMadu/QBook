package org.example;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.sql.*;
import java.util.Base64;

public class UserManager {
    static Cipher cipher;
    public boolean register_user(int user_id, String username, String password, String email, String role, Date created_at)
            throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();

        cipher = Cipher.getInstance("AES");

        String sql = "INSERT INTO user (user_id, username, password, email, role, created_at) VALUES (?, ?, ?, ?, ?, ?)";

        if (!role.equals("Customer") && !role.equals("Vendor")) {
            System.out.println("Invalid role. Please use 'Customer' or 'Vendor'.");
            return false;
        }

        String encryptedPassword = encrypt(password, secretKey);

        try (Connection connection = DatabaseConnectivity.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user_id);
            statement.setString(2, username);
            statement.setString(3, encryptedPassword);
            statement.setString(4, email);
            statement.setString(5, role);
            statement.setDate(6, created_at);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("User registered successfully!");
                return true;
            } else {
                System.out.println("User registration failed.");
                return false;
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.out.println("Username already exists. Please choose another one.");
            }
            else {
                e.printStackTrace();
            }
            return false;
        }
    }

    public static String encrypt(String plainPassword, SecretKey secretKey)
            throws Exception {
        byte[] plainTextByte = plainPassword.getBytes();
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedByte = cipher.doFinal(plainTextByte);
        Base64.Encoder encoder = Base64.getEncoder();
        String encryptedText = encoder.encodeToString(encryptedByte);
        return encryptedText;
    }

    public boolean authenticateUser(String username, String password) {
        String query = "SELECT password FROM user WHERE username = ?";

        try (Connection connection = DatabaseConnectivity.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");

                if (storedPassword.equals(password)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}