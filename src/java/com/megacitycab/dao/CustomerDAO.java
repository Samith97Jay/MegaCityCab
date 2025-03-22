package com.megacitycab.dao;

import com.megacitycab.model.Customer;
import com.megacitycab.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Optimized Data Access Object (DAO) for handling customer-related operations in the database.
 */
public class CustomerDAO {
    private static CustomerDAO instance;

    private CustomerDAO() {}

    // Singleton pattern
    public static synchronized CustomerDAO getInstance() {
        if (instance == null) {
            instance = new CustomerDAO();
        }
        return instance;
    }

    public boolean insertCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        if (!isValidNIC(customer.getNic()) || !isValidPhoneNo(customer.getPhoneno())) {
            System.out.println("Error: Invalid NIC or Phone Number format.");
            return false;
        }

        String sql = "INSERT INTO customers (custId, name, address, nic, phoneno) " +
                     "SELECT ?, ?, ?, ?, ? FROM DUAL WHERE NOT EXISTS " +
                     "(SELECT 1 FROM customers WHERE nic = ? OR custId = ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getCustId());
            stmt.setString(2, customer.getName());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getNic());
            stmt.setString(5, customer.getPhoneno());
            stmt.setString(6, customer.getNic());
            stmt.setString(7, customer.getCustId());

            return stmt.executeUpdate() > 0;
        }
    }

    public Customer getCustomer(String custId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT custId, name, address, nic, phoneno FROM customers WHERE custId = ? LIMIT 1";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, custId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Customer.Builder(rs.getString("custId"))
                            .name(rs.getString("name"))
                            .address(rs.getString("address"))
                            .nic(rs.getString("nic"))
                            .phoneno(rs.getString("phoneno"))
                            .build();
                }
            }
        }
        return null;
    }

    public boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE customers SET name = ?, address = ?, nic = ?, phoneno = ? WHERE custId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getAddress());
            stmt.setString(3, customer.getNic());
            stmt.setString(4, customer.getPhoneno());
            stmt.setString(5, customer.getCustId());

            return stmt.executeUpdate() > 0;
        }
    }

   public boolean deleteCustomer(String custId) throws SQLException, ClassNotFoundException {
    String sql = "DELETE FROM customers WHERE custId = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, custId);
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0; // Returns true if deletion was successful
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

    public List<Customer> fetchAllCustomers() throws SQLException, ClassNotFoundException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT custId, name, address, nic, phoneno FROM customers";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                customers.add(new Customer.Builder(rs.getString("custId"))
                        .name(rs.getString("name"))
                        .address(rs.getString("address"))
                        .nic(rs.getString("nic"))
                        .phoneno(rs.getString("phoneno"))
                        .build());
            }
        }
        return customers;
    }

    public Customer fetchCustomerByNic(String nic) throws SQLException, ClassNotFoundException {
        return fetchCustomerByCondition("nic", nic);
    }

    public Customer fetchCustomerByIdOrNic(String input) throws SQLException, ClassNotFoundException {
        String sql = "SELECT custId, name, address, nic, phoneno FROM customers WHERE custId = ? OR nic = ? LIMIT 1";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, input);
            stmt.setString(2, input);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Customer.Builder(rs.getString("custId"))
                            .name(rs.getString("name"))
                            .address(rs.getString("address"))
                            .nic(rs.getString("nic"))
                            .phoneno(rs.getString("phoneno"))
                            .build();
                }
            }
        }
        return null;
    }

    private Customer fetchCustomerByCondition(String column, String value) throws SQLException, ClassNotFoundException {
        String sql = "SELECT custId, name, address, nic, phoneno FROM customers WHERE " + column + " = ? LIMIT 1";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, value);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Customer.Builder(rs.getString("custId"))
                            .name(rs.getString("name"))
                            .address(rs.getString("address"))
                            .nic(rs.getString("nic"))
                            .phoneno(rs.getString("phoneno"))
                            .build();
                }
            }
        }
        return null;
    }

    private boolean isRecordExists(String column, String value) throws SQLException, ClassNotFoundException {
        String sql = "SELECT 1 FROM customers WHERE " + column + " = ? LIMIT 1";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, value);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    private boolean isValidNIC(String nic) {
        return Pattern.matches("^\\d{9}[Vv]$|^\\d{12}$", nic);
    }

    private boolean isValidPhoneNo(String phoneno) {
        return Pattern.matches("^(\\+94\\d{9}|\\d{10})$", phoneno);
    }

}
  


