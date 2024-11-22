package org.example;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.sql.*;
import java.util.Base64;

public class UserManager {
    private static final String ALGORITHM = "AES";
    private static final byte[] FIXED_KEY = "YourSecureKey123".getBytes();

    private static Cipher cipher;

    static {
        try {
            cipher = Cipher.getInstance("AES");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize Cipher");
        }
    }

    private static SecretKey getSecretKey() {
        return new SecretKeySpec(FIXED_KEY, ALGORITHM);
    }

    public boolean register_user(int user_id, String name, String username, String password, String email, String role, Date created_at)
            throws Exception {

        String sql = "INSERT INTO users (user_id, name, username, password, email, role, created_at) VALUES (?, ?, ?, ?, ?, ?, ?)";



        if (!role.equals("customer") && !role.equals("vendor")) {
            System.out.println("Invalid role. Please use 'Customer' or 'Vendor'.");
            return false;
        }

        String encryptedPassword = encrypt(password);

        try (Connection connection = DatabaseConnectivity.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user_id);
            statement.setString(2, name);
            statement.setString(3, username);
            statement.setString(4, encryptedPassword);
            statement.setString(5, email);
            statement.setString(6, role);
            statement.setDate(7, created_at);

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

    public static String encrypt(String plainPassword)
            throws Exception {
        byte[] plainTextByte = plainPassword.getBytes();
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey());
        byte[] encryptedByte = cipher.doFinal(plainTextByte);
        Base64.Encoder encoder = Base64.getEncoder();
        String encryptedText = encoder.encodeToString(encryptedByte);
        return encryptedText;
    }

    public static String decrypt(String encryptedText)
            throws Exception {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] encryptedTextByte = decoder.decode(encryptedText);
        cipher.init(Cipher.DECRYPT_MODE, getSecretKey());
        byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
        String decryptedText = new String(decryptedByte);
        return decryptedText;
    }


    public boolean authenticateUser(String username, String password) throws Exception{

        String query = "SELECT password FROM users WHERE username = ?";

        try (Connection connection = DatabaseConnectivity.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");

                String decryptPassword = decrypt(storedPassword);

                if (decryptPassword.equals(password)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public String getName(String username){
        String query = "SELECT name FROM users WHERE username = ?";
        String name = null;

        try (Connection connection = DatabaseConnectivity.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                name = resultSet.getString("name");
                return name;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error: Can not find the name";
    }

}