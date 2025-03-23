package com.megacitycab.service;

import com.megacitycab.dao.VehicleDAO;
import com.megacitycab.model.Vehicle;
import java.sql.SQLException;

/**
 * Service class for managing vehicle-related operations.
 */
public class VehicleService {
    
    private VehicleDAO vehicleDAO;

    public VehicleService() {
        vehicleDAO = new VehicleDAO();
    }

    /**
     * Registers a new vehicle.
     */
    public boolean registerVehicle(String vehicleId , String vehicleType, String lisce,
                                   String vehicleModel, String vehicleBrand, String color, int seat) throws ClassNotFoundException, SQLException {
        int result = vehicleDAO.insertVehicle(vehicleType, vehicleId, lisce, vehicleModel, vehicleBrand, color, seat);
        return result > 0;
    }

    /**
     * Retrieves a vehicle by vehicleId.
     */
    public Vehicle getVehicle(String vehicleId) {
        try {
            return vehicleDAO.getVehicle(vehicleId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Updates an existing vehicle.
     */
    public boolean updateVehicle(Vehicle vehicle) {
        try {
            return vehicleDAO.updateVehicle(vehicle);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a vehicle by vehicleId.
     */
    public boolean deleteVehicle(String vehicleId) {
        try {
            return vehicleDAO.deleteVehicle(vehicleId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves an available vehicle by vehicle type.
     */
    public Vehicle getAvailableVehicle(String vehicleType) {
        try {
            return vehicleDAO.getAvailableVehicle(vehicleType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieves an available, not booked vehicle by vehicle type.
     */
    public Vehicle getAvailableNotBookedVehicle(String vehicleType) {
        try {
            return vehicleDAO.getAvailableNotBookedVehicle(vehicleType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
