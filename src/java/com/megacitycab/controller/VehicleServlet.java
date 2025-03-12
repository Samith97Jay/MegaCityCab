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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author OZT00090
 */
public class VehicleServlet extends HttpServlet {

  private VehicleService vehicleService;

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
                    json.append("\"vehicleRegId\":\"").append(vehicle.getVehicleRegId()).append("\",");
                    json.append("\"licensePlate\":\"").append(vehicle.getLicensePlate()).append("\",");
                    json.append("\"model\":\"").append(vehicle.getModel()).append("\",");
                    json.append("\"brand\":\"").append(vehicle.getBrand()).append("\",");
                    json.append("\"color\":\"").append(vehicle.getColor()).append("\",");
                    json.append("\"seatingCapacity\":").append(vehicle.getSeatingCapacity());
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
                    json.append("\"vehicleRegId\":\"").append(vehicle.getVehicleRegId()).append("\",");
                    json.append("\"licensePlate\":\"").append(vehicle.getLicensePlate()).append("\",");
                    json.append("\"model\":\"").append(vehicle.getModel()).append("\",");
                    json.append("\"brand\":\"").append(vehicle.getBrand()).append("\",");
                    json.append("\"color\":\"").append(vehicle.getColor()).append("\",");
                    json.append("\"seatingCapacity\":").append(vehicle.getSeatingCapacity());
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
}
