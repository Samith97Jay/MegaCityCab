package com.megacitycab.model;


import java.util.Objects;


public class Driver {
 
    private final String driverId;
    private final String name;
    private final String licenseNumber; 
    private final String phone;
    private final String address;
    private final String assignedVehicleId; 

    
    private Driver(Builder builder) {
        this.driverId = builder.driverId;
        this.name = builder.name;
        this.licenseNumber = builder.licenseNumber;
        this.phone = builder.phone;
        this.address = builder.address;
        this.assignedVehicleId = builder.assignedVehicleId;
    }


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

    public String getAssignedVehicleId() {
        return assignedVehicleId;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "driverId='" + driverId + '\'' +
                ", name='" + name + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", assignedVehicleId='" + assignedVehicleId + '\'' +
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

   
    public static class Builder {

        private final String driverId;
      
        private String name;
        private String licenseNumber;
        private String phone;
        private String address;
        private String assignedVehicleId;

       
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

        public Builder assignedVehicleId(String assignedVehicleId) {
            this.assignedVehicleId = assignedVehicleId;
            return this;
        }

       
        public Driver build() {
            return new Driver(this);
        }
    }
}
