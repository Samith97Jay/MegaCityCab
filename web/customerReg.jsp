<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.text.SimpleDateFormat, java.util.Date" %>
<%
    // Generate a unique customer ID using the current date and time
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    String timestamp = sdf.format(new Date());
    String custId = "CUS00_" + timestamp;
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Registration</title>
    <style>
 
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
            min-height: 100vh;
            padding: 20px;
        }
 
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
        
        .booking-container {
            background: #fff;
            padding: 2rem;
            border-radius: 8px;
            width: 400px;
            max-width: 100%;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            margin-top: 80px;
        }
        .booking-container h2 {
            text-align: center;
            margin-bottom: 1.5rem;
            color: #333;
        }
        .booking-container form {
            display: flex;
            flex-direction: column;
        }
        .booking-container label {
            font-weight: bold;
            margin-bottom: 0.5rem;
            color: #555;
        }
        .booking-container input[type="text"],
        .booking-container input[type="date"] {
            padding: 0.75rem;
            margin-bottom: 1rem;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 1rem;
        }
        .booking-container input[readonly] {
            background: #e9ecef;
        }
        .booking-container button {
            padding: 0.75rem;
            background: #5563DE;
            color: #fff;
            border: none;
            border-radius: 4px;
            font-size: 1rem;
            cursor: pointer;
            transition: background 0.3s ease;
        }
        .booking-container button:hover {
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
        <form action="CustomerServlet" method="post">
            <!-- Display the auto-generated registration number (read-only) -->
            <label for="custId">Registration Number</label>
            <input type="text" id="custId" name="custIdDisplay" value="<%=custId%>" readonly>
            <!-- Hidden field to submit the registration number -->
            <input type="hidden" name="custId" value="<%=custId%>">
            
            <label for="name">Name</label>
            <input type="text" id="name" name="name" required>
            
            <label for="address">Address</label>
            <input type="text" id="address" name="address">
            
            <label for="nic">NIC Number</label>
            <input type="text" id="nic" name="nic" pattern="[0-9]{9}[VvXx]|[0-9]{12}" required >
            
            
            <label for="phoneno">Contact Number</label>
            <input type="text" id="phoneno" name="phoneno" input type="text" id="phoneno" name="phoneno" pattern="[0-9]{10}" required>
           
            
            <button type="submit">Register</button>
        </form>
            <a class="back-link" href="register.jsp">Return Back</a>
    </div>
</body>
</html>
