<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.io.FileWriter"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Define the file path to store the last generated number
    String filePath = application.getRealPath("/") + "lastDriverId.txt";
    int lastId = 1000; // Default starting value if the file does not exist

    try {
        File file = new File(filePath);
        if (file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String lastLine = reader.readLine();
            if (lastLine != null) {
                lastId = Integer.parseInt(lastLine); // Read last saved ID
            }
            reader.close();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    // Increment the last customer ID
    int newId = lastId + 1;

    // Save the updated ID back to the file
    try {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(String.valueOf(newId));
        writer.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

    // Generate a timestamp
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    String timestamp = sdf.format(new Date());

    // Generate Customer ID (combining incrementing number and timestamp)
    String driverId = "D" + timestamp + "_" + newId ;
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Driver Registration</title>
       <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            margin: 0;
            padding: 0;
            background: url('img/car-bg.jpg') no-repeat center center/cover;
            height: 100vh;
            display: flex;
            flex-direction: column;
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

        /* Main Content */
        .main-content {
            margin-top: 100px;
            text-align: center;
            color: white;
        }

        .form-container {
            background: rgba(255, 255, 255, 0.95);
            padding: 2rem;
            border-radius: 10px;
            width: 450px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
            margin: auto;
            text-align: left;
        }

        .form-container h2 {
            text-align: center;
            color: #333;
        }

        .form-container input[type="text"] {
            padding: 0.8rem;
            margin-bottom: 1rem;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 1rem;
            width: 100%;
        }

        .form-container button {
            padding: 0.8rem;
            background: #ffcc00;
            color: #333;
            border: none;
            border-radius: 5px;
            font-size: 1rem;
            cursor: pointer;
            width: 100%;
            transition: background 0.3s ease;
        }

        .form-container button:hover {
            background: #e6b800;
        }

        .back-link {
            display: block;
            text-align: center;
            margin-top: 1.5rem;
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
    <!-- Navigation Header -->
  

    <!-- Driver Registration Container -->
    <div class="form-container">
        <h2>Driver Registration</h2>
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

           <form action="DriverServlet" method="post" id="autoSubmitForm">
            <!-- Auto-generated Customer ID -->
          <label for="driverId" id="driverIdLabel" style="display: none;">Driver ID</label>
<input type="text" id="driverId" name="driverId" value="<%=driverId%>" readonly style="display: none;">
<input type="hidden" name="driverId" value="<%=driverId%>">

<script>
    window.onload = function() {
        // Hide the Customer ID label and input field
        document.getElementById("custIdLabel").style.display = "none";
        document.getElementById("custId").style.display = "none";
    };
</script>

            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required placeholder="Enter Name">

            <label for="lisce">License Number:</label>
            <input type="text" id="licenseNumber" name="lisce" required placeholder="Enter Driving License Number">

            <label for="phoneno">Contact Number:</label>
            <input type="text" id="phone" name="phoneno" required placeholder="Enter Contact Number">

            <label for="address">Address:</label>
            <input type="text" id="address" name="address" required placeholder="Enter Address">

            <label for="assignedVehicleId">Assigned Vehicle ID:</label>
            <input type="text" id="assignedVehicleId" name="assignedVehicleId" placeholder="Enter Vehicle Registration ID ">

            <button type="submit">Register</button>
        </form>
        <a class="back-link" href="dashboard.jsp">Return Back</a>
    </div>
</body>
</html>   
