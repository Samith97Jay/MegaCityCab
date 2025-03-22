package com.megacitycab.controller;

import com.megacitycab.service.BillingService;
import com.megacitycab.service.BillingService.BillingInfo;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;

/**
 * BillingServlet acts as the controller for handling billing-related requests.
 * <p>
 * It retrieves the booking number from the client request, and if only the
 * booking number is provided (no distance), it will just load the basic
 * booking details without calculating fare. Once the user submits a manual
 * distance, the servlet calculates the total fare and shows the breakdown.
 * </p>
 */

@WebServlet("/BillingServlet")
public class BillingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Singleton instance of the BillingService used for billing calculations.
    private BillingService billingService;

    @Override
    public void init() throws ServletException {
        super.init();
        // Obtain the singleton instance of BillingService.
        billingService = BillingService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(BillingServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BillingServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(BillingServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BillingServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Processes both GET and POST requests.
     *
     * @param request  the HttpServletRequest object that contains the request from the client.
     * @param response the HttpServletResponse object that contains the response to the client.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException      if an I/O error occurs.
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {

        // Retrieve the booking number from request parameters.
        String bookingId = request.getParameter("bookingId");
        BillingInfo billingInfo = null;

        if (bookingId != null && !bookingId.trim().isEmpty()) {
            // Check if a manual distance value has been provided.
            String manualDistanceStr = request.getParameter("manualDistance");

            if (manualDistanceStr != null && !manualDistanceStr.trim().isEmpty()) {
                // The user has submitted a distance for calculation
                try {
                    double manualDistance = Double.parseDouble(manualDistanceStr);
                    billingInfo = billingService.calculateBill(bookingId, manualDistance);
                    if (billingInfo == null) {
                        request.setAttribute("errorMessage", "No booking found for: " + bookingId);
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("errorMessage", "Invalid distance value provided.");
                }
            } else {
                // No manual distance yet, just load basic booking info (distance=0, total=0)
                billingInfo = billingService.getBookingDetails(bookingId);
                if (billingInfo == null) {
                    request.setAttribute("errorMessage", "Booking not found for booking Id: " + bookingId);
                }
            }
        } else {
            // Handle cases where booking number is missing.
            request.setAttribute("errorMessage", "Booking Id is required.");
        }

        // Set the billing info attribute for use in the view (JSP).
        request.setAttribute("billingInfo", billingInfo);

        // Forward the request to billing.jsp to display the result.
        request.getRequestDispatcher("billing.jsp").forward(request, response);
    }
}
