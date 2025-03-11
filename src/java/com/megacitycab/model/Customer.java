package com.megacitycab.model;

import java.util.Objects;

/**
 * Represents a Customer in the Mega City Cab system.
 * <p>
 * This class is designed using the Builder pattern to enforce immutability and to provide a clear,
 * flexible construction process for Customer objects.
 */
public class Customer {
    // Required field: Unique customer registration number.
    private final String registrationNumber;
    // Optional fields capturing additional customer details.
    private final String name;
    private final String address;
    private final String nic;         // National Identity Card number
    private final String telephone;

    /**
     * Private constructor to enforce object creation via the Builder.
     *
     * @param builder the Builder instance containing customer data.
     */
    private Customer(Builder builder) {
        this.registrationNumber = builder.registrationNumber;
        this.name = builder.name;
        this.address = builder.address;
        this.nic = builder.nic;
        this.telephone = builder.telephone;
    }

    // Getters for all fields (no setters provided to maintain immutability)
    public String getRegistrationNumber() {
        return registrationNumber;
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

    public String getTelephone() {
        return telephone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", nic='" + nic + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Customer)) return false;
        Customer other = (Customer) obj;
        return Objects.equals(this.registrationNumber, other.registrationNumber);
    }

    /**
     * Builder class for constructing Customer instances.
     */
    public static class Builder {
        // Required attribute for a Customer.
        private final String registrationNumber;
        // Optional attributes.
        private String name;
        private String address;
        private String nic;
        private String telephone;

        /**
         * Builder constructor with the required registrationNumber.
         *
         * @param registrationNumber the unique registration number of the customer.
         * @throws IllegalArgumentException if registrationNumber is null or empty.
         */
        public Builder(String registrationNumber) {
            if (registrationNumber == null || registrationNumber.trim().isEmpty()) {
                throw new IllegalArgumentException("Registration number cannot be null or empty.");
            }
            this.registrationNumber = registrationNumber;
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

        public Builder telephone(String telephone) {
            this.telephone = telephone;
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
