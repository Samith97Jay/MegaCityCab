/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megacitycab.dao;

import com.megacitycab.model.Driver;
import com.megacitycab.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author OZT00090
 */
public class DriverDAO {
    // SQL query for inserting a new driver.
    private static final String INSERT_DRIVER_SQL = "INSERT INTO drivers (driverId, name, licenseNumber, phone, address, assignedCarId) VALUES (?, ?, ?, ?, ?, ?)";

    /**
     * Inserts a new driver into the database.
     *
     * @param driver the driver object to insert.
     * @return true if the driver was successfully inserted; false otherwise.
     */
    public boolean insertDriver(Driver driver) throws SQLException, ClassNotFoundException {
        boolean rowInserted = false;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_DRIVER_SQL)) {
            
            // Map the Driver object's properties to the SQL query.
            stmt.setString(1, driver.getDriverId());
            stmt.setString(2, driver.getName());
            stmt.setString(3, driver.getLicenseNumber());
            stmt.setString(4, driver.getPhone());
            stmt.setString(5, driver.getAddress());
            stmt.setString(6, driver.getAssignedCarId());
            
            rowInserted = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider logging the error and/or rethrowing as a custom exception.
        }
        return rowInserted;
    }
        /**
     * Retrieves a driver from the database based on driverId.
     */
    public Driver getDriver(String driverId) throws Exception {
        String sql = "SELECT driverId, name, licenseNumber, phone, address, assignedCarId FROM drivers WHERE driverId = ?";
        Driver driver = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, driverId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    driver = new Driver.Builder(rs.getString("driverId"))
                            .name(rs.getString("name"))
                            .licenseNumber(rs.getString("licenseNumber"))
                            .phone(rs.getString("phone"))
                            .address(rs.getString("address"))
                            .assignedCarId(rs.getString("assignedCarId"))
                            .build();
                }
            }
        }
        return driver;
    }
        /**
     * Updates an existing driver record.
     */
    public boolean updateDriver(Driver driver) throws Exception {
        String sql = "UPDATE drivers SET name = ?, licenseNumber = ?, phone = ?, address = ?, assignedCarId = ? WHERE driverId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, driver.getName());
            stmt.setString(2, driver.getLicenseNumber());
            stmt.setString(3, driver.getPhone());
            stmt.setString(4, driver.getAddress());
            stmt.setString(5, driver.getAssignedCarId());
            stmt.setString(6, driver.getDriverId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        }
    }
    
    
}
