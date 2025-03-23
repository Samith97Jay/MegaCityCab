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

        try (PrintWriter out = response.getWriter()) {
            if ("getAvailableNotBookedVehicle".equalsIgnoreCase(action)) {
                handleGetAvailableNotBookedVehicle(request, out);
            } else if ("getAvailableVehicle".equalsIgnoreCase(action)) {
                handleGetAvailableVehicle(request, out);
            } else {
                out.write("{\"error\":\"Invalid action\"}");
            }
        }
    }

    private void handleGetAvailableNotBookedVehicle(HttpServletRequest request, PrintWriter out) {
        try {
            String vehicleType = request.getParameter("vehicleType");
            Vehicle vehicle = vehicleService.getAvailableNotBookedVehicle(vehicleType);
            out.write(vehicle != null ? buildVehicleJson(vehicle) : "{\"message\":\"All vehicles are booked\"}");
        } catch (Exception e) {
            Logger.getLogger(VehicleServlet.class.getName()).log(Level.SEVERE, "Error fetching vehicle", e);
            out.write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    private void handleGetAvailableVehicle(HttpServletRequest request, PrintWriter out) {
        try {
            String vehicleType = request.getParameter("vehicleType");
            Vehicle vehicle = vehicleService.getAvailableVehicle(vehicleType);
            out.write(vehicle != null ? buildVehicleJson(vehicle) : "{}" );
        } catch (Exception e) {
            Logger.getLogger(VehicleServlet.class.getName()).log(Level.SEVERE, "Error fetching vehicle", e);
            out.write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    private String buildVehicleJson(Vehicle vehicle) {
        return "{" +
                "\"vehicleType\":\"" + vehicle.getVehicleType() + "\"," +
                "\"vehicleId\":\"" + vehicle.getVehicleId() + "\"," +
                "\"lisce\":\"" + vehicle.getLisce() + "\"," +
                "\"vehicleModel\":\"" + vehicle.getVehicleModel() + "\"," +
                "\"vehicleBrand\":\"" + vehicle.getVehicleBrand() + "\"," +
                "\"color\":\"" + vehicle.getColor() + "\"," +
                "\"seat\":" + vehicle.getSeat() +
                "}";
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vehicleType = request.getParameter("vehicleType");
        String vehicleId = request.getParameter("vehicleId");
        String lisce = request.getParameter("lisce");
        String vehicleModel = request.getParameter("vehicleModel");
        String vehicleBrand = request.getParameter("vehicleBrand");
        String color = request.getParameter("color");
        int seat = parseSeat(request.getParameter("seat"));

        try {
            boolean isRegistered = vehicleService.registerVehicle(vehicleType, vehicleId, lisce, vehicleModel, vehicleBrand, color, seat);
            request.setAttribute(isRegistered ? "message" : "errorMessage", isRegistered ? "Vehicle registered successfully with Registration ID: " + vehicleId : "Vehicle registration failed. Please try again.");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VehicleServlet.class.getName()).log(Level.SEVERE, "Error registering vehicle", ex);
            request.setAttribute("errorMessage", "An error occurred while registering the vehicle.");
        }

        request.getRequestDispatcher("vehicleReg.jsp").forward(request, response);
    }

    private int parseSeat(String seatParam) {
        try {
            return Integer.parseInt(seatParam);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
