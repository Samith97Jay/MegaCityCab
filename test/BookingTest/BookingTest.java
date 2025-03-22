package BookingTest;

import com.megacitycab.dao.BookingDAO;
import com.megacitycab.model.Booking;
import java.sql.SQLException;
import org.junit.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class BookingTest {

       private static BookingDAO bookingDAO;

    @BeforeClass
    public static void setUpBeforeClass() {
        bookingDAO = BookingDAO.getInstance();
    }

    @Test
    public void testAddBooking() throws SQLException, ClassNotFoundException {
        Booking booking = createBooking("BK20250322172325_1043", "John Doe");
        boolean isInserted = bookingDAO.addBooking(booking);
        assertTrue("Booking should be added successfully", isInserted);
    }

    @Test
    public void testRetrieveBooking() throws SQLException, ClassNotFoundException {
        Booking booking = createBooking("BK20250322172325_1044", "Jane Doe");
        bookingDAO.addBooking(booking);
        Booking retrieved = bookingDAO.getBooking("BK20250322172325_1045");
        assertNotNull("Booking should be retrieved", retrieved);
        assertEquals("Customer name should match", "Jane Doe", retrieved.getCustomerName());
    }

    @Test
    public void testRetrieveAllBookings() throws SQLException, ClassNotFoundException {
        Booking booking1 = createBooking("BK20250322172325_1046", "Alice Smith");
        Booking booking2 = createBooking("BK20250322172325_1047", "Bob Johnson");
        bookingDAO.addBooking(booking1);
        bookingDAO.addBooking(booking2);
        List<Booking> bookings = bookingDAO.getAllBookings();
        assertNotNull("Bookings list should not be null", bookings);
        assertFalse("Bookings list should not be empty", bookings.isEmpty());
    }

    @Test
    public void testDeleteBooking() throws SQLException, ClassNotFoundException {
        Booking booking = createBooking("BK20250322172325_1050", "Michael Brown");
        bookingDAO.addBooking(booking);
        boolean isDeleted = bookingDAO.deleteBooking("BK20250322172325_1048");
        assertTrue("Booking should be deleted successfully", isDeleted);
    }

    @Test
    public void testDuplicateBooking() throws SQLException, ClassNotFoundException {
        Booking booking = createBooking("BK20250322172325_1049", "Chris Evans");
        bookingDAO.addBooking(booking);
        boolean isInsertedAgain = bookingDAO.addBooking(booking);
        assertFalse("Duplicate booking should not be allowed", isInsertedAgain);
    }

    private Booking createBooking(String bookingId, String customerName) {
        try {
            return new Booking.Builder(bookingId)
                    .customerName(customerName)
                    .customerAddress("123 Street, City")
                    .telephoneNo("0712345678")
                    .pickupLocation("Downtown")
                    .destination("Airport")
                    .bookingDate(new SimpleDateFormat("yyyy-MM-dd").parse("2025-03-25"))
                    .customerId("CUS1001")
                    .vehicleType("Sedan")
                    .vehicleId("VH-12345")
                    .vehicleBrand("Toyota")
                    .vehicleModel("Corolla")
                    .seat("4")
                    .build();
        } catch (Exception e) {
            return null;
        }
    }
}

