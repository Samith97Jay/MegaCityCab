package com.megacitycab.service;

import com.megacitycab.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthService {
    private static AuthService instance;

    
    private AuthService() {}

    public static synchronized AuthService getInstance() {
        if (instance == null) {
            instance = new AuthService();
        }
        return instance;
    }

    public boolean authenticate(String uname, String pwd) throws SQLException, ClassNotFoundException {
        // Trim input to remove accidental spaces
        uname = uname != null ? uname.trim() : "";
        pwd = pwd != null ? pwd.trim() : "";

        boolean isAuthenticated = false;
        String sql = "SELECT pwd FROM users WHERE uname = ?";

        System.out.println("AuthenticationService: Trying to authenticate user: '" + uname + "'");

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, uname);
            System.out.println("AuthenticationService: Executing query: " + stmt);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String storedPassword = rs.getString("pwd");
                    System.out.println("AuthenticationService: Retrieved stored pwd: '" + storedPassword + "'");
                    System.out.println("AuthenticationService: Provided pwd: '" + pwd + "'");
                    
                    // Compare the two pwds exactly.
                    if (storedPassword.equals(pwd)) {
                        isAuthenticated = true;
                        System.out.println("AuthenticationService: Authentication successful for user: '" + uname + "'");
                    } else {
                        System.out.println("AuthenticationService: Password mismatch for user: '" + uname + "'");
                    }
                } else {
                    System.out.println("AuthenticationService: No record found for user: '" + uname + "'");
                }
            }
        } catch (SQLException ex) {
            System.err.println("AuthenticationService: Error during authentication: " + ex.getMessage());
            ex.printStackTrace();
        }
        return isAuthenticated;
    }
}
