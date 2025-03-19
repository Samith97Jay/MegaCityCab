package com.megacitycab.model;

import java.util.Objects;

/**
 * Represents a Customer in the Mega City Cab system.
 * <p>
 * This class is designed using the Builder pattern to enforce immutability and to provide a clear,
 * flexible construction process for Customer objects.
 */
public class Customer {

    private final String custId;
    private final String name;
    private final String address;
    private final String nic;        
    private final String phoneno;

    /**
     * Private constructor to enforce object creation via the Builder.
     *
     * @param builder the Builder instance containing customer data.
     */
    private Customer(Builder builder) {
        this.custId = builder.custId;
        this.name = builder.name;
        this.address = builder.address;
        this.nic = builder.nic;
        this.phoneno = builder.phoneno;
    }

    
  

    // Getters for all fields (no setters provided to maintain immutability)
    public String getCustId() {
        return custId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getNic() {
        return nic;
    }

    public String getPhoneno() {
        return phoneno;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId='" + custId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", nic='" + nic + '\'' +
                ", phoneno='" + phoneno + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(custId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Customer)) return false;
        Customer other = (Customer) obj;
        return Objects.equals(this.custId, other.custId);
    }


    /**
     * Builder class for constructing Customer instances.
     */
    public static class Builder {
        // Required attribute for a Customer.
        private final String custId;
        // Optional attributes.
        private String name;
        private String address;
        private String nic;
        private String phoneno;

        /**
         * Builder constructor with the required custId.
         *
         * @param custId the unique registration number of the customer.
         * @throws IllegalArgumentException if custId is null or empty.
         */
        public Builder(String custId) {
            if (custId == null || custId.trim().isEmpty()) {
                throw new IllegalArgumentException("Customer Id cannot be null or empty.");
            }
            this.custId = custId;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder nic(String nic) {
            this.nic = nic;
            return this;
        }

        public Builder phoneno(String phoneno) {
            this.phoneno = phoneno;
            return this;
        }

        /**
         * Builds and returns a new Customer instance.
         *
         * @return a Customer object constructed with the Builder's parameters.
         */
        public Customer build() {
            return new Customer(this);
        }
    }
}