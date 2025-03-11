package com.megacitycab.service;

import com.megacitycab.dao.CarRegistrationDAO;
import com.megacitycab.model.Car;

public class CarRegistrationService {

    private CarRegistrationDAO dao;

    public CarRegistrationService() {
        dao = new CarRegistrationDAO();
    }

    /**
     * Registers a new vehicle.
     *
     * @param vehicleType     The type of the vehicle.
     * @param vehicleRegId    The vehicle registration ID.
     * @param licensePlate    The license plate.
     * @param model           The model of the vehicle.
     * @param brand           The brand of the vehicle.
     * @param color           The color of the vehicle.
     * @param seatingCapacity The seating capacity.
     * @return true if the vehicle was registered successfully.
     */
    public boolean registerVehicle(String vehicleType, String vehicleRegId, String licensePlate,
                                   String model, String brand, String color, int seatingCapacity) {
        int result = dao.insertVehicle(vehicleType, vehicleRegId, licensePlate, model, brand, color, seatingCapacity);
        return result > 0;
    }

    /**
     * Retrieves an available vehicle based on the provided vehicle type.
     *
     * @param vehicleType The type of the vehicle to search for.
     * @return A Car object if found, otherwise null.
     */
    public Car getAvailableVehicle(String vehicleType) {
        try {
            return dao.getAvailableVehicle(vehicleType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Retrieves an available vehicle based on the provided vehicle type,
     * ensuring that the vehicle is not already booked.
     *
     * @param vehicleType The type of the vehicle to search for.
     * @return A Car object if an available (not booked) vehicle is found; otherwise, null.
     */
    public Car getAvailableNotBookedVehicle(String vehicleType) {
        try {
            return dao.getAvailableNotBookedVehicle(vehicleType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
