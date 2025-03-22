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

   
    
      public int insertVehicle(String vehicleType, String vehicleId, String lisce,
                             String vehicleModel, String vehicleBrand, String color, int seat) throws SQLException, ClassNotFoundException {
        int rowsAffected = 0;
        String sql = "INSERT INTO vehicles (vehicleType, vehicleId, lisce, vehicleModel, vehicleBrand, color, seat) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, vehicleType);
            stmt.setString(2, vehicleId);
            stmt.setString(3, lisce);
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
        String sql = "SELECT vehicleType, vehicleId, lisce, vehicleModel, vehicleBrand, color, seat,driverId FROM vehicles WHERE vehicleId = ?";
        Vehicle vehicle = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehicleId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    vehicle = new Vehicle.Builder(rs.getString("vehicleType"), rs.getString("vehicleId"))
                            .lisce(rs.getString("lisce"))
                            .vehicleModel(rs.getString("vehicleModel"))
                            .vehicleBrand(rs.getString("vehicleBrand"))
                            .color(rs.getString("color"))
                            .seat(rs.getInt("seat"))
                            .driverId(rs.getString("driverId"))
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
        String sql = "UPDATE vehicles SET vehicleType = ?, lisce = ?, vehicleModel = ?, vehicleBrand = ?, color = ?, seat = ?,driverId =? WHERE vehicleId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehicle.getVehicleType());
            stmt.setString(2, vehicle.getLisce());
            stmt.setString(3, vehicle.getVehicleModel());
            stmt.setString(4, vehicle.getVehicleBrand());
            stmt.setString(5, vehicle.getColor());
            stmt.setInt(6, vehicle.getSeat());
            stmt.setString(7, vehicle.getDriverId());
            stmt.setString(8, vehicle.getVehicleId());
           

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        }
    }
    public boolean deleteVehicle(String vehicleId) throws SQLException, ClassNotFoundException {
    String sql = "DELETE FROM vehicles WHERE vehicleId = ?";
    
    
  try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, vehicleId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Optionally log or rethrow as a custom exception
        }
        return false;
    }
    /**
     * Retrieves an available vehicle for the given vehicle type.
     *
     * @param vehicleType The type of the vehicle.
     * @return A Vehicle object if found, otherwise null.
     * @throws Exception
     */
    public Vehicle getAvailableVehicle(String vehicleType) throws Exception {
        String sql = "SELECT vehicleType, vehicleId, lisce, vehicleModel, vehicleBrand, color, seat, driverId "
                   + "FROM vehicles WHERE vehicleType = ? LIMIT 1";
        Vehicle vehicle = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, vehicleType);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    vehicle = new Vehicle.Builder(rs.getString("vehicleType"), rs.getString("vehicleId"))
                            .lisce(rs.getString("lisce"))
                            .vehicleModel(rs.getString("vehicleModel"))
                            .vehicleBrand(rs.getString("vehicleBrand"))
                            .color(rs.getString("color"))
                            .seat(rs.getInt("seat"))
                            .driverId(rs.getString("driverId"))
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
        String sql = "SELECT vehicleType, vehicleId, lisce, vehicleModel, vehicleBrand, color, seat, driverId " +
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
                    vehicle = new Vehicle.Builder(rs.getString("vehicleId"), rs.getString("vehicleType"))
                            .lisce(rs.getString("lisce"))
                            .vehicleModel(rs.getString("vehicleModel"))
                            .vehicleBrand(rs.getString("vehicleBrand"))
                            .color(rs.getString("color"))
                            .seat(rs.getInt("seat"))
                            .driverId(rs.getString("driverId"))
                            .build();
                }
            }
        }
        return vehicle;
    }
    
}
