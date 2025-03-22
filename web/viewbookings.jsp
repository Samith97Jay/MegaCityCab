<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.megacitycab.model.Booking" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Mega City Cab - Booking Details</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        /* Reset default styles */
       body {
    font-family: 'Poppins', sans-serif;
    margin: 0;
    padding: 0;
    background: url('img/car-bg.jpg') no-repeat center center/cover;
    height: 100vh;
    display: flex;
    flex-direction: column;
    align-items: center;
}

/* Navigation Bar */
.navbar {
    background: rgba(0, 0, 0, 0.9);
    padding: 15px 20px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    width: 100%;
    position: fixed;
    top: 0;
    left: 0;
    z-index: 1000;
}

.navbar .brand {
    font-size: 1.8rem;
    font-weight: bold;
    color: #ffcc00;
    text-decoration: none;
    transition: 0.3s;
}

.navbar .brand:hover {
    color: #ffd633;
}

/* Nav Links */
.nav-links {
    display: flex;
    gap: 20px;
}

.nav-links a {
    color: #ffffff;
    font-size: 1rem;
    font-weight: 600;
    text-decoration: none;
    padding: 10px 15px;
    position: relative;
    transition: color 0.3s ease;
}

.nav-links a::after {
    content: "";
    display: block;
    width: 0;
    height: 3px;
    background: #ffcc00;
    position: absolute;
    bottom: -5px;
    left: 50%;
    transform: translateX(-50%);
    transition: width 0.3s ease;
}

.nav-links a:hover {
    color: #ffcc00;
}

.nav-links a:hover::after {
    width: 100%;
}

/* Booking Container */
.container {
    max-width: 800px;
    background: rgba(255, 255, 255, 0.95);
    padding: 2rem;
    border-radius: 10px;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
    margin: auto;
    text-align: left;
    margin-top: 100px;
}

h1 {
    text-align: center;
    color: #333;
    margin-bottom: 1.5rem;
}

/* Booking Details */
.booking-details p {
    margin-bottom: 0.75rem;
    line-height: 1.6;
    font-size: 1rem;
    color: #555;
}

.label {
    font-weight: bold;
    color: #333;
}

/* Booking Cards */
.booking-card {
    background: #e8f0fe;
    padding: 1rem;
    border-radius: 6px;
    margin-bottom: 1rem;
    cursor: pointer;
    transition: background 0.3s ease;
}

.booking-card:hover {
    background: #d0e1fd;
}

.booking-info {
    display: flex;
    justify-content: space-between;
}

.booking-info span {
    font-size: 1rem;
    color: #333;
}

/* Button & Links */
.back-link {
    display: block;
    text-align: center;
    margin-top: 2rem;
    text-decoration: none;
    font-size: 1rem;
    color: #ffcc00;
    transition: color 0.3s ease;
}

.back-link:hover {
    color: #e6b800;
}

/* Footer */
footer {
    background: rgba(0, 0, 0, 0.9);
    padding: 15px;
    text-align: center;
    width: 100%;
    position: fixed;
    bottom: 0;
    left: 0;
}

footer p {
    margin: 0;
    color: #fff;
}
    </style>
</head>
<body>

    <header class="navbar">
        <a class="brand" href="dashboard.jsp">Mega City Cab</a>
        <nav class="nav-links">
            <a href="booking.jsp">New Booking</a>
            <a href="BookingServlet?action=list">View Bookings</a>
            <a href="register.jsp">Registration</a>
            <a href="retrive.jsp">View</a>
            <a href="billing.jsp">Billing</a>
            <a href="guide.jsp">Guide</a>
            <a href="dashboard.jsp">Home</a>
        </nav>
    </header>
 
<div class="container">
    <%
       
        List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
        Booking booking = (Booking) request.getAttribute("booking");
        String errorMessage = (String) request.getAttribute("errorMessage");
        
        if (errorMessage != null) {
    %>
        <p style="text-align:center; color:red;"><%= errorMessage %></p>
    <%
        } else if (bookings != null) { 
    %>
        <h1>Bookings List</h1>
        <% for (Booking b : bookings) { %>
           
            <a href="BookingServlet?bookingId=<%= b.getBookingId() %>" style="text-decoration:none;">
                <div class="booking-card">
                    <div class="booking-info">
                        <span><strong>Booking Number:</strong> <%= b.getBookingId() %></span>
                        <span><strong>Customer Registration Number/NIC:</strong> <%= b.getCustomerId() %></span>
                    </div>
                </div>
            </a>
        <% } %>
    <%
        } else if (booking != null) { 
    %>
        <h1>Booking Details</h1>
        <div class="booking-details">
            <p><span class="label">Booking Number:</span> <%= booking.getBookingId() %></p>
            <p><span class="label">Customer Registration Number/NIC:</span> <%= booking.getCustomerId() %></p>
            <p><span class="label">Customer Name:</span> <%= booking.getCustomerName() %></p>
            <p><span class="label">Contact Number:</span> <%= booking.getTelephoneNo() %></p>
            <p><span class="label">Vehicle Type:</span> <%= booking.getVehicleType() %></p>
            <p><span class="label">Assigned Vehicle Registration Number:</span> <%= booking.getVehicleId() %></p>
            <p><span class="label">Vehicle Brand:</span> <%= booking.getVehicleBrand() %></p>
            <p><span class="label">Vehicle Model:</span> <%= booking.getVehicleModel() %></p>
            <p><span class="label">Vehicle Seating Capacity:</span> <%= booking.getSeat() %></p>
            <p><span class="label">Pickup Location:</span> <%= booking.getPickupLocation() %></p>
            <p><span class="label">Destination:</span> <%= booking.getDestination() %></p>
            <p>
            <span class="label">Booking Date:</span>
            <%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(booking.getBookingDate()) %>
            </p>

        </div>
    <%
        } else {
    %>
        <p style="text-align:center; color:red;">No booking details found.</p>
    <%
        }
    %>
    <a class="back-link" href="BookingServlet?action=list">Return to Back</a>
    <a
</div>
</body>
</html>
