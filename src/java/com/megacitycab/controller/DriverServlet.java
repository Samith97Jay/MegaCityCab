/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megacitycab.controller;

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

/**
 *
 * @author OZT00090
 */
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
        String licenseNumber = request.getParameter("licenseNumber");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String assignedVehicleId = request.getParameter("assignedVehicleId");

        // Build a Driver instance using the Builder pattern.
        Driver driver = new Driver.Builder(driverId)
                .name(name)
                .licenseNumber(licenseNumber)
                .phone(phone)
                .address(address)
                .assignedVehicleId(assignedVehicleId)
                .build();

        // Attempt to register the driver.
        boolean isRegistered = false;
     try {
         isRegistered = driverService.registerDriver(driver);
     } catch (SQLException ex) {
         Logger.getLogger(DriverServlet.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
         Logger.getLogger(DriverServlet.class.getName()).log(Level.SEVERE, null, ex);
     }

        // Set a feedback message based on the outcome.
        if(isRegistered) {
            request.setAttribute("message", "Driver registered successfully!");
        } else {
            request.setAttribute("message", "Driver registration failed. Please try again.");
        }
        request.getRequestDispatcher("driverReg.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect GET requests to the POST handler.
        doPost(request, response);
    }
}
