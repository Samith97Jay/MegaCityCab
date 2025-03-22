package com.megacitycab.model;

import java.util.Date;
import java.util.Objects;


public class Booking {
    private final String bookingId;
    private final String customerName;
    private final String customerAddress;
    private final String telephoneNo;
    private final String destination;
    private final Date bookingDate;

    public String getPickupLocation() {
        return pickupLocation;
    }
    private final String pickupLocation;
    private final String customerId;
    private final String vehicleType;
    private final String vehicleId;
    private final String vehicleBrand;
    private final String vehicleModel;
    private final String seat;



    public Booking(String bookingId, String customerName, String customerAddress, String telephoneNo, String destination, Date bookingDate, String pickupLocation, String customerId, String vehicleType, String vehicleId, String vehicleBrand, String vehicleModel, String seat) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.telephoneNo = telephoneNo;
        this.destination = destination;
        this.bookingDate = bookingDate;
        this.pickupLocation = pickupLocation;
        this.customerId = customerId;
        this.vehicleType = vehicleType;
        this.vehicleId = vehicleId;
        this.vehicleBrand = vehicleBrand;
        this.vehicleModel = vehicleModel;
        this.seat = seat;
    }


    private Booking(Builder builder) {
        this.bookingId = builder.bookingId;
        this.customerName = builder.customerName;
        this.customerAddress = builder.customerAddress;
        this.telephoneNo = builder.telephoneNo;
        this.destination = builder.destination;
        this.bookingDate = builder.bookingDate != null ? new Date(builder.bookingDate.getTime()) : null;
         this.pickupLocation = builder.pickupLocation;
        this.customerId = builder.customerId;
        this.vehicleType = builder.vehicleType;
        this.vehicleId = builder.vehicleId;
        this.vehicleBrand = builder.vehicleBrand;
        this.vehicleModel = builder.vehicleModel;
        this.seat = builder.seat;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getTelephoneNo() {
        return telephoneNo;
    }

    public String getDestination() {
        return destination;
    }

    public Date getBookingDate() {
        return bookingDate != null ? new Date(bookingDate.getTime()) : null;
    }
    
    public String getCustomerId() {
        return customerId;
    }
    
    // New getters for vehicle details
    public String getVehicleType() {
        return vehicleType;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public String getSeat() {
        return seat;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", telephoneNo='" + telephoneNo + '\'' +
                ", destination='" + destination + '\'' +
                ", bookingDate=" + bookingDate +
                ", pickupLocation=" + pickupLocation +
                ", vehicleType='" + vehicleType + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", vehicleBrand='" + vehicleBrand + '\'' +
                ", vehicleModel='" + vehicleModel + '\'' +
                ", seat='" + seat + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Booking)) return false;
        Booking other = (Booking) obj;
        return Objects.equals(bookingId, other.bookingId);
    }

    public static class Builder {
        private final String bookingId;
        private String customerName;
        private String customerAddress;
        private String telephoneNo;
        private String destination;
        private Date bookingDate;
        private String customerId;
        // New fields for vehicle details
        private String vehicleType;
        private String vehicleId;
        private String vehicleBrand;
        private String vehicleModel;
        private String seat;
        private String pickupLocation;

        public Builder(String bookingId) {
            if (bookingId == null || bookingId.trim().isEmpty()) {
                throw new IllegalArgumentException("Booking number cannot be null or empty.");
            }
            this.bookingId = bookingId;
        }

        public Builder customerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public Builder customerAddress(String customerAddress) {
            this.customerAddress = customerAddress;
            return this;
        }

        public Builder telephoneNo(String telephoneNo) {
            this.telephoneNo = telephoneNo;
            return this;
        }

        public Builder destination(String destination) {
            this.destination = destination;
            return this;
        }

        public Builder bookingDate(Date bookingDate) {
            this.bookingDate = bookingDate != null ? new Date(bookingDate.getTime()) : null;
            return this;
        }
        
             public Builder pickupLocation(String pickupLocation) {
            this.pickupLocation = pickupLocation;
            return this;
        }
        
        public Builder customerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        // New builder methods for vehicle details
        public Builder vehicleType(String vehicleType) {
            this.vehicleType = vehicleType;
            return this;
        }

        public Builder vehicleId(String vehicleId) {
            this.vehicleId = vehicleId;
            return this;
        }

        public Builder vehicleBrand(String vehicleBrand) {
            this.vehicleBrand = vehicleBrand;
            return this;
        }

        public Builder vehicleModel(String vehicleModel) {
            this.vehicleModel = vehicleModel;
            return this;
        }

        public Builder seat(String seat) {
            this.seat = seat;
            return this;
        }

        public Booking build() {
            return new Booking(this);
        }
    }
}
