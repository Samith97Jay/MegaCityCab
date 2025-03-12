<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Generate a driver ID in the format DIxxxx, where xxxx is a random four-digit number
    int randomNum = (int)(Math.random() * 9000) + 1000; // ensures a 4-digit number
    String generatedDriverId = "DI" + randomNum;
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Driver Registration</title>
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
        
        /* Driver Registration Container Styling */
        .driver-reg-container {
            background: #fff;
            padding: 2rem;
            border-radius: 8px;
            width: 400px;
            max-width: 100%;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            margin-top: 80px; /* to avoid overlap with the fixed navbar */
        }
        .driver-reg-container h2 {
            text-align: center;
            margin-bottom: 1.5rem;
            color: #333;
        }
        .driver-reg-container form {
            display: flex;
            flex-direction: column;
        }
        .driver-reg-container label {
            font-weight: bold;
            margin-bottom: 0.5rem;
            color: #555;
        }
        .driver-reg-container input[type="text"],
        .driver-reg-container input[type="number"],
        .driver-reg-container select {
            padding: 0.75rem;
            margin-bottom: 1rem;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 1rem;
        }
        .driver-reg-container input[readonly] {
            background: #e9e9e9;
            cursor: not-allowed;
        }
        .driver-reg-container button {
            padding: 0.75rem;
            background: #5563DE;
            color: #fff;
            border: none;
            border-radius: 4px;
            font-size: 1rem;
            cursor: pointer;
            transition: background 0.3s ease;
        }
        .driver-reg-container button:hover {
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
</head>
<body>
    <!-- Navigation Header -->
  

    <!-- Driver Registration Container -->
    <div class="driver-reg-container">
        <h2>Driver Registration</h2>
        <% 
            String message = (String) request.getAttribute("message");
            if (message != null && !message.trim().isEmpty()) { 
        %>
            <div class="message"><%= message %></div>
        <% 
            }
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null && !errorMessage.trim().isEmpty()) { 
        %>
            <div class="error-message"><%= errorMessage %></div>
        <% } %>
        <form action="DriverController" method="post">
            <label for="driverId">Driver ID:</label>
            <input type="text" id="driverId" name="driverId" value="<%= generatedDriverId %>" readonly required>

            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required placeholder="Enter Name">

            <label for="licenseNumber">License Number:</label>
            <input type="text" id="licenseNumber" name="licenseNumber" required placeholder="Enter Driving License Number">

            <label for="phone">Contact Number:</label>
            <input type="text" id="phone" name="phone" required placeholder="Enter Contact Number">

            <label for="address">Address:</label>
            <input type="text" id="address" name="address" required placeholder="Enter Address">

            <label for="assignedCarId">Assigned Vehicle ID:</label>
            <input type="text" id="assignedCarId" name="assignedCarId" placeholder="Enter Vehicle Registration ID ">

            <button type="submit">Register</button>
        </form>
        <a class="back-link" href="register.jsp">Return Back</a>
    </div>
</body>
</html>
