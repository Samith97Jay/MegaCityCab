package com.megacitycab.service;

import com.megacitycab.dao.CustomerDAO;
import com.megacitycab.model.Customer;
import java.sql.SQLException;
import java.util.List;


public class CustomerService {

private final CustomerDAO customerDAO;

    /**
     * Default constructor using Singleton DAO instance.
     */
    public CustomerService() {
        this.customerDAO = CustomerDAO.getInstance();
    }

    /**
     * Constructor for Dependency Injection (Optional).
     * 
     * @param customerDAO DAO instance for handling customer data.
     */
    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    /**
     * Inserts a new customer into the database.
     *
     * @param customer the Customer object to insert.
     * @return true if successfully inserted, false otherwise.
     * @throws SQLException, ClassNotFoundException if a database error occurs.
     */
    public boolean insertCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        if (customer == null || customer.getCustId().trim().isEmpty()) {
            throw new IllegalArgumentException("Customer object or Customer ID cannot be null/empty.");
        }
        return customerDAO.insertCustomer(customer);
    }

    /**
     * Retrieves a customer by their unique ID.
     *
     * @param custId the customer ID.
     * @return the Customer object if found, otherwise null.
     * @throws SQLException, ClassNotFoundException if a database error occurs.
     */
    public Customer getCustomer(String custId) throws SQLException, ClassNotFoundException {
        if (custId == null || custId.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer ID cannot be null/empty.");
        }
        return customerDAO.getCustomer(custId);
    }

    /**
     * Fetches all customers from the database.
     *
     * @return a List of all Customer objects.
     * @throws SQLException, ClassNotFoundException if a database error occurs.
     */
    public List<Customer> fetchAllCustomers() throws SQLException, ClassNotFoundException {
        return customerDAO.fetchAllCustomers();
    }

    /**
     * Retrieves a customer by NIC number.
     *
     * @param nic the customer's NIC.
     * @return the Customer object if found, otherwise null.
     * @throws SQLException, ClassNotFoundException if a database error occurs.
     */
    public Customer fetchCustomerByNic(String nic) throws SQLException, ClassNotFoundException {
        if (nic == null || nic.trim().isEmpty()) {
            throw new IllegalArgumentException("NIC cannot be null/empty.");
        }
        return customerDAO.fetchCustomerByNic(nic);
    }

    /**
     * Retrieves a customer using either Customer ID or NIC.
     *
     * @param input the Customer ID or NIC.
     * @return the Customer object if found, otherwise null.
     * @throws SQLException, ClassNotFoundException if a database error occurs.
     */
    public Customer getCustomerByIdOrNic(String input) throws SQLException, ClassNotFoundException {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input (Customer ID/NIC) cannot be null/empty.");
        }
        return customerDAO.fetchCustomerByIdOrNic(input);
    }
    
    

    /**
     * Updates an existing customer in the database.
     *
     * @param customer the Customer object containing updated details.
     * @return true if update is successful, false otherwise.
     * @throws Exception if a database error occurs or invalid data is provided.
     */
    public boolean updateCustomer(Customer customer) throws Exception {
        if (customer == null || customer.getCustId().trim().isEmpty()) {
            throw new IllegalArgumentException("Customer object or Customer ID cannot be null/empty.");
        }
        return customerDAO.updateCustomer(customer);
    }

    /**
     * Deletes a customer record based on Customer ID.
     *
     * @param custId the unique ID of the customer.
     * @return true if deletion is successful, false otherwise.
     * @throws SQLException, ClassNotFoundException if a database error occurs.
     */
    public boolean deleteCustomer(String custId) throws SQLException, ClassNotFoundException {
        if (custId == null || custId.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer ID cannot be null/empty.");
        }
        return customerDAO.deleteCustomer(custId);
    }
}