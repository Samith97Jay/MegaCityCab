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
    
      public int insertVehicle(String vehicleType, String vehicleId, String licensePlate,
                             String vehicleModel, String vehicleBrand, String color, int seat) throws SQLException, ClassNotFoundException {
        int rowsAffected = 0;
        String sql = "INSERT INTO vehicles (vehicleType, vehicleId, licensePlate, vehicleModel, vehicleBrand, color, seat) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, vehicleType);
            stmt.setString(2, vehicleId);
            stmt.setString(3, licensePlate);
            stmt.setString(4, vehicleModel);
            stmt.setString(5, vehicleBrand);
            stmt.setString(6, color);
            stmt.setInt(7, seat);
            
            rowsAffected = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Optionally log the exception or rethrow as a custom exception
        }
        return rowsAffected;
    }
    
    /**
     * Retrieves a vehicle from the database based on vehicleId.
     */
    public Vehicle getVehicle(String vehicleId) throws Exception {
        String sql = "SELECT vehicleType, vehicleId, licensePlate, vehicleModel, vehicleBrand, color, seat FROM vehicles WHERE vehicleId = ?";
        Vehicle vehicle = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehicleId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    vehicle = new Vehicle.Builder(rs.getString("vehicleType"), rs.getString("vehicleId"))
                            .licensePlate(rs.getString("licensePlate"))
                            .vehicleModel(rs.getString("vehicleModel"))
                            .vehicleBrand(rs.getString("vehicleBrand"))
                            .color(rs.getString("color"))
                            .seat(rs.getInt("seat"))
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
        String sql = "UPDATE vehicles SET vehicleType = ?, licensePlate = ?, vehicleModel = ?, vehicleBrand = ?, color = ?, seat = ? WHERE vehicleId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehicle.getVehicleType());
            stmt.setString(2, vehicle.getLicensePlate());
            stmt.setString(3, vehicle.getVehicleModel());
            stmt.setString(4, vehicle.getVehicleBrand());
            stmt.setString(5, vehicle.getColor());
            stmt.setInt(6, vehicle.getSeat());
            stmt.setString(7, vehicle.getVehicleId());

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
        String sql = "SELECT vehicleType, vehicleId, licensePlate, vehicleModel, vehicleBrand, color, seat "
                   + "FROM vehicles WHERE vehicleType = ? LIMIT 1";
        Vehicle vehicle = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, vehicleType);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    vehicle = new Vehicle.Builder(rs.getString("vehicleType"), rs.getString("vehicleId"))
                            .licensePlate(rs.getString("licensePlate"))
                            .vehicleModel(rs.getString("vehicleModel"))
                            .vehicleBrand(rs.getString("vehicleBrand"))
                            .color(rs.getString("color"))
                            .seat(rs.getInt("seat"))
                            .build();
                }
            }
        }
        return vehicle;
    
    }  

    /**
     * Retrieves an available vehicle for the given vehicle type that is not already booked.
     * The query excludes vehicles whose vehicleId exists in the bookings table.
     *
     * @param vehicleType The type of the vehicle.
     * @return A Vehicle object if found, otherwise null.
     * @throws Exception
     */
    public Vehicle getAvailableNotBookedVehicle(String vehicleType) throws Exception {
        String sql = "SELECT vehicleType, vehicleId, licensePlate, vehicleModel, vehicleBrand, color, seat " +
                     "FROM vehicles " +
                     "WHERE vehicleType = ? " +
                     "AND vehicleId NOT IN (SELECT vehicleId FROM bookings WHERE vehicleType = ?) " +
                     "LIMIT 1";
        Vehicle vehicle = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehicleType);
            stmt.setString(2, vehicleType);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    vehicle = new Vehicle.Builder(rs.getString("vehicleType"), rs.getString("vehicleId"))
                            .licensePlate(rs.getString("licensePlate"))
                            .vehicleModel(rs.getString("vehicleModel"))
                            .vehicleBrand(rs.getString("vehicleBrand"))
                            .color(rs.getString("color"))
                            .seat(rs.getInt("seat"))
                            .build();
                }
            }
        }
        return vehicle;
    }
    
}
