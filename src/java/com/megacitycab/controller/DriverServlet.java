/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megacitycab.controller;

import com.megacitycab.model.Customer;
import com.megacitycab.model.Driver;
import com.megacitycab.service.DriverService;
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


@WebServlet("/DriverServlet")
public class DriverServlet extends HttpServlet {

 private static final long serialVersionUID = 1L;
    private DriverService driverService;

    @Override
    public void init() throws ServletException {
        driverService = new DriverService();
    }

    /**
     * Processes registration form submissions.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters.
        String driverId = request.getParameter("driverId");
        String name = request.getParameter("name");
        String lisce = request.getParameter("lisce");
        String phoneno = request.getParameter("phoneno");
        String address = request.getParameter("address");
        String assignedVehicleId = request.getParameter("assignedVehicleId");
        
          if (driverId == null || driverId.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Driver ID is required.");
            request.getRequestDispatcher("driverReg.jsp").forward(request, response);
            return;
        }


        // Build a Driver instance using the Builder pattern.
        Driver driver = new Driver.Builder(driverId)
                .name(name)
                .lisce(lisce)
                .phoneno(phoneno)
                .address(address)
                .assignedVehicleId(assignedVehicleId)
                .build();

        try {
            boolean success = driverService.registerDriver(driver);
            if (success) {
                request.setAttribute("message", "Driver registered successfully with Driver ID: " + driverId);
            } else {
                request.setAttribute("errorMessage", "Failed to register driver. Please try again.");
            }
        } catch (SQLException | ClassNotFoundException ex) {
          
            request.setAttribute("errorMessage", "An error occurred while registering the driver.");
        }

        request.getRequestDispatcher("driverReg.jsp").forward(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect GET requests to the POST handler.
        doPost(request, response);
    }
}
