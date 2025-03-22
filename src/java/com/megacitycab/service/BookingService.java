package com.megacitycab.service;

import com.megacitycab.dao.BookingDAO;
import com.megacitycab.model.Booking;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides booking services for the Mega City Cab system.
 */
public class BookingService {
      private static final Logger LOGGER = Logger.getLogger(BookingService.class.getName());
    private static BookingService instance;
    private BookingDAO bookingDAO;
    private static final int DEFAULT_ID = 1000;

    private BookingService() {
        this.bookingDAO = BookingDAO.getInstance();
    }

    public static synchronized BookingService getInstance() {
        if (instance == null) {
            instance = new BookingService();
        }
        return instance;
    }

    public synchronized String generateBookingId() {
        int lastId = DEFAULT_ID;
        String filePath = System.getProperty("user.dir") + File.separator + "lastBookingId.txt";

        // Read the last saved ID from the file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String lastLine = reader.readLine();
            if (lastLine != null) {
                lastId = Integer.parseInt(lastLine);
            }
        } catch (IOException e) {
            LOGGER.warning("Could not read booking ID file, starting from default: " + e.getMessage());
        }

        // Increment the last customer ID
        int newId = lastId + 1;

        // Save the updated ID back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(String.valueOf(newId));
        } catch (IOException e) {
            LOGGER.warning("Could not write booking ID file: " + e.getMessage());
        }

        // Generate a timestamp
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = sdf.format(new Date());

        // Generate Booking ID
        return "BK" + timestamp + "_" + newId;
    }

    public boolean addBooking(Booking booking) throws ClassNotFoundException {
        try {
            boolean added = bookingDAO.addBooking(booking);
            if (!added) {
                LOGGER.warning("BookingService: addBooking returned false for booking: " + booking);
            }
            return added;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error adding booking: " + booking, ex);
            return false;
        }
    }

    public Booking getBooking(String bookingId) throws ClassNotFoundException {
        try {
            return bookingDAO.getBooking(bookingId);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving booking with ID: " + bookingId, ex);
            return null;
        }
    }

    public List<Booking> getAllBookings() throws ClassNotFoundException {
        try {
            return bookingDAO.getAllBookings();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving all bookings.", ex);
            return null;
        }
    }

    public boolean deleteBooking(String bookingId) throws ClassNotFoundException {
        try {
            boolean deleted = bookingDAO.deleteBooking(bookingId);
            if (!deleted) {
                LOGGER.warning("BookingService: deleteBooking returned false for bookingId: " + bookingId);
            }
            return deleted;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error deleting booking with ID: " + bookingId, ex);
            return false;
        }
    }
}
