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
@WebServlet("/CustomerServlet")
public class CustomerSevlet extends HttpServlet {

    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        // Initialize the CustomerService (no frameworks or libraries used)
        customerService = new CustomerService();
    }

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if ("getCustomer".equalsIgnoreCase(action)) {
            // The parameter can be a cust Id (e.g., CUS_88021) or NIC (e.g., 78454112)
            String input = request.getParameter("custId");

            Customer customer = null;
            try {
                // Now we look up by either cust Id OR NIC:
                customer = customerService.getCustomerByCustIdOrNic(input);
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
                        + "\"phoneno\":\"" + escapeJson(customer.getPhoneNo()) + "\""
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


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve parameters from the form (customerReg.jsp)
        String custId = request.getParameter("custId");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String nic = request.getParameter("nic");
        String phoneno = request.getParameter("phoneno");

        // Build the Customer object using the Builder pattern
        Customer customer = new Customer.Builder(custId)
                .name(name)
                .address(address)
                .nic(nic)
                .phoneno(phoneno)
                .build();

        try {
            // Add the customer using the service layer
            boolean success = customerService.addCustomer(customer);

            if (success) {
                // Set success message and forward back to customerReg.jsp
                request.setAttribute("message", "Customer registered successfully with Customer Id: " + custId);
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

 
    private String escapeJson(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("\"", "\\\"");
    }

}
