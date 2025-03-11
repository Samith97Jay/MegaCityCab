package com.megacitycab.model;


import java.util.Objects;

/**
 * Represents a Driver in the Mega City Cab system.
 * <p>
 * This class is designed using the Builder pattern to enforce immutability and to provide a clear,
 * flexible construction process for Driver objects. It adheres to the Single Responsibility Principle,
 * focusing solely on driver-related data.
 */

public class Driver {
    // Required field: Unique identifier for the driver.
    private final String driverId;
    // Optional fields capturing additional driver details.
    private final String name;
    private final String licenseNumber; // Driver's license number.
    private final String phone;
    private final String address;
    private final String assignedCarId; // ID of the car assigned to the driver, if applicable.

    /**
     * Private constructor to enforce object creation via the Builder.
     *
     * @param builder the Builder instance containing driver data.
     */
    private Driver(Builder builder) {
        this.driverId = builder.driverId;
        this.name = builder.name;
        this.licenseNumber = builder.licenseNumber;
        this.phone = builder.phone;
        this.address = builder.address;
        this.assignedCarId = builder.assignedCarId;
    }

    // Getters for all fields (no setters provided to maintain immutability)
    public String getDriverId() {
        return driverId;
    }

    public String getName() {
        return name;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getAssignedCarId() {
        return assignedCarId;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "driverId='" + driverId + '\'' +
                ", name='" + name + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", assignedCarId='" + assignedCarId + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(driverId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Driver)) return false;
        Driver other = (Driver) obj;
        return Objects.equals(driverId, other.driverId);
    }

    /**
     * Builder class for constructing Driver instances.
     * <p>
     * This Builder follows the Builder design pattern, providing a flexible and readable
     * approach to constructing immutable Driver objects.
     */
    public static class Builder {
        // Required attribute for a Driver.
        private final String driverId;
        // Optional attributes.
        private String name;
        private String licenseNumber;
        private String phone;
        private String address;
        private String assignedCarId;

        /**
         * Builder constructor with the required driverId.
         *
         * @param driverId the unique identifier for the driver.
         * @throws IllegalArgumentException if driverId is null or empty.
         */
        public Builder(String driverId) {
            if (driverId == null || driverId.trim().isEmpty()) {
                throw new IllegalArgumentException("Driver ID cannot be null or empty.");
            }
            this.driverId = driverId;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder licenseNumber(String licenseNumber) {
            this.licenseNumber = licenseNumber;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder assignedCarId(String assignedCarId) {
            this.assignedCarId = assignedCarId;
            return this;
        }

        /**
         * Builds the Driver instance with the provided values.
         *
         * @return a new Driver object.
         */
        public Driver build() {
            return new Driver(this);
        }
    }
}
