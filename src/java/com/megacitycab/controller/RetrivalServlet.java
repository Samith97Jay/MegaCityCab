package com.megacitycab.controller;

import com.megacitycab.model.Vehicle;
import com.megacitycab.model.Customer;
import com.megacitycab.model.Driver;
import com.megacitycab.service.RetrivalService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/RetrivalServlet")
public class RetrivalServlet extends HttpServlet {



    
    private static final long serialVersionUID = 1L;
    private RetrivalService service;


     @Override
    public void init() throws ServletException {
        service = new RetrivalService();
    }
  

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchId = request.getParameter("searchId");

        if (searchId != null && !searchId.trim().isEmpty()) {
            try {
               
                if (searchId.startsWith("CU")) {
                    Customer customer = service.getCustomer(searchId);
                    if (customer != null) {
                        request.setAttribute("recordType", "customer");
                        request.setAttribute("customer", customer);
                    } else {
                        request.setAttribute("errorMessage", "No customer record found for Customer Id " + searchId);
                    }
                }
              
                else if (searchId.startsWith("D")) {
                    Driver driver = service.getDriver(searchId);
                    if (driver != null) {
                        request.setAttribute("recordType", "driver");
                        request.setAttribute("driver", driver);
                    } else {
                        request.setAttribute("errorMessage", "No driver record found for ID " + searchId);
                    }
                }
                
                else if (searchId.startsWith("V")) {
                    Vehicle vehicle = service.getVehicle(searchId);
                    if (vehicle != null) {
                        request.setAttribute("recordType", "vehicle");
                        request.setAttribute("vehicle", vehicle);
                    } else {
                        request.setAttribute("errorMessage", "No vehicle record found for ID " + searchId);
                    }
                }
                // Otherwise, assume search is by NIC (for customers)
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

  /**
     * Handles POST requests for updating or deleting records.
     * It determines which type of record is being updated or deleted based on the submitted parameters.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean updateSuccess = false;
        boolean deleteSuccess = false;
        String recordType = "";

        try {
            // Check for delete actions first
            if (request.getParameter("deleteCustomer") != null) {
                String custId = request.getParameter("custId");
                deleteSuccess = service.deleteCustomer(custId);
                recordType = "customer";
                if (deleteSuccess) {
                    request.setAttribute("message", "Customer account deleted successfully!");
                } else {
                    request.setAttribute("errorMessage", "Customer deletion failed.");
                }
            } else if (request.getParameter("deleteDriver") != null) {
                String driverId = request.getParameter("driverId");
                deleteSuccess = service.deleteDriver(driverId);
                recordType = "driver";
                if (deleteSuccess) {
                    request.setAttribute("message", "Driver account deleted successfully!");
                } else {
                    request.setAttribute("errorMessage", "Driver deletion failed.");
                }
            } else if (request.getParameter("deleteVehicle") != null) {
                String vehicleId = request.getParameter("vehicleId");
                deleteSuccess = service.deleteVehicle(vehicleId);
                recordType = "vehicle";
                if (deleteSuccess) {
                    request.setAttribute("message", "Vehicle removed successfully!");
                } else {
                    request.setAttribute("errorMessage", "Vehicle removal failed.");
                }
            }
            // If not a delete action, then process updates
            else if (request.getParameter("custId") != null && !request.getParameter("custId").isEmpty()) {
                // Update Customer record
                String custId = request.getParameter("custId");
                String customerName = request.getParameter("name");
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
                if (updateSuccess) {
                    request.setAttribute("message", "Customer record updated successfully!");
                } else {
                    request.setAttribute("errorMessage", "Customer record update failed.");
                }
            } else if (request.getParameter("driverId") != null && !request.getParameter("driverId").isEmpty()) {
                // Update Driver record
                String driverId = request.getParameter("driverId");
                String driverName = request.getParameter("driverName");
                String lisce = request.getParameter("lisce");
                String phoneno = request.getParameter("phone");
                String driverAddress = request.getParameter("driverAddress");
                String assignedVehicleId = request.getParameter("assignedVehicleId");

                Driver updatedDriver = new Driver.Builder(driverId)
                        .name(driverName)
                        .lisce(lisce)
                        .phoneno(phoneno)
                        .address(driverAddress)
                        .assignedVehicleId(assignedVehicleId)
                        .build();

                updateSuccess = service.updateDriver(updatedDriver);
                recordType = "driver";
                request.setAttribute("driver", service.getDriver(driverId));
                if (updateSuccess) {
                    request.setAttribute("message", "Driver record updated successfully!");
                } else {
                    request.setAttribute("errorMessage", "Driver record update failed.");
                }
            }  else if (request.getParameter("vehicleId") != null && !request.getParameter("vehicleType").isEmpty()) {
                // Update Vehicle record
                String vehicleId = request.getParameter("vehicleId");
                String vehicleType = request.getParameter("vehicleType");
                
                // Validate vehicleType parameter
                if (vehicleType == null || vehicleType.trim().isEmpty()) {
                    request.setAttribute("errorMessage", "Vehicle type cannot be null or empty.");
                    request.setAttribute("recordType", "vehicle");
                    request.setAttribute("vehicle", service.getVehicle(vehicleId));
                    request.getRequestDispatcher("retrive.jsp").forward(request, response);
                    return;
                }
                
                String lisce = request.getParameter("lisce");
                String vehicleModel = request.getParameter("vehicleModel");
                String vehicleBrand = request.getParameter("vehicleBrand");
                String color = request.getParameter("color");
                int seat = 0;
                try {
                    seat = Integer.parseInt(request.getParameter("seat"));
                } catch (NumberFormatException e) {
                    // Handle invalid seating capacity input if needed.
                }
                String vehicleDriverId = request.getParameter("vehicleDriverId");

                Vehicle updatedVehicle;
                updatedVehicle = new Vehicle.Builder(vehicleType, vehicleId)
                        .lisce(lisce)
                        .vehicleModel(vehicleModel)
                        .vehicleBrand(vehicleBrand)
                        .color(color)
                        .seat(seat)
                        .driverId(vehicleDriverId)
                        .build();

                updateSuccess = service.updateVehicle(updatedVehicle);
                recordType = "vehicle";
                request.setAttribute("car", service.getVehicle(vehicleId));
                if (updateSuccess) {
                    request.setAttribute("message", "Vehicle record updated successfully!");
                } else {
                    request.setAttribute("errorMessage", "Vehicle record update failed.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error processing request: " + e.getMessage());
        }
        request.setAttribute("recordType", recordType);
        request.getRequestDispatcher("retrive.jsp").forward(request, response);
    }
    }

