package com.megacitycab.model;

import java.util.Date;
import java.util.Objects;

/**
 * Represents a cab booking in the Mega City Cab system.
 * This class is implemented using the Builder design pattern.
 */
public class Booking {
    private final String bookingNumber;
    private final String customerName;
    private final String customerAddress;
    private final String telephoneNumber;
    private final String destination;
    private final Date bookingDate;
    private final String customerRegNo;
    // New vehicle fields
    private final String vehicleType;
    private final String vehicleRegId;
    private final String vbrand;
    private final String vmodel;
    private final String vseating;

    private Booking(Builder builder) {
        this.bookingNumber = builder.bookingNumber;
        this.customerName = builder.customerName;
        this.customerAddress = builder.customerAddress;
        this.telephoneNumber = builder.telephoneNumber;
        this.destination = builder.destination;
        this.bookingDate = builder.bookingDate != null ? new Date(builder.bookingDate.getTime()) : null;
        this.customerRegNo = builder.customerRegNo;
        this.vehicleType = builder.vehicleType;
        this.vehicleRegId = builder.vehicleRegId;
        this.vbrand = builder.vbrand;
        this.vmodel = builder.vmodel;
        this.vseating = builder.vseating;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getDestination() {
        return destination;
    }

    public Date getBookingDate() {
        return bookingDate != null ? new Date(bookingDate.getTime()) : null;
    }
    
    public String getCustomerRegNo() {
        return customerRegNo;
    }
    
    // New getters for vehicle details
    public String getVehicleType() {
        return vehicleType;
    }

    public String getVehicleRegId() {
        return vehicleRegId;
    }

    public String getVbrand() {
        return vbrand;
    }

    public String getVmodel() {
        return vmodel;
    }

    public String getVseating() {
        return vseating;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingNumber='" + bookingNumber + '\'' +
                ", customerRegNo='" + customerRegNo + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", destination='" + destination + '\'' +
                ", bookingDate=" + bookingDate +
                ", vehicleType='" + vehicleType + '\'' +
                ", vehicleRegId='" + vehicleRegId + '\'' +
                ", vbrand='" + vbrand + '\'' +
                ", vmodel='" + vmodel + '\'' +
                ", vseating='" + vseating + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Booking)) return false;
        Booking other = (Booking) obj;
        return Objects.equals(bookingNumber, other.bookingNumber);
    }

    public static class Builder {
        private final String bookingNumber;
        private String customerName;
        private String customerAddress;
        private String telephoneNumber;
        private String destination;
        private Date bookingDate;
        private String customerRegNo;
        // New fields for vehicle details
        private String vehicleType;
        private String vehicleRegId;
        private String vbrand;
        private String vmodel;
        private String vseating;

        public Builder(String bookingNumber) {
            if (bookingNumber == null || bookingNumber.trim().isEmpty()) {
                throw new IllegalArgumentException("Booking number cannot be null or empty.");
            }
            this.bookingNumber = bookingNumber;
        }

        public Builder customerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public Builder customerAddress(String customerAddress) {
            this.customerAddress = customerAddress;
            return this;
        }

        public Builder telephoneNumber(String telephoneNumber) {
            this.telephoneNumber = telephoneNumber;
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
        
        public Builder customerRegNo(String customerRegNo) {
            this.customerRegNo = customerRegNo;
            return this;
        }

        // New builder methods for vehicle details
        public Builder vehicleType(String vehicleType) {
            this.vehicleType = vehicleType;
            return this;
        }

        public Builder vehicleRegId(String vehicleRegId) {
            this.vehicleRegId = vehicleRegId;
            return this;
        }

        public Builder vbrand(String vbrand) {
            this.vbrand = vbrand;
            return this;
        }

        public Builder vmodel(String vmodel) {
            this.vmodel = vmodel;
            return this;
        }

        public Builder vseating(String vseating) {
            this.vseating = vseating;
            return this;
        }

        public Booking build() {
            return new Booking(this);
        }
    }
}
