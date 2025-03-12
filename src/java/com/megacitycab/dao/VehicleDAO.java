/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megacitycab.dao;

import com.megacitycab.model.Vehicle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.megacitycab.util.DBConnection;

/**
 *
 * @author OZT00090
 */
public class VehicleDAO {
    
      public int insertVehicle(String vehicleType, String vehicleRegId, String licensePlate,
                             String model, String brand, String color, int seatingCapacity) throws SQLException, ClassNotFoundException {
        int rowsAffected = 0;
        String sql = "INSERT INTO vehicles (vehicleType, vehicleRegId, licensePlate, model, brand, color, seatingCapacity) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, vehicleType);
            stmt.setString(2, vehicleRegId);
            stmt.setString(3, licensePlate);
            stmt.setString(4, model);
            stmt.setString(5, brand);
            stmt.setString(6, color);
            stmt.setInt(7, seatingCapacity);
            
            rowsAffected = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Optionally log the exception or rethrow as a custom exception
        }
        return rowsAffected;
    }
    
    /**
     * Retrieves a vehicle from the database based on vehicleRegId.
     */
    public Vehicle getVehicle(String vehicleRegId) throws Exception {
        String sql = "SELECT vehicleType, vehicleRegId, licensePlate, model, brand, color, seatingCapacity FROM vehicles WHERE vehicleRegId = ?";
        Vehicle vehicle = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehicleRegId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    vehicle = new Vehicle.Builder(rs.getString("vehicleType"), rs.getString("vehicleRegId"))
                            .licensePlate(rs.getString("licensePlate"))
                            .model(rs.getString("model"))
                            .brand(rs.getString("brand"))
                            .color(rs.getString("color"))
                            .seatingCapacity(rs.getInt("seatingCapacity"))
                            .build();
                }
            }
        }
        return vehicle;
    }

    /**
     * Updates an existing vehicle record.
     */
    public boolean updateVehicle(Vehicle vehicle) throws Exception {
        String sql = "UPDATE vehicles SET vehicleType = ?, licensePlate = ?, model = ?, brand = ?, color = ?, seatingCapacity = ? WHERE vehicleRegId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehicle.getVehicleType());
            stmt.setString(2, vehicle.getLicensePlate());
            stmt.setString(3, vehicle.getModel());
            stmt.setString(4, vehicle.getBrand());
            stmt.setString(5, vehicle.getColor());
            stmt.setInt(6, vehicle.getSeatingCapacity());
            stmt.setString(7, vehicle.getVehicleRegId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        }
    }
    
    /**
     * Retrieves an available vehicle for the given vehicle type.
     *
     * @param vehicleType The type of the vehicle.
     * @return A Vehicle object if found, otherwise null.
     * @throws Exception
     */
    public Vehicle getAvailableVehicle(String vehicleType) throws Exception {
        String sql = "SELECT vehicleType, vehicleRegId, licensePlate, model, brand, color, seatingCapacity "
                   + "FROM vehicles WHERE vehicleType = ? LIMIT 1";
        Vehicle vehicle = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, vehicleType);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    vehicle = new Vehicle.Builder(rs.getString("vehicleType"), rs.getString("vehicleRegId"))
                            .licensePlate(rs.getString("licensePlate"))
                            .model(rs.getString("model"))
                            .brand(rs.getString("brand"))
                            .color(rs.getString("color"))
                            .seatingCapacity(rs.getInt("seatingCapacity"))
                            .build();
                }
            }
        }
        return vehicle;
    
    }  

    /**
     * Retrieves an available vehicle for the given vehicle type that is not already booked.
     * The query excludes vehicles whose vehicleRegId exists in the bookings table.
     *
     * @param vehicleType The type of the vehicle.
     * @return A Vehicle object if found, otherwise null.
     * @throws Exception
     */
    public Vehicle getAvailableNotBookedVehicle(String vehicleType) throws Exception {
        String sql = "SELECT vehicleType, vehicleRegId, licensePlate, model, brand, color, seatingCapacity " +
                     "FROM vehicles " +
                     "WHERE vehicleType = ? " +
                     "AND vehicleRegId NOT IN (SELECT vehicleRegId FROM bookings WHERE vehicleType = ?) " +
                     "LIMIT 1";
        Vehicle vehicle = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehicleType);
            stmt.setString(2, vehicleType);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    vehicle = new Vehicle.Builder(rs.getString("vehicleType"), rs.getString("vehicleRegId"))
                            .licensePlate(rs.getString("licensePlate"))
                            .model(rs.getString("model"))
                            .brand(rs.getString("brand"))
                            .color(rs.getString("color"))
                            .seatingCapacity(rs.getInt("seatingCapacity"))
                            .build();
                }
            }
        }
        return vehicle;
    }
    
}
