/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megacitycab.service;

import com.megacitycab.dao.CustomerDAO;
import com.megacitycab.model.Customer;
import java.sql.SQLException;

/**
 *
 * @author OZT00090
 */
public class CustomerService {
    private CustomerDAO customerDAO;

    public CustomerService() {
        this.customerDAO = CustomerDAO.getInstance();
    }

    /**
     * Adds a new customer to the system.
     *
     * @param customer the Customer object containing registration details.
     * @return true if added successfully; false otherwise.
     * @throws SQLException if a database error occurs.
     */
    public boolean addCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        return customerDAO.addCustomer(customer);
    }

    /**
     * Retrieves a customer by registration number.
     *
     * @param registrationNumber the unique registration number.
     * @return the Customer object if found; otherwise, null.
     * @throws SQLException if a database error occurs.
     */
    public Customer getCustomer(String registrationNumber) throws SQLException, ClassNotFoundException {
        return customerDAO.getCustomer(registrationNumber);
    }

    /**
     * Retrieves a customer based on NIC.
     *
     * @param nic the NIC number of the customer.
     * @return the Customer object if found; otherwise, null.
     * @throws SQLException if a database error occurs.
     */
    public Customer getCustomerByNic(String nic) throws SQLException, ClassNotFoundException {
        return customerDAO.getCustomerByNic(nic);
    }
    
    /**
     * Updates an existing customer record.
     *
     * @param customer the Customer object with updated details.
     * @return true if the update was successful; false otherwise.
     * @throws Exception if a database error occurs.
     */
    public boolean updateCustomer(Customer customer) throws Exception {
        return customerDAO.updateCustomer(customer);
    }
    
    
    public Customer getCustomerByRegOrNic(String input) throws SQLException, ClassNotFoundException {
    return CustomerDAO.getInstance().getCustomerByRegOrNic(input);
}
}
