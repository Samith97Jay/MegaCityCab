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
    private final String vehicleRegId;    // Auto-generated unique registration ID
    private final String licensePlate;    // License plate number
    private final String model;           // Model of the vehicle
    private final String brand;           // Brand or manufacturer
    private final String color;           // Color of the vehicle
    private final int seatingCapacity;    // Number of seats available

    /**
     * Private constructor to enforce object creation via the Builder.
     *
     * @param builder the Builder instance containing vehicle data.
     */
    private Vehicle(Builder builder) {
        this.vehicleType = builder.vehicleType;
        this.vehicleRegId = builder.vehicleRegId;
        this.licensePlate = builder.licensePlate;
        this.model = builder.model;
        this.brand = builder.brand;
        this.color = builder.color;
        this.seatingCapacity = builder.seatingCapacity;
    }

    // Getters for all fields (no setters to preserve immutability)
    public String getVehicleType() {
        return vehicleType;
    }

    public String getVehicleRegId() {
        return vehicleRegId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleType='" + vehicleType + '\'' +
                ", vehicleRegId='" + vehicleRegId + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", seatingCapacity=" + seatingCapacity +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleRegId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vehicle)) return false;
        Vehicle other = (Vehicle) obj;
        return Objects.equals(this.vehicleRegId, other.vehicleRegId);
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
        private final String vehicleRegId;
        
        // Optional attributes
        private String licensePlate;
        private String model;
        private String brand;
        private String color;
        private int seatingCapacity;

        /**
         * Builder constructor with the required fields.
         *
         * @param vehicleType  the type of the vehicle (must not be null or empty)
         * @param vehicleRegId the auto-generated vehicle registration ID (must not be null or empty)
         */
        public Builder(String vehicleType, String vehicleRegId) {
            if (vehicleType == null || vehicleType.trim().isEmpty()) {
                throw new IllegalArgumentException("Vehicle type cannot be null or empty.");
            }
            if (vehicleRegId == null || vehicleRegId.trim().isEmpty()) {
                throw new IllegalArgumentException("Vehicle Registration ID cannot be null or empty.");
            }
            this.vehicleType = vehicleType;
            this.vehicleRegId = vehicleRegId;
        }

        public Builder licensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder color(String color) {
            this.color = color;
            return this;
        }

        public Builder seatingCapacity(int seatingCapacity) {
            this.seatingCapacity = seatingCapacity;
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
