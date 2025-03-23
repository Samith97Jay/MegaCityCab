<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.megacitycab.service.BillingService.BillingInfo" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Mega City Cab - Billing Details</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
     body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 0;
}

.container {
    width: 60%;
    margin: 50px auto;
    background: white;
    padding: 20px;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
}

h2 {
    text-align: center;
    color: #333;
}

/* Navigation Bar */
.navbar {
    background-color: #333;
    overflow: hidden;
    padding: 10px 0;
}

.navbar a {
    float: left;
    display: block;
    color: white;
    text-align: center;
    padding: 14px 20px;
    text-decoration: none;
}

.navbar a:hover {
    background-color: #ffcc00;
    color: black;
}

/* Form Styles */
form {
    display: flex;
    flex-direction: column;
}

label {
    font-weight: bold;
    margin-top: 10px;
}

input[type="text"], input[type="number"], input[type="date"] {
    width: 100%;
    padding: 8px;
    margin-top: 5px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

/* Button Styles */
button {
    width: 100%;
    padding: 10px;
    background-color: #ffcc00;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 16px;
    margin-top: 15px;
}

button:hover {
    background-color: #e6b800;
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

            <a href="retrive.jsp">View</a>
            <a href="billing.jsp">Billing</a>
            <a href="guide.jsp">Guide</a>
            <a href="dashboard.jsp">Home</a>
           
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
                <label for="bookingId">Enter Booking Number:</label>
                <input type="text" id="bookingId" name="bookingId" placeholder="" required>
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
                    <input type="hidden" name="bookingId" value="<%= billingInfo.getBookingNumber() %>">
                    <label for="manualDistance">Enter Distance (miles):</label>
                    <input type="text" id="manualDistance" name="manualDistance" placeholder="20" required>
                    <br/>
                    <button type="submit">Update Bill</button>
                </form>
            <% } else { 
                // Distance > 0, show full calculation breakdown
            %>
               <div class="calculation-breakdown">
    <p><span>Distance:</span> <%= String.format("%.2f", billingInfo.getDistance()) %> miles</p>
    <p><span>Base Fare:</span> LKR <%= String.format("%.2f", billingInfo.getBaseFare()) %></p>
    <p><span>Distance Cost:</span> LKR <%= String.format("%.2f", billingInfo.getDistanceCost()) %></p>
    <p><span>Tax (10%):</span> LKR <%= String.format("%.2f", billingInfo.getTaxAmount()) %></p>
    <hr>
    <p><span>Total Bill:</span> LKR <%= String.format("%.2f", billingInfo.getTotalAmount()) %></p>
</div>    

                <!-- Print button at the bottom of the final bill -->
                <button class="print-button" onclick="window.print()">Print Bill</button>
            <% } %>
        <% } %>

        <br>
        <a class="back-link" href="dashboard.jsp">Return to Home</a>
    </div>
</body>
</html>
