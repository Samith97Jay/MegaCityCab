package com.megacitycab.service;

import com.megacitycab.dao.DBConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationService {
    private static AuthenticationService instance;

    private AuthenticationService() {}

    public static synchronized AuthenticationService getInstance() {
        if (instance == null) {
            instance = new AuthenticationService();
        }
        return instance;
    }

    public boolean authenticate(String username, String password) {
        // Trim input to remove accidental spaces
        username = username != null ? username.trim() : "";
        password = password != null ? password.trim() : "";

        boolean isAuthenticated = false;
        String sql = "SELECT password FROM users WHERE username = ?";

        System.out.println("AuthenticationService: Trying to authenticate user: '" + username + "'");

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            System.out.println("AuthenticationService: Executing query: " + stmt);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String storedPassword = rs.getString("password");
                    System.out.println("AuthenticationService: Retrieved stored password: '" + storedPassword + "'");
                    System.out.println("AuthenticationService: Provided password: '" + password + "'");
                    
                    // Compare the two passwords exactly.
                    if (storedPassword.equals(password)) {
                        isAuthenticated = true;
                        System.out.println("AuthenticationService: Authentication successful for user: '" + username + "'");
                    } else {
                        System.out.println("AuthenticationService: Password mismatch for user: '" + username + "'");
                    }
                } else {
                    System.out.println("AuthenticationService: No record found for user: '" + username + "'");
                }
            }
        } catch (SQLException ex) {
            System.err.println("AuthenticationService: Error during authentication: " + ex.getMessage());
            ex.printStackTrace();
        }
        return isAuthenticated;
    }
}
