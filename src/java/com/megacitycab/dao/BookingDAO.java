/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megacitycab.dao;

import com.megacitycab.model.Booking;
import com.megacitycab.util.DBConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author OZT00090
 */
public class BookingDAO {
    private static BookingDAO instance;

    private BookingDAO() {}

    public static synchronized BookingDAO getInstance() {
        if (instance == null) {
            instance = new BookingDAO();
        }
        return instance;
    }

    /**
     * Inserts a booking into the database.
     *
     * @param booking The booking to add.
     * @return true if insertion was successful.
     * @throws SQLException If an SQL error occurs.
     */
    public boolean addBooking(Booking booking) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO bookings (bookingNumber, customerName, pickupLocation, telephoneNumber, destination, bookingDate, customerRegNo, vehicleType, vehicleRegId, Vbrand, Vmodel, Vseating) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, booking.getBookingNumber());
            stmt.setString(2, booking.getCustomerName());
            stmt.setString(3, booking.getCustomerAddress());
            stmt.setString(4, booking.getTelephoneNumber());
            stmt.setString(5, booking.getDestination());
            Date bookingDate = (Date) booking.getBookingDate();
            stmt.setDate(6, new java.sql.Date(bookingDate != null ? bookingDate.getTime() : System.currentTimeMillis()));
            stmt.setString(7, booking.getCustomerRegNo());
            stmt.setString(8, booking.getVehicleType());
            stmt.setString(9, booking.getVehicleRegId());
            stmt.setString(10, booking.getVbrand());
            stmt.setString(11, booking.getVmodel());
            stmt.setString(12, booking.getVseating());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Inserted booking: " + booking);
            } else {
                System.err.println("No rows inserted for booking: " + booking);
            }
            return rowsInserted > 0;
        } catch (SQLException ex) {
            System.err.println("SQLException in addBooking:");
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("Error Code: " + ex.getErrorCode());
            System.err.println("Message: " + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * Retrieves a booking from the database using its booking number.
     *
     * @param bookingNumber The unique booking number.
     * @return The Booking object if found, otherwise null.
     * @throws SQLException If an SQL error occurs.
     */
    public Booking getBooking(String bookingNumber) throws SQLException, ClassNotFoundException {
        String sql = "SELECT bookingNumber, customerName, pickupLocation, telephoneNumber, destination, customerRegNo, bookingDate, vehicleType, vehicleRegId, Vbrand, Vmodel, Vseating "
                   + "FROM bookings WHERE bookingNumber = ?";
        Booking booking = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bookingNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String bNumber = rs.getString("bookingNumber");
                    String customerName = rs.getString("customerName");
                    String customerAddress = rs.getString("pickupLocation");
                    String telephoneNumber = rs.getString("telephoneNumber");
                    String destination = rs.getString("destination");
                    java.sql.Date sqlDate = rs.getDate("bookingDate");
                    Date bookingDate = sqlDate != null ? new Date(sqlDate.getTime()) : null;
                    String customerRegNo = rs.getString("customerRegNo");
                    String vehicleType = rs.getString("vehicleType");
                    String vehicleRegId = rs.getString("vehicleRegId");
                    String vbrand = rs.getString("Vbrand");
                    String vmodel = rs.getString("Vmodel");
                    String vseating = rs.getString("Vseating");

                    booking = new Booking.Builder(bNumber)
                            .customerName(customerName)
                            .customerAddress(customerAddress)
                            .telephoneNumber(telephoneNumber)
                            .destination(destination)
                            .bookingDate(bookingDate)
                            .customerRegNo(customerRegNo)
                            .vehicleType(vehicleType)
                            .vehicleRegId(vehicleRegId)
                            .vbrand(vbrand)
                            .vmodel(vmodel)
                            .vseating(vseating)
                            .build();
                }
            }
        }
        return booking;
    }

    /**
     * Retrieves all bookings from the database.
     *
     * @return A list of Booking objects.
     * @throws SQLException If an SQL error occurs.
     */
    public List<Booking> getAllBookings() throws SQLException, ClassNotFoundException {
        String sql = "SELECT bookingNumber, customerName, pickupLocation, telephoneNumber, destination, customerRegNo, bookingDate, vehicleType, vehicleRegId, Vbrand, Vmodel, Vseating FROM bookings";
        List<Booking> bookings = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String bNumber = rs.getString("bookingNumber");
                String customerName = rs.getString("customerName");
                String customerAddress = rs.getString("pickupLocation");
                String telephoneNumber = rs.getString("telephoneNumber");
                String destination = rs.getString("destination");
                java.sql.Timestamp ts = rs.getTimestamp("bookingDate");
                Date bookingDate = ts != null ? new Date(ts.getTime()) : null;
                String customerRegNo = rs.getString("customerRegNo");
                String vehicleType = rs.getString("vehicleType");
                String vehicleRegId = rs.getString("vehicleRegId");
                String vbrand = rs.getString("Vbrand");
                String vmodel = rs.getString("Vmodel");
                String vseating = rs.getString("Vseating");

                Booking booking = new Booking.Builder(bNumber)
                        .customerName(customerName)
                        .customerAddress(customerAddress)
                        .telephoneNumber(telephoneNumber)
                        .destination(destination)
                        .bookingDate(bookingDate)
                        .customerRegNo(customerRegNo)
                        .vehicleType(vehicleType)
                        .vehicleRegId(vehicleRegId)
                        .vbrand(vbrand)
                        .vmodel(vmodel)
                        .vseating(vseating)
                        .build();
                bookings.add(booking);
            }
        }
        return bookings;
    }
    
}
