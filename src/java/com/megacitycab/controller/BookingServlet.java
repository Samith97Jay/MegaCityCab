package com.megacitycab.controller;

import com.megacitycab.model.Booking;
import com.megacitycab.service.BookingService;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookingService bookingService;
    

    @Override
    public void init() throws ServletException {
        super.init();
        bookingService = BookingService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        try {
            if ("list".equalsIgnoreCase(action)) {
                List<Booking> bookings = bookingService.getAllBookings();
                request.setAttribute("bookings", bookings);
                request.getRequestDispatcher("viewbookings.jsp").forward(request, response);
                
            } else if ("delete".equalsIgnoreCase(action)) {
                String bookingId = request.getParameter("bookingId");
                if (bookingId != null && !bookingId.trim().isEmpty()) {
                    boolean isDeleted = bookingService.deleteBooking(bookingId);
                    request.setAttribute("message", isDeleted ? "Booking deleted successfully." : "Failed to delete booking.");
                } else {
                    request.setAttribute("errorMessage", "Booking ID is required to delete booking.");
                }
                
                request.setAttribute("bookings", bookingService.getAllBookings());
                request.getRequestDispatcher("viewbookings.jsp").forward(request, response);
                
            } else {
                String bookingNumber = request.getParameter("bookingId");
                if (bookingNumber != null && !bookingNumber.trim().isEmpty()) {
                    Booking booking = bookingService.getBooking(bookingNumber);
                    request.setAttribute("booking", booking);
                } else {
                    request.setAttribute("errorMessage", "Booking ID is required to view booking details.");
                }
                request.getRequestDispatcher("viewbookings.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException e) {
            Logger.getLogger(BookingServlet.class.getName()).log(Level.SEVERE, null, e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookingNumber = request.getParameter("bookingId");
        String customerName = request.getParameter("name");
        String customerAddress = request.getParameter("customerAddress");
        String pickupLocation = request.getParameter("pickupLocation");
        String telephoneNo = request.getParameter("phoneno");
        String destination = request.getParameter("destination");
        String bookingDateStr = request.getParameter("bookingDate");
        String customerId = request.getParameter("custId");
        
        String vehicleType = request.getParameter("vehicleType");
        String vehicleId = request.getParameter("vehicleId");
        String vehicleBrand = request.getParameter("vehicleBrand");
        String vehicleModel = request.getParameter("vehicleModel");
        String seat = request.getParameter("seat");

        if (customerName == null || customerName.trim().isEmpty() ||
            telephoneNo == null || telephoneNo.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Customer details are missing. Please provide required information.");
            request.getRequestDispatcher("booking.jsp").forward(request, response);
            return;
        }

        Date bookingDate = parseDate(bookingDateStr);
        
        Booking booking = new Booking.Builder(bookingNumber)
                .customerName(customerName)
                .customerAddress(customerAddress)
                .pickupLocation(pickupLocation)
                .telephoneNo(telephoneNo)
                .destination(destination)
                .bookingDate(new java.sql.Date(bookingDate.getTime()))
                .customerId(customerId)
                .vehicleType(vehicleType)
                .vehicleId(vehicleId)
                .vehicleBrand(vehicleBrand)
                .vehicleModel(vehicleModel)
                .seat(seat)
                .build();

        try {
            boolean isAdded = bookingService.addBooking(booking);
            request.setAttribute("message", isAdded ? "Booking added successfully." + bookingNumber : "Failed to add booking.");
        } catch (ClassNotFoundException e) {
            Logger.getLogger(BookingServlet.class.getName()).log(Level.SEVERE, null, e);
            request.setAttribute("errorMessage", "An error occurred while adding booking.");
        }
        
        request.getRequestDispatcher("booking.jsp").forward(request, response);
    }

    private Date parseDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return new Date();
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            return new Date();
        }
    }
}
