package com.megacitycab.controller;

import com.megacitycab.model.Vehicle;
import com.megacitycab.model.Customer;
import com.megacitycab.model.Driver;
import com.megacitycab.service.RetriveService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/RetrivalServlet")
public class RetrivelServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private RetriveService service;

    @Override
    public void init() throws ServletException {
        service = new RetriveService();
    }

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchId = request.getParameter("searchId");

        if (searchId != null && !searchId.trim().isEmpty()) {
            try {
                // Check for customer registration number (CUS_ prefix)
                if (searchId.startsWith("CUS00_")) {
                    Customer customer = service.getCustomer(searchId);
                    if (customer != null) {
                        request.setAttribute("recordType", "customer");
                        request.setAttribute("customer", customer);
                    } else {
                        request.setAttribute("errorMessage", "No customer record found for Registration Number " + searchId);
                    }
                }
                // Check for driver record (DI prefix)
                else if (searchId.startsWith("DI")) {
                    Driver driver = service.getDriver(searchId);
                    if (driver != null) {
                        request.setAttribute("recordType", "driver");
                        request.setAttribute("driver", driver);
                    } else {
                        request.setAttribute("errorMessage", "No driver record found for ID " + searchId);
                    }
                }
                // Check for vehicle record (prefixes: C, S, V, or B)
                else if (searchId.startsWith("C") || searchId.startsWith("S") ||
                         searchId.startsWith("V")) {
                    Vehicle vehicle = service.getVehicle(searchId);
                    if (vehicle != null) {
                        request.setAttribute("recordType", "vehicle");
                        request.setAttribute("vehicle", vehicle);
                    } else {
                        request.setAttribute("errorMessage", "No vehicle record found for ID " + searchId);
                    }
                }
               
                else {
                    Customer customer = service.getCustomerByNic(searchId);
                    if (customer != null) {
                        request.setAttribute("recordType", "customer");
                        request.setAttribute("customer", customer);
                    } else {
                        request.setAttribute("errorMessage", "No customer record found for NIC " + searchId);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Error retrieving record: " + e.getMessage());
            }
        }
        request.getRequestDispatcher("retrive.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Identify which type of record is being updated.
        String custId = request.getParameter("custId");
        String driverId = request.getParameter("driverId");
        String vehicleId = request.getParameter("vehicleId");

        boolean updateSuccess = false;
        String recordType = "";

        try {
            if (custId != null && !custId.isEmpty()) {
                
                String customerName = request.getParameter("namew");
                String customerAddress = request.getParameter("address");
                String customerNic = request.getParameter("nic");
                String customerTelephone = request.getParameter("phoneno");

                Customer updatedCustomer = new Customer.Builder(custId)
                        .name(customerName)
                        .address(customerAddress)
                        .nic(customerNic)
                        .phoneno(customerTelephone)
                        .build();

                updateSuccess = service.updateCustomer(updatedCustomer);
                recordType = "customer";
                request.setAttribute("customer", service.getCustomer(custId));
            } else if (driverId != null && !driverId.isEmpty()) {
                // Update Driver record
                String driverName = request.getParameter("driverName");
                String licenseNumber = request.getParameter("licenseNumber");
                String phone = request.getParameter("phone");
                String driverAddress = request.getParameter("driverAddress");
                String assignedVehicleId = request.getParameter("assignedVehicleId");

                Driver updatedDriver = new Driver.Builder(driverId)
                        .name(driverName)
                        .licenseNumber(licenseNumber)
                        .phone(phone)
                        .address(driverAddress)
                        .assignedVehicleId(assignedVehicleId)
                        .build();

                updateSuccess = service.updateDriver(updatedDriver);
                recordType = "driver";
                request.setAttribute("driver", service.getDriver(driverId));
            } else if (vehicleId != null && !vehicleId.isEmpty()) {
                // Update Vehicle record
                String vehicleType = request.getParameter("vehicleType");
                String licensePlate = request.getParameter("licensePlate");
                String vehicleModel = request.getParameter("vehicleModel");
                String vehicleBrand = request.getParameter("vehicleBrand");
                String color = request.getParameter("color");
                int seat = 0;
                try {
                    seat = Integer.parseInt(request.getParameter("seat"));
                } catch (NumberFormatException e) {
                    
                }

                Vehicle updatedVehicle = new Vehicle.Builder(vehicleType, vehicleId)
                        .licensePlate(licensePlate)
                        .vehicleModel(vehicleModel)
                        .vehicleBrand(vehicleBrand)
                        .color(color)
                        .seat(seat)
                        .build();

                updateSuccess = service.updateVehicle(updatedVehicle);
                recordType = "vehicle";
                request.setAttribute("vehicle", service.getVehicle(vehicleId));
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error updating record: " + e.getMessage());
        }

        if (updateSuccess) {
            request.setAttribute("message", "Record updated successfully!");
        } else {
            request.setAttribute("errorMessage", "Record update failed.");
        }
        request.setAttribute("recordType", recordType);
        request.getRequestDispatcher("retrive.jsp").forward(request, response);
    }
}
