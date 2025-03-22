package com.megacitycab.dao;

import com.megacitycab.model.Booking;
import com.megacitycab.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingDAO {
    private static BookingDAO instance;

    private BookingDAO() {}

    public static synchronized BookingDAO getInstance() {
        if (instance == null) {
            instance = new BookingDAO();
        }
        return instance;
    }

    public boolean addBooking(Booking booking) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO bookings (bookingId, customerName, customerAddress, telephoneNo, pickupLocation, destination, bookingDate, customerId, vehicleType, vehicleId, vehicleBrand, vehicleModel, seat) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, booking.getBookingId());
            stmt.setString(2, booking.getCustomerName());
            stmt.setString(3, booking.getCustomerAddress());
            stmt.setString(4, booking.getTelephoneNo());
            stmt.setString(5, booking.getPickupLocation());
            stmt.setString(6, booking.getDestination());
            stmt.setDate(7, new java.sql.Date(booking.getBookingDate().getTime()));
            stmt.setString(8, booking.getCustomerId());
            stmt.setString(9, booking.getVehicleType());
            stmt.setString(10, booking.getVehicleId());
            stmt.setString(11, booking.getVehicleBrand());
            stmt.setString(12, booking.getVehicleModel());
            stmt.setString(13, booking.getSeat());

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
    

    public Booking getBooking(String bookingId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM bookings WHERE bookingId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bookingId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Booking.Builder(rs.getString("bookingId"))
                            .customerName(rs.getString("customerName"))
                            .customerAddress(rs.getString("customerAddress"))
                            .telephoneNo(rs.getString("telephoneNo"))
                            .pickupLocation(rs.getString("pickupLocation"))
                            .destination(rs.getString("destination"))
                            .bookingDate(rs.getDate("bookingDate"))
                            .customerId(rs.getString("customerId"))
                            .vehicleType(rs.getString("vehicleType"))
                            .vehicleId(rs.getString("vehicleId"))
                            .vehicleBrand(rs.getString("vehicleBrand"))
                            .vehicleModel(rs.getString("vehicleModel"))
                            .seat(rs.getString("seat"))
                            .build();
                }
            }
        }
        return null;
    }

    public List<Booking> getAllBookings() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM bookings";
        List<Booking> bookings = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                bookings.add(new Booking.Builder(rs.getString("bookingId"))
                        .customerName(rs.getString("customerName"))
                        .customerAddress(rs.getString("customerAddress"))
                        .telephoneNo(rs.getString("telephoneNo"))
                        .pickupLocation(rs.getString("pickupLocation"))
                        .destination(rs.getString("destination"))
                        .bookingDate(rs.getDate("bookingDate"))
                        .customerId(rs.getString("customerId"))
                        .vehicleType(rs.getString("vehicleType"))
                        .vehicleId(rs.getString("vehicleId"))
                        .vehicleBrand(rs.getString("vehicleBrand"))
                        .vehicleModel(rs.getString("vehicleModel"))
                        .seat(rs.getString("seat"))
                        .build());
            }
        }
        return bookings;
    }

    public boolean deleteBooking(String bookingId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM bookings WHERE bookingId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bookingId);
            return stmt.executeUpdate() > 0;
        }
    }
}
