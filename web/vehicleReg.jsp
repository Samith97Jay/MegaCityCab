<%-- 
    Document   : vehicleReg
    Created on : Mar 11, 2025, 7:31:08 PM
    Author     : OZT00090
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Vehicle Registration</title>
    <style>
        /* Global Reset and Body Styling */
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f2f2f2;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
            padding: 20px;
        }
        /* Navigation Styling */
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
        header.navbar .vehicleBrand {
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
          bottom: -30px; /* position tooltip below the link */
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
          padding-left: 30px; /* space for the icon */
          background: url('https://img.icons8.com/?size=100&id=111473&format=png&color=000000') no-repeat left center;
          background-size: 20px 20px;
        }

        /* Tooltip styling on hover */
        header.navbar .nav-links a[href="index.jsp"]:hover::after {
          content: 'Dashboard';
          position: absolute;
          bottom: -30px; /* position tooltip below the link */
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
        
        /* Registration Container Styling */
        .registration-container {
            background: #fff;
            padding: 2rem;
            border-radius: 8px;
            width: 400px;
            max-width: 100%;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            margin-top: 80px; /* To avoid overlap with fixed navbar */
        }
        .registration-container h2 {
            text-align: center;
            margin-bottom: 1.5rem;
            color: #333;
        }
        .registration-container form {
            display: flex;
            flex-direction: column;
        }
        .registration-container label {
            font-weight: bold;
            margin-bottom: 0.5rem;
            color: #555;
        }
        .registration-container select,
        .registration-container input[type="text"],
        .registration-container input[type="number"] {
            padding: 0.75rem;
            margin-bottom: 1rem;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 1rem;
        }
        .registration-container input[readonly] {
            background: #e9e9e9;
            cursor: not-allowed;
        }
        .registration-container button {
            padding: 0.75rem;
            background: #5563DE;
            color: #fff;
            border: none;
            border-radius: 4px;
            font-size: 1rem;
            cursor: pointer;
            transition: background 0.3s ease;
        }
        .registration-container button:hover {
            background: #444fb7;
        }
        .message {
            text-align: center;
            margin-bottom: 1rem;
            font-size: 1rem;
            color: green;
        }
        .error-message {
            text-align: center;
            margin-bottom: 1rem;
            font-size: 1rem;
            color: red;
        }
        .back-link {
            display: block;
            text-align: center;
            margin-top: 2rem;
            text-decoration: none;
            font-size: 1rem;
            color: #5563DE;
            transition: color 0.3s ease;
        }
        .back-link:hover {
            color: #444;
        }
    </style>
    <script>
        // Function to generate a vehicle registration ID based on selected vehicle type
        function generateVehicleId() {
            var vehicleTypeSelect = document.getElementById("vehicleType");
            var vehicleIdField = document.getElementById("vehicleId");
            var type = vehicleTypeSelect.value;
            var prefix = "";
            if(type === "Car"){
                prefix = "C";
            } else if(type === "SUV"){
                prefix = "S";
            } else if(type === "Van"){
                prefix = "V";
            
            }
            // Generate a random number between 1000 and 9999
            var randomNum = Math.floor(Math.random() * 9000) + 1000;
            vehicleIdField.value = prefix + randomNum;
        }

        // When the page loads, ensure a registration ID is generated for the default selection
        window.onload = function(){
            generateVehicleId();
        };
    </script>
</head>
<body>

    <header class="navbar">
        <a class="vehicleBrand" href="dashboard.jsp">Mega City Cab</a>
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

    <!-- Registration Form Container -->
    <div class="registration-container">
        <h2>Vehicle Registration</h2>
        <%-- Display dynamic messages if present --%>
        <% 
            String message = (String) request.getAttribute("message");
            String errorMessage = (String) request.getAttribute("errorMessage");
            if(message != null) { 
        %>
            <div class="message"><%= message %></div>
        <% } else if(errorMessage != null) { %>
            <div class="error-message"><%= errorMessage %></div>
        <% } %>
        
        <form action="VehicleServlet" method="post">
          
            <label for="vehicleType">Vehicle Type</label>
            <select id="vehicleType" name="vehicleType" onchange="generateVehicleId()" required>
                <option value="Car">Car</option>
                <option value="SUV">SUV</option>
                <option value="Van">Van</option>
               
            </select>
            
            <!-- Auto-generated Vehicle Registration ID (read-only) -->
            <label for="vehicleId">Vehicle Registration ID</label>
            <input type="text" id="vehicleId" name="vehicleId" readonly placeholder="Auto-generated ID">

            <!-- Other Fields -->
            <label for="licensePlate">Vehicle License Plate Number</label>
            <input type="text" id="licensePlate" name="licensePlate" placeholder="Enter License Plate">
            
            <label for="vehicleBrand">Vehicle Brand</label>
            <input type="text" id="vehicleBrand" name="vehicleBrand" placeholder="Enter Vehicle Brand">
            
            <label for="vehicleModel">Vehicle Model</label>
            <input type="text" id="vehicleModel" name="vehicleModel" placeholder="Enter Vehicle Model">
            
            <label for="color">Vehicle Color</label>
            <input type="text" id="color" name="color" placeholder="Enter Vehicle Color">
            
            <label for="seat">Vehicle Seating Capacity</label>
            <input type="number" id="seat" name="seat" placeholder="Enter Seat" min="1">
            
            <button type="submit">Register Vehicle</button>
            
            <a class="back-link" href="dashboard.jsp">Return Back</a>
        </form>
    </div>
</body>
</html>

