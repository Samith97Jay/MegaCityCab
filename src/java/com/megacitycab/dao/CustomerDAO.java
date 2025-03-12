/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megacitycab.dao;

import com.megacitycab.model.Customer;
import com.megacitycab.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author OZT00090
 */
public class CustomerDAO {
    
    private static CustomerDAO instance;

    // Private constructor to enforce Singleton pattern
    private CustomerDAO() {}

    /**
     * Returns the singleton instance of CustomerDAO.
     *
     * @return the singleton instance.
     */
    public static synchronized CustomerDAO getInstance() {
        if (instance == null) {
            instance = new CustomerDAO();
        }
        return instance;
    }

    /**
     * Adds a new customer record to the database.
     *
     * @param customer the Customer object containing customer details.
     * @return true if the customer is added successfully; false otherwise.
     * @throws SQLException if a database access error occurs.
     */
    public boolean addCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO customers (registrationNumber, name, address, nic, telephone) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getRegistrationNumber());
            stmt.setString(2, customer.getName());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getNic());
            stmt.setString(5, customer.getTelephone());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        }
    }

    /**
     * Retrieves a customer from the database based on the registration number.
     *
     * @param registrationNumber the unique identifier for the customer.
     * @return the Customer object if found; otherwise, null.
     * @throws SQLException if a database access error occurs.
     */
    public Customer getCustomer(String registrationNumber) throws SQLException, ClassNotFoundException {
        String sql = "SELECT registrationNumber, name, address, nic, telephone FROM customers WHERE registrationNumber = ?";
        Customer customer = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, registrationNumber);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String regNumber = rs.getString("registrationNumber");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String nic = rs.getString("nic");
                    String telephone = rs.getString("telephone");

                    customer = new Customer.Builder(regNumber)
                            .name(name)
                            .address(address)
                            .nic(nic)
                            .telephone(telephone)
                            .build();
                }
            }
        }
        return customer;
    }

    /**
     * Retrieves a customer from the database based on NIC.
     *
     * @param nic the NIC number of the customer.
     * @return the Customer object if found; otherwise, null.
     * @throws SQLException if a database access error occurs.
     */
    public Customer getCustomerByNic(String nic) throws SQLException, ClassNotFoundException {
        String sql = "SELECT registrationNumber, name, address, nic, telephone FROM customers WHERE nic = ?";
        Customer customer = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nic);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String regNumber = rs.getString("registrationNumber");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String retrievedNic = rs.getString("nic");
                    String telephone = rs.getString("telephone");

                    customer = new Customer.Builder(regNumber)
                            .name(name)
                            .address(address)
                            .nic(retrievedNic)
                            .telephone(telephone)
                            .build();
                }
            }
        }
        return customer;
    }

    /**
     * Retrieves a customer from the database based on either the registration number or NIC.
     *
     * @param input the registration number (e.g., "CUS_88021") or NIC (e.g., "78454112").
     * @return the Customer object if found; otherwise, null.
     * @throws SQLException if a database access error occurs.
     */
    public Customer getCustomerByRegOrNic(String input) throws SQLException, ClassNotFoundException {
        String sql = "SELECT registrationNumber, name, address, nic, telephone FROM customers " +
                     "WHERE registrationNumber = ? OR nic = ? LIMIT 1";
        Customer customer = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, input);
            stmt.setString(2, input);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String regNumber = rs.getString("registrationNumber");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String retrievedNic = rs.getString("nic");
                    String telephone = rs.getString("telephone");

                    customer = new Customer.Builder(regNumber)
                            .name(name)
                            .address(address)
                            .nic(retrievedNic)
                            .telephone(telephone)
                            .build();
                }
            }
        }
        return customer;
    }

    /**
     * Updates an existing customer record.
     *
     * @param customer the Customer object with updated details.
     * @return true if the update was successful; false otherwise.
     * @throws Exception if a database access error occurs.
     */
    public boolean updateCustomer(Customer customer) throws Exception {
        String sql = "UPDATE customers SET name = ?, address = ?, nic = ?, telephone = ? WHERE registrationNumber = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getAddress());
            stmt.setString(3, customer.getNic());
            stmt.setString(4, customer.getTelephone());
            stmt.setString(5, customer.getRegistrationNumber());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        }
    }
    
}
