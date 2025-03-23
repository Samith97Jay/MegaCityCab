<%@page import="com.megacitycab.service.VehicleService"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.io.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    VehicleService vehicleService = new VehicleService();
    
    // Generate a timestamp-based vehicle ID
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    String timestamp = sdf.format(new Date());
    String vehicleId = "V" + timestamp;
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Vehicle Registration</title>
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
        .form-container {
            background: rgba(255, 255, 255, 0.95);
            padding: 2rem;
            border-radius: 10px;
            width: 450px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
            margin: auto;
            text-align: left;
            margin-top: 100px;
        }
        .form-container h2 {
            text-align: center;
            color: #333;
        }
        .form-container input[type="text"],
        .form-container input[type="number"],
        .form-container select {
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
    </style>
</head>
<body>
    <header class="navbar">
        <a class="brand" href="dashboard.jsp">Mega City Cab</a>
    </header>
    <div class="form-container">
        <h2>Vehicle Registration</h2>
        
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
        
        
        
        <form action="VehicleServlet" method="post">
            <label for="vehicleType">Vehicle Type</label>
            <select id="vehicleType" name="vehicleType" required>
                <option value="Car">Car</option>
                <option value="SUV">SUV</option>
                <option value="Van">Van</option>
            </select>
            
               
          <label for="vehicleId" id="vehicleIdLabel" style="display: none;">Vehicle Id</label>
<input type="text" id="vehicleId" name="vehicleId" value="<%=vehicleId%>" readonly style="display: none;">
<input type="hidden" name="vehicleId" value="<%=vehicleId%>">
        
   
<script>
    window.onload = function() {
        // Hide the Customer ID label and input field
        document.getElementById("vehicleIdLabel").style.display = "none";
        document.getElementById("vehicleId").style.display = "none";
    };
</script>



            <label for="lisce">Vehicle Number</label>
            <input type="text" id="lisce" name="lisce" required>
            
            <label for="vehicleBrand">Vehicle Brand</label>
            <input type="text" id="vehicleBrand" name="vehicleBrand" required>
            
            <label for="vehicleModel">Vehicle Model</label>
            <input type="text" id="vehicleModel" name="vehicleModel" required>
            
            <label for="color">Vehicle Color</label>
            <input type="text" id="color" name="color" required>
            
            <label for="seat">Vehicle Seat</label>
            <input type="number" id="seat" name="seat" min="1" required>
            
            <button type="submit">Register Vehicle</button>
        </form>
    </div>
</body>
</html>
