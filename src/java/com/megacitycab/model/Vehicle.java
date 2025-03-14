/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megacitycab.model;

import java.util.Objects;

/**
 *
 * @author OZT00090
 */
public class Vehicle {
    
     private final String vehicleType;     // Type of the vehicle (Vehicle, SUV, Van, Bus)
    private final String vehicleId;    // Auto-generated unique registration ID
    private final String licensePlate;    // License plate number
    private final String vehicleModel;           // Model of the vehicle
    private final String vehicleBrand;           // Brand or manufacturer
    private final String color;           // Color of the vehicle
    private final int seat;    // Number of seats available

    /**
     * Private constructor to enforce object creation via the Builder.
     *
     * @param builder the Builder instance containing vehicle data.
     */
    private Vehicle(Builder builder) {
        this.vehicleType = builder.vehicleType;
        this.vehicleId = builder.vehicleId;
        this.licensePlate = builder.licensePlate;
        this.vehicleModel = builder.vehicleModel;
        this.vehicleBrand = builder.vehicleBrand;
        this.color = builder.color;
        this.seat = builder.seat;
    }

    // Getters for all fields (no setters to preserve immutability)
    public String getVehicleType() {
        return vehicleType;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public String getColor() {
        return color;
    }

    public int getSeat() {
        return seat;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleType='" + vehicleType + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", vehicleModel='" + vehicleModel + '\'' +
                ", vehicleBrand='" + vehicleBrand + '\'' +
                ", color='" + color + '\'' +
                ", seat=" + seat +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vehicle)) return false;
        Vehicle other = (Vehicle) obj;
        return Objects.equals(this.vehicleId, other.vehicleId);
    }

    /**
     * Builder class for constructing Vehicle instances.
     * 
     * This follows the Builder design pattern, providing a clear and flexible
     * approach to constructing immutable Vehicle objects.
     */
    public static class Builder {
        // Required attributes
        private final String vehicleType;
        private final String vehicleId;
        
        // Optional attributes
        private String licensePlate;
        private String vehicleModel;
        private String vehicleBrand;
        private String color;
        private int seat;

        /**
         * Builder constructor with the required fields.
         *
         * @param vehicleType  the type of the vehicle (must not be null or empty)
         * @param vehicleId the auto-generated vehicle registration ID (must not be null or empty)
         */
        public Builder(String vehicleType, String vehicleId) {
            if (vehicleType == null || vehicleType.trim().isEmpty()) {
                throw new IllegalArgumentException("Vehicle type cannot be null or empty.");
            }
            if (vehicleId == null || vehicleId.trim().isEmpty()) {
                throw new IllegalArgumentException("Vehicle Registration ID cannot be null or empty.");
            }
            this.vehicleType = vehicleType;
            this.vehicleId = vehicleId;
        }

        public Builder licensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public Builder vehicleModel(String vehicleModel) {
            this.vehicleModel = vehicleModel;
            return this;
        }

        public Builder vehicleBrand(String vehicleBrand) {
            this.vehicleBrand = vehicleBrand;
            return this;
        }

        public Builder color(String color) {
            this.color = color;
            return this;
        }

        public Builder seat(int seat) {
            this.seat = seat;
            return this;
        }

        /**
         * Builds the Vehicle instance with the provided values.
         *
         * @return a new Vehicle object.
         */
        public Vehicle build() {
            return new Vehicle(this);
        }
    }
    
}
