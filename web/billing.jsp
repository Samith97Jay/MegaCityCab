<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.megacitycab.service.BillingService.BillingInfo" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Mega City Cab - Billing Details</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        /* Reset defaults */
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f2f2f2;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 20px;
            min-height: 100vh;
        }
        /* Navigation styling */
        header.navbar {
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            background: #fff;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            padding: 1rem 2rem;
            display: flex;
            align-items: center;
            justify-content: space-between;
            z-index: 1000;
        }
        header.navbar .brand {
            font-size: 1.2rem;
            font-weight: bold;
            color: #333;
            text-decoration: none;
        }
        header.navbar .nav-links {
            display: flex;
            gap: 1rem;
        }
        header.navbar .nav-links a {
            color: #5563DE;
            text-decoration: none;
            font-size: 1rem;
            transition: color 0.3s;
        }
        header.navbar .nav-links a:hover {
            color: #444;
        }
        /* Style for the Dashboard link with icon */
        header.navbar .nav-links a[href="dashboard.jsp"] {
          position: relative;
          padding-left: 30px; /* space for the icon */
          background: url('https://img.icons8.com/?size=100&id=S5D5w5vFLhYp&format=png&color=000000') no-repeat left center;
          background-size: 20px 20px;
        }
        /* Tooltip styling on hover */
        header.navbar .nav-links a[href="dashboard.jsp"]:hover::after {
          content: 'Dashboard';
          position: absolute;
          bottom: -30px;
          left: 50%;
          transform: translateX(-50%);
          background: #333;
          color: #fff;
          padding: 4px 8px;
          border-radius: 4px;
          font-size: 0.85rem;
          white-space: nowrap;
          opacity: 0;
          transition: opacity 0.3s;
          pointer-events: none;
        }
        /* Make tooltip visible on hover */
        header.navbar .nav-links a[href="dashboard.jsp"]:hover::after {
          opacity: 1;
        }
        
        /* Style for the Dashboard link with icon */
        header.navbar .nav-links a[href="index.jsp"] {
          position: relative;
          padding-left: 30px;
          background: url('https://img.icons8.com/?size=100&id=111473&format=png&color=000000') no-repeat left center;
          background-size: 20px 20px;
        }
        /* Tooltip styling on hover */
        header.navbar .nav-links a[href="index.jsp"]:hover::after {
          content: 'Dashboard';
          position: absolute;
          bottom: -30px;
          left: 50%;
          transform: translateX(-50%);
          background: #333;
          color: #fff;
          padding: 4px 8px;
          border-radius: 4px;
          font-size: 0.85rem;
          white-space: nowrap;
          opacity: 0;
          transition: opacity 0.3s;
          pointer-events: none;
        }
        /* Make tooltip visible on hover */
        header.navbar .nav-links a[href="index.jsp"]:hover::after {
          opacity: 1;
        }
        
        .billing-container {
            background: #fff;
            padding: 2rem;
            border-radius: 8px;
            max-width: 500px;
            width: 100%;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            text-align: center;
            margin-top: 80px;
        }
        .billing-container h1 {
            margin-bottom: 1rem;
            color: #333;
        }
        .billing-details {
            margin: 1.5rem 0;
            font-size: 1.1rem;
            color: #555;
            line-height: 1.6;
        }
        .billing-details span.label {
            font-weight: bold;
            color: #333;
        }
        .back-link {
            display: inline-block;
            margin-top: 1.5rem;
            text-decoration: none;
            font-size: 1rem;
            color: #5563DE;
            transition: color 0.3s ease;
        }
        .back-link:hover {
            color: #444;
        }
        .error-message {
            color: red;
            margin-bottom: 1rem;
            font-size: 1rem;
        }
        form {
            margin-bottom: 1.5rem;
        }
        form label {
            font-weight: bold;
            margin-right: 0.5rem;
        }
        form input[type="text"] {
            padding: 0.5rem;
            width: 70%;
            margin-bottom: 1rem;
        }
        form button {
            padding: 0.5rem 1rem;
            background-color: #5563DE;
            color: #fff;
            border: none;
            cursor: pointer;
            border-radius: 4px;
        }
        form button:hover {
            background-color: #444;
        }
        .calculation-breakdown {
            text-align: left;
            margin: 1.5rem 0;
            font-size: 1rem;
            color: #555;
            line-height: 1.6;
        }
        .calculation-breakdown p span {
            font-weight: bold;
        }
        button.print-button {
            padding: 0.5rem 1rem;
            background-color: #28a745;
            color: #fff;
            border: none;
            cursor: pointer;
            border-radius: 4px;
            margin-top: 1rem;
        }
        button.print-button:hover {
            background-color: #218838;
        }

        /* Hide unwanted elements on print */
        @media print {
            /* Hide navigation, background, and print button */
            header.navbar, .back-link, button.print-button {
                display: none !important;
            }
            body {
                background: #fff !important;
                margin: 0;
                padding: 0;
            }
            .billing-container {
                box-shadow: none;
                border: none;
                margin: 0;
                width: 100%;
            }
        }
    </style>
</head>
<body>
    <!-- Navigation Header -->
    <header class="navbar">
        <a class="brand" href="dashboard.jsp">Mega City Cab</a>
        <nav class="nav-links">
            <a href="booking.jsp">New Booking</a>
            <a href="BookingServlet?action=list">View Bookings</a>
            <a href="register.jsp">Registration</a>
            <a href="viewEdt.jsp">View</a>
            <a href="billing.jsp">Billing</a>
            <a href="help.jsp">Help</a>
            <a href="dashboard.jsp"></a>
            <a href="index.jsp"></a>
        </nav>
    </header>

    <div class="billing-container">
        <h1>Mega City Cab - Billing Details</h1>

        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            BillingInfo billingInfo = (BillingInfo) request.getAttribute("billingInfo");
        %>

        <!-- Display error message if available -->
        <% if (errorMessage != null) { %>
            <div class="error-message"><%= errorMessage %></div>
        <% } %>

        <!-- If no billing info yet, show booking number form -->
        <% if (billingInfo == null) { %>
            <form action="BillingServlet" method="post">
                <label for="bookingNumber">Enter Booking Number:</label>
                <input type="text" id="bookingNumber" name="bookingNumber" placeholder="e.g., BKN146045" required>
                <br/>
                <button type="submit">Get Booking Details</button>
            </form>
        <% } else { 
            // We have at least the booking details
        %>
            <div class="billing-details">
                <p><span class="label">Booking Number:</span> <%= billingInfo.getBookingNumber() %></p>
                <p><span class="label">Customer Pickup Location:</span> <%= billingInfo.getpickupLocation() %></p>
                <p><span class="label">Destination:</span> <%= billingInfo.getDestination() %></p>
            </div>

            <!-- If distance is 0, it means user hasn't updated it yet. Show the form to update distance. -->
            <% if (billingInfo.getDistance() == 0) { %>
                <h3>Update Distance</h3>
                <form action="BillingServlet" method="post">
                    <input type="hidden" name="bookingNumber" value="<%= billingInfo.getBookingNumber() %>">
                    <label for="manualDistance">Enter Distance (miles):</label>
                    <input type="text" id="manualDistance" name="manualDistance" placeholder="e.g., 12.5" required>
                    <br/>
                    <button type="submit">Update Bill</button>
                </form>
            <% } else { 
                // Distance > 0, show full calculation breakdown
            %>
                <div class="calculation-breakdown">
                    <p><span>Distance:</span> <%= String.format("%.2f", billingInfo.getDistance()) %> miles</p>
                    <p><span>Base Fare:</span> $<%= String.format("%.2f", billingInfo.getBaseFare()) %></p>
                    <p><span>Distance Cost:</span> $<%= String.format("%.2f", billingInfo.getDistanceCost()) %></p>
                    <p><span>Tax (10%):</span> $<%= String.format("%.2f", billingInfo.getTaxAmount()) %></p>
                    <hr>
                    <p><span>Total Bill:</span> $<%= String.format("%.2f", billingInfo.getTotalAmount()) %></p>
                </div>

                <!-- Print button at the bottom of the final bill -->
                <button class="print-button" onclick="window.print()">Print Bill</button>
            <% } %>
        <% } %>

        <br>
        <a class="back-link" href="index.jsp">Return to Home</a>
    </div>
</body>
</html>
