<%-- 
    Document   : customerReg
    Created on : Mar 11, 2025, 4:09:02 PM
    Author     : OZT00090
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.Random, java.text.SimpleDateFormat, java.util.Date" %>
<%
        // Get current date and time as a formatted string (yyyyMMddHHmmss)
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        // Generate a random 5-digit number (10000 - 99999)
        int randomNum = 10000 + new Random().nextInt(90000);

        // Combine timestamp and random number
        String registrationNumber = "CUS_" + timestamp + "_" + randomNum;
    %>
<!DOCTYPE html>
<html>
<head>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Mega City Cab</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
                    <li class="nav-item"><a class="nav-link" href="bookCar.jsp">Book a Car</a></li>
                    <li class="nav-item"><a class="nav-link" href="viewBookings.jsp">View Bookings</a></li>
                    <li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
                    <li class="nav-item"><a class="nav-link" href="signup.jsp">Sign Up</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <meta charset="UTF-8">
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Customer Registration</title>
  
   
   
</head>
<body>
    <!-- Navigation Header -->
   
    
    <div class="booking-container">
        <h2>Customer Registration</h2>
        <%-- Display dynamic messages if present --%>
        <%
            String message = (String) request.getAttribute("message");
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (message != null) {
        %>
            <div class="message"><%= message %></div>
        <% } else if (errorMessage != null) { %>
            <div class="error-message"><%= errorMessage %></div>
        <% } %>
        <form action="CustomerRegistrationServlet" method="post">
            <!-- Display the auto-generated registration number (read-only) -->
            <label for="registrationNumber">Registration Number</label>
            <input type="text" id="registrationNumber" name="registrationNumberDisplay" value="<%= registrationNumber %>" readonly>
            <!-- Hidden field to submit the registration number -->
            <input type="hidden" name="registrationNumber" value="<%= registrationNumber %>">
            
            <label for="name">Name</label>
            <input type="text" id="name" name="name" required>
            
            <label for="address">Address</label>
            <input type="text" id="address" name="address">
            
            <label for="nic">NIC Number</label>
            <input type="text" id="nic" name="nic">
            
            <label for="telephone">Contact Number</label>
            <input type="text" id="telephone" name="telephone">
            
            <button type="submit">Register</button>
        </form>
            <a class="back-link" href="register.jsp">Return Back</a>
    </div>
</body>
</html>

