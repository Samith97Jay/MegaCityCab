
import com.megacitycab.dao.BookingDAO;
import com.megacitycab.model.Booking;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author OZT00090
 */
public class BookingTesting {
    
    public static void main(String[] args) {
        BookingDAO bookingDAO = BookingDAO.getInstance(); // Get singleton instance

        // Test Case 1: Insert a sample booking for testing
        System.out.println("\nTest Case 1: Insert Sample Booking");
        if (addSampleBooking("BKN12345", "John Doe", "123 Main St", "0712345678", "Colombo",
                "2025-03-20", "CUS_1001", "Car", "VH-78945", "Honda", "Civic", "5", bookingDAO)) {
            System.out.println("✅ Sample Booking Inserted Successfully - Passed");
        } else {
            System.out.println("❌ Sample Booking Insertion Failed - Failed");
        }

        // Test Case 2: Retrieve All Bookings (Should List at Least One Booking)
        System.out.println("\nTest Case 2: Retrieve All Bookings");
        if (retrieveAllBookingsTest(bookingDAO)) {
            System.out.println("✅ Retrieved All Bookings Successfully - Passed");
        } else {
            System.out.println("❌ Failed to Retrieve All Bookings - Failed");
        }

        // Test Case 3: Retrieve Specific Booking
        System.out.println("\nTest Case 3: Retrieve Specific Booking");
        if (retrieveSpecificBookingTest("BKN12345", bookingDAO)) {
            System.out.println("✅ Booking Retrieval Successful - Passed");
        } else {
            System.out.println("❌ Booking Retrieval Failed - Failed");
        }

        // Test Case 4: Attempt to Retrieve a Non-Existent Booking (Should Fail)
        System.out.println("\nTest Case 4: Retrieve Non-Existent Booking");
        if (!retrieveSpecificBookingTest("BKN99999", bookingDAO)) {
            System.out.println("✅ Non-Existent Booking Not Found - Passed");
        } else {
            System.out.println("❌ Non-Existent Booking Found - Failed");
        }

        // Test Case 5: Delete Booking and Verify Deletion
        System.out.println("\nTest Case 5: Delete Booking");
        if (deleteBookingTest("BKN12345", bookingDAO)) {
            System.out.println("✅ Booking Deletion Successful - Passed");
        } else {
            System.out.println("❌ Booking Deletion Failed - Failed");
        }

        // Final Validation: Ensure Booking is Deleted
        System.out.println("\nFinal Validation: Ensure Deleted Booking is Not Retrieved");
        if (!retrieveSpecificBookingTest("BKN12345", bookingDAO)) {
            System.out.println("✅ Deleted Booking Not Found - Passed");
        } else {
            System.out.println("❌ Deleted Booking Still Exists - Failed");
        }
    }

    // Function to Insert a Sample Booking
    public static boolean addSampleBooking(String bookingNumber, String customerName, String customerAddress,
                                           String telephoneNumber, String destination, String bookingDateStr,
                                           String customerRegNo, String vehicleType, String vehicleRegId,
                                           String brand, String model, String seating, BookingDAO bookingDAO) {
        Date bookingDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            bookingDate = (Date) sdf.parse(bookingDateStr);
        } catch (ParseException ex) {
            bookingDate = new Date();
        }

        Booking booking = new Booking.Builder(bookingNumber)
                .customerName(customerName)
                .customerAddress(customerAddress)
                .telephoneNumber(telephoneNumber)
                .destination(destination)
                .bookingDate(bookingDate)
                .customerRegNo(customerRegNo)
                .vehicleType(vehicleType)
                .vehicleRegId(vehicleRegId)
                .vbrand(brand)
                .vmodel(model)
                .vseating(seating)
                .build();

        try {
            return bookingDAO.addBooking(booking);
        } catch (Exception e) {
            System.out.println("❌ Error during sample booking insertion: " + e.getMessage());
            return false;
        }
    }

    // Function to Retrieve All Bookings
    public static boolean retrieveAllBookingsTest(BookingDAO bookingDAO) {
        try {
            List<Booking> bookings = bookingDAO.getAllBookings();
            if (bookings != null && !bookings.isEmpty()) {
                System.out.println("ℹ️ Retrieved " + bookings.size() + " Booking(s):");
                for (Booking booking : bookings) {
                    System.out.println(booking);
                }
                return true;
            } else {
                System.out.println("ℹ️ No bookings found.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("❌ Error retrieving all bookings: " + e.getMessage());
            return false;
        }
    }

    // Function to Retrieve a Specific Booking
    public static boolean retrieveSpecificBookingTest(String bookingNumber, BookingDAO bookingDAO) {
        try {
            Booking booking = bookingDAO.getBooking(bookingNumber);
            if (booking != null) {
                System.out.println("ℹ️ Retrieved Booking Details: " + booking);
                return true;
            } else {
                System.out.println("ℹ️ Booking not found with number: " + bookingNumber);
                return false;
            }
        } catch (Exception e) {
            System.out.println("❌ Error during booking retrieval: " + e.getMessage());
            return false;
        }
    }

    // Function to Delete a Booking
    public static boolean deleteBookingTest(String bookingNumber, BookingDAO bookingDAO) {
        try {
            return bookingDAO.deleteBooking(bookingNumber);
        } catch (Exception e) {
            System.out.println("❌ Error during booking deletion: " + e.getMessage());
            return false;
        }
    }
    
}
