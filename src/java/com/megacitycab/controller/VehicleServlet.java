/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megacitycab.controller;

import com.megacitycab.model.Vehicle;
import com.megacitycab.service.VehicleService;
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


@WebServlet("/VehicleServlet")
public class VehicleServlet extends HttpServlet {

  private VehicleService vehicleService;
    private int seat;

    @Override
    public void init() throws ServletException {
        vehicleService = new VehicleService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        try {
            if ("getAvailableNotBookedVehicle".equalsIgnoreCase(action)) {
                String vehicleType = request.getParameter("vehicleType");
                Vehicle vehicle = vehicleService.getAvailableNotBookedVehicle(vehicleType);
                if (vehicle != null) {
                    // Build a JSON response with vehicle details
                    StringBuilder json = new StringBuilder();
                    json.append("{");
                    json.append("\"vehicleType\":\"").append(vehicle.getVehicleType()).append("\",");
                    json.append("\"vehicleId\":\"").append(vehicle.getVehicleId()).append("\",");
                    json.append("\"lisce\":\"").append(vehicle.getLisce()).append("\",");
                    json.append("\"vehicleModel\":\"").append(vehicle.getVehicleModel()).append("\",");
                    json.append("\"vehicleBrand\":\"").append(vehicle.getVehicleBrand()).append("\",");
                    json.append("\"color\":\"").append(vehicle.getColor()).append("\",");
                    json.append("\"seat\":").append(vehicle.getSeat());
                    json.append("}");
                    out.write(json.toString());
                } else {
                    // Return a JSON message if no available vehicle is found
                    out.write("{\"message\":\"All vehicles are booked\"}");
                }
            } else if ("getAvailableVehicle".equalsIgnoreCase(action)) {
                String vehicleType = request.getParameter("vehicleType");
                Vehicle vehicle = vehicleService.getAvailableVehicle(vehicleType);
                if (vehicle != null) {
                    StringBuilder json = new StringBuilder();
                    json.append("{");
                    json.append("\"vehicleType\":\"").append(vehicle.getVehicleType()).append("\",");
                    json.append("\"vehicleId\":\"").append(vehicle.getVehicleId()).append("\",");
                    json.append("\"lisce\":\"").append(vehicle.getLisce()).append("\",");
                    json.append("\"vehicleModel\":\"").append(vehicle.getVehicleModel()).append("\",");
                    json.append("\"vehicleBrand\":\"").append(vehicle.getVehicleBrand()).append("\",");
                    json.append("\"color\":\"").append(vehicle.getColor()).append("\",");
                    json.append("\"seat\":").append(vehicle.getSeat());
                    json.append("}");
                    out.write(json.toString());
                } else {
                    out.write("{}");
                }
            } else {
                out.write("{\"error\":\"Invalid action\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.write("{\"error\":\"" + e.getMessage() + "\"}");
        } finally {
            out.flush();
            out.close();
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters from the form
        String vehicleType = request.getParameter("vehicleType");
        String vehicleId = request.getParameter("vehicleId");
        String lisce = request.getParameter("lisce");
        String vehicleModel = request.getParameter("vehicleModel");
        String vehicleBrand = request.getParameter("vehicleBrand");
        String color = request.getParameter("color");
        int seat = 0;
        try {
            seat= Integer.parseInt(request.getParameter("seat"));
        } catch (NumberFormatException e) {
            // Handle if seatingCapacity is not provided or is invalid
        }

        // Call service to register the vehicle
        boolean isRegistered = false;
      try {
          isRegistered = vehicleService.registerVehicle(vehicleType, vehicleId, lisce, vehicleModel, vehicleBrand, color, seat);
      } catch (ClassNotFoundException ex) {
          Logger.getLogger(VehicleServlet.class.getName()).log(Level.SEVERE, null, ex);
      } catch (SQLException ex) {
          Logger.getLogger(VehicleServlet.class.getName()).log(Level.SEVERE, null, ex);
      }

        if(isRegistered){
            request.setAttribute("message", "Vehicle registered successfully with Registration ID: " + vehicleId);
        } else {
            request.setAttribute("errorMessage", "Vehicle registration failed. Please try again.");
        }
        request.getRequestDispatcher("vehicleReg.jsp").forward(request, response);
    }
}
