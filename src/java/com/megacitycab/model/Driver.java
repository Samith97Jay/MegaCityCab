package com.megacitycab.model;


import java.util.Objects;


public class Driver {
 
    private final String driverId;
    private final String name;
    private final String lisce; 
    private final String phoneno;
    private final String address;
    private final String assignedVehicleId; 

    
    private Driver(Builder builder) {
        this.driverId = builder.driverId;
        this.name = builder.name;
        this.lisce = builder.lisce;
        this.phoneno = builder.phoneno;
        this.address = builder.address;
        this.assignedVehicleId = builder.assignedVehicleId;
    }


    public String getDriverId() {
        return driverId;
    }

    public String getName() {
        return name;
    }

    public String getLisce() {
        return lisce;
    }

    public String getPhoneno() {
        return phoneno;
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
                ", lisce='" + lisce + '\'' +
                ", phoneno='" + phoneno + '\'' +
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
        private String lisce;
        private String phoneno;
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

        public Builder lisce(String lisce) {
            this.lisce = lisce;
            return this;
        }

        public Builder phoneno(String phoneno) {
            this.phoneno = phoneno;
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
