package com.megacitycab.dao;

import com.megacitycab.model.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.megacitycab.util.DBConnection;

public class AdminDAO {

    public Admin getAdminByUsername(String uname) throws SQLException, ClassNotFoundException {
        Admin admin = null;
        String sql = "SELECT uname, pwd FROM users WHERE uname = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, uname);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String retrievedUsername = rs.getString("uname");
                    String retrievedPassword = rs.getString("pwd");
                    admin = new Admin(retrievedUsername, retrievedPassword);
                }
            }
        } catch (SQLException ex) {
            System.err.println("AdminDAO: Error retrieving admin - " + ex.getMessage());
            ex.printStackTrace();
        }
        return admin;
    }
}

