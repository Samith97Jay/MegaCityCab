/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megacitycab.controller;

import com.megacitycab.model.Customer;
import com.megacitycab.service.CustomerService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author OZT00090
 */
@WebServlet("/CustomerRegistrationServlet")
public class CustomerSevlet extends HttpServlet {

    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        // Initialize the CustomerService (no frameworks or libraries used)
        customerService = new CustomerService();
    }

    /**
     * Handles GET requests to fetch customer details based on a registration number or NIC.
     * Example URL: /CustomerRegistrationServlet?action=getCustomer&customerRegNo=CUS_88021
     * or         : /CustomerRegistrationServlet?action=getCustomer&customerRegNo=78454112
     *
     * If found, returns JSON like:
     *   {
     *     "customerName": "John Doe",
     *     "telephoneNumber": "0771234567"
     *   }
     * If not found, returns an empty JSON object: {}
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if ("getCustomer".equalsIgnoreCase(action)) {
            // The parameter can be a registration number (e.g., CUS_88021) or NIC (e.g., 78454112)
            String input = request.getParameter("customerRegNo");

            Customer customer = null;
            try {
                // Now we look up by either registration number OR NIC:
                customer = customerService.getCustomerByRegOrNic(input);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CustomerSevlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Return a JSON response
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();

            if (customer != null) {
                // Manually build JSON (no external libraries)
                String json = "{"
                        + "\"customerName\":\"" + escapeJson(customer.getName()) + "\","
                        + "\"telephoneNumber\":\"" + escapeJson(customer.getTelephone()) + "\""
                        + "}";
                out.write(json);
            } else {
                // If customer not found, return empty object
                out.write("{}");
            }
            out.flush();
        } else {
            // If action not recognized, send HTTP 400
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
        }
    }

    /**
     * Handles POST requests for customer registration.
     * Receives parameters from the registration form, creates a Customer object,
     * and persists it via the CustomerService.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve parameters from the form (customerReg.jsp)
        String registrationNumber = request.getParameter("registrationNumber");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String nic = request.getParameter("nic");
        String telephone = request.getParameter("telephone");

        // Build the Customer object using the Builder pattern
        Customer customer = new Customer.Builder(registrationNumber)
                .name(name)
                .address(address)
                .nic(nic)
                .telephone(telephone)
                .build();

        try {
            // Add the customer using the service layer
            boolean success = customerService.addCustomer(customer);

            if (success) {
                // Set success message and forward back to customerReg.jsp
                request.setAttribute("message", "Customer registered successfully with Registration Number: " + registrationNumber);
            } else {
                request.setAttribute("errorMessage", "Failed to register customer. Please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerSevlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Forward to the registration page so that messages are displayed
        request.getRequestDispatcher("customerReg.jsp").forward(request, response);
    }

    /**
     * Helper method to escape special characters in JSON string values.
     * This is a simple implementation that escapes double quotes.
     *
     * @param value The string to be escaped.
     * @return Escaped string safe for JSON output.
     */
    private String escapeJson(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("\"", "\\\"");
    }

}
