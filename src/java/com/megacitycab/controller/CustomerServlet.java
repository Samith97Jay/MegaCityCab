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
import org.json.JSONObject;

/**
 * Servlet Controller for Customer operations.
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(CustomerServlet.class.getName());
    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        this.customerService = new CustomerService();
    }

    /**
     * Handles GET requests to fetch customer details by Customer ID or NIC.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("getCustomer".equalsIgnoreCase(action)) {
            handleGetCustomer(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
        }
    }

    /**
     * Handles POST requests for customer registration.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String custId = request.getParameter("custId");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String nic = request.getParameter("nic");
        String phoneno = request.getParameter("phoneno");

        if (custId == null || custId.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Customer ID is required.");
            request.getRequestDispatcher("customerReg.jsp").forward(request, response);
            return;
        }

     Customer customer = new Customer.Builder(custId)
        .name(name)
        .address(address)
        .nic(nic)
        .phoneno(phoneno)
        .build();

        try {
            boolean success = customerService.insertCustomer(customer);
            if (success) {
                request.setAttribute("message", "Customer registered successfully with Customer ID: " + custId);
            } else {
                request.setAttribute("errorMessage", "Failed to register customer. Please try again.");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Error inserting customer", ex);
            request.setAttribute("errorMessage", "An error occurred while registering the customer.");
        }

        request.getRequestDispatcher("customerReg.jsp").forward(request, response);
    }

    /**
     * Handles customer retrieval based on ID or NIC.
     */
    private void handleGetCustomer(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String input = request.getParameter("custId");

        if (input == null || input.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Customer ID or NIC is required.");
            return;
        }

        try {
            Customer customer = customerService.fetchCustomerByIdOrNic(input);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            JSONObject jsonResponse = new JSONObject();
            if (customer != null) {
                jsonResponse.put("name", customer.getName());
                jsonResponse.put("phoneno", customer.getPhoneno());
            }

            PrintWriter out = response.getWriter();
            out.write(jsonResponse.toString());
            out.flush();
        } catch (SQLException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Error fetching customer", ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving customer data.");
        }
    }
}