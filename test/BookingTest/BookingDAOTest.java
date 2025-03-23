/*package BookingTest;

import com.megacitycab.dao.BookingDAO;
import com.megacitycab.model.Booking;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class BookingDAOTest {

    private BookingDAO bookingDAO;

    @Before
    public void setUp() {
        bookingDAO = BookingDAO.getInstance(); // Get singleton instance
    }

    @Test
    public void testBookingInsertion_Successful() throws SQLException, ClassNotFoundException {
        Booking booking = createBooking("'BK20250322170930_1041', 'CUS20250322044847_1003', '0748484894', 'Colombo', 'Negombo', '2025-03-22', 'Car', 'C1224', 'Toyota', 'Yaris', '4', ' fghjk', NULL", "John Doe", "123 Main St", "0712345678", "City Center",
                "2025-03-15", "CUS20250322044847_1003", "Car", "VH-12345", "Toyota", "Corolla", "5");

        boolean result = bookingDAO.addBooking(booking);
        assertTrue("Booking should be inserted successfully", result);
    }

    @Test
    public void testRetrieveBookingByNumber() throws SQLException, ClassNotFoundException {
        Booking booking = createBooking("BK20250322170930_1043", "Alice Smith", "456 Park Ave", "0776543210", "Airport",
                "2025-03-20", "CUS20250322044847_1005", "SUV", "VH-56789", "Honda", "CRV", "7");
        bookingDAO.addBooking(booking);

        Booking retrievedBooking = bookingDAO.getBooking("BK20250322170930_1043");
        assertNotNull("Booking should be retrieved", retrievedBooking);
        assertEquals("BKN10002", retrievedBooking.getBookingId());
    }

    @Test
    public void testDuplicateBookingNumber_ShouldFail() throws SQLException, ClassNotFoundException {
        Booking booking1 = createBooking("BK20250322170930_1045", "Michael Lee", "789 Elm St", "0711122233", "Train Station",
                "2025-03-25", "CUS20250322044847_1003", "Van", "VH-99887", "Ford", "Transit", "8");
        Booking booking2 = createBooking("BKN10003", "Emily Clark", "321 Oak St", "0788997766", "Downtown",
                "2025-03-28", "CUS202503220448476_1003", "Sedan", "VH-55443", "BMW", "5 Series", "4");

        bookingDAO.addBooking(booking1);
        boolean result = bookingDAO.addBooking(booking2);
        
        assertFalse("Duplicate booking number should not be allowed", result);
    }

    @Test
    public void testRetrieveAllBookings() throws SQLException, ClassNotFoundException {
        Booking booking1 = createBooking("BK20250322170930_1045", "Daniel Carter", "555 Birch Rd", "0755566677", "Suburbs",
                "2025-03-30", "CUS_1005", "Minivan", "VH-77788", "Kia", "Carnival", "7");
        Booking booking2 = createBooking("BKN10005", "Sarah White", "777 Pine St", "0712341234", "Beach",
                "2025-04-02", "CUS_1006", "Luxury", "VH-22334", "Mercedes", "E-Class", "5");

        bookingDAO.addBooking(booking1);
        bookingDAO.addBooking(booking2);

        List<Booking> bookings = bookingDAO.getAllBookings();
        assertNotNull("Bookings list should not be null", bookings);
        assertTrue("At least two bookings should be retrieved", bookings.size() >= 2);
    }

    @Test
    public void testDeleteBooking_Successful() throws SQLException, ClassNotFoundException {
        Booking booking = createBooking("BKN10006", "Liam Johnson", "888 Cedar St", "0723344556", "Office",
                "2025-04-05", "CUS_1007", "Hatchback", "VH-11223", "Hyundai", "i20", "4");
        bookingDAO.addBooking(booking);

        boolean result = bookingDAO.deleteBooking("BKN10006");
        assertTrue("Booking should be deleted successfully", result);
    }

    @Test
    public void testDeleteNonExistentBooking_ShouldFail() throws SQLException, ClassNotFoundException {
        boolean result = bookingDAO.deleteBooking("BKN99999");
        assertFalse("Non-existent booking should not be deleted", result);
    }

    // Helper method to create a Booking instance
    private Booking createBooking(String bookingNumber, String customerName, String customerAddress,
                                  String telephoneNumber, String destination, String bookingDateStr,
                                  String customerRegNo, String vehicleType, String vehicleRegId,
                                  String brand, String model, String seating, String customerId) {
        Date bookingDate;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            bookingDate = sdf.parse(bookingDateStr);
        } catch (Exception ex) {
            bookingDate = new Date();
        }
        String vehicleId = null;

        return new Booking.Builder(bookingNumber)
                .customerName(customerName)
                .customerAddress(customerAddress)
                .destination(destination)
                .bookingDate(bookingDate)
                .customerId(customerId)
                .vehicleType(vehicleType)
                .vehicleId(vehicleId)
                .vbrand(brand)
                .vmodel(model)
                .vseating(seating)
                .build();
    }
}*/