<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Search &amp; Edit Record</title>
    <style>
       * {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
}
body {
    font-family: 'Poppins', sans-serif;
    background: url('img/car-bg.jpg') no-repeat center center/cover;
    display: flex;
    justify-content: center;
    align-items: flex-start;
    padding: 20px;
    height: 100vh;
}

/* Container Styling */
.container {
    background: rgba(255, 255, 255, 0.95);
    padding: 2rem;
    border-radius: 10px;
    width: 500px;
    max-width: 100%;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
    margin-top: 100px;
}

/* Titles */
h2 {
    text-align: center;
    margin-bottom: 1.5rem;
    color: #333;
}

/* Search Section */
.search-section {
    text-align: center;
    margin-bottom: 2rem;
}
.search-section input[type="text"] {
    width: 75%;
    padding: 0.75rem;
    font-size: 1rem;
    border: 1px solid #ccc;
    border-radius: 5px;
}
.search-section button {
    padding: 0.75rem 1.5rem;
    background: #ffcc00;
    color: #333;
    border: none;
    border-radius: 5px;
    font-size: 1rem;
    margin-left: 10px;
    cursor: pointer;
    transition: background 0.3s ease;
}
.search-section button:hover {
    background: #e6b800;
}

/* Form Section */
.form-section {
    margin-top: 20px;
}
.form-section label {
    font-weight: bold;
    margin-bottom: 0.5rem;
    display: block;
    color: #555;
}
.form-section input[type="text"],
.form-section input[type="number"] {
    width: 100%;
    padding: 0.75rem;
    margin-bottom: 1rem;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 1rem;
}
.form-section input[readonly] {
    background: #e9e9e9;
}

/* Button Styling */
.form-section button {
    width: 100%;
    padding: 0.8rem;
    background: #ffcc00;
    color: #333;
    border: none;
    border-radius: 5px;
    font-size: 1rem;
    cursor: pointer;
    transition: background 0.3s ease;
}
.form-section button:hover {
    background: #e6b800;
}

/* Messages */
.message, .errorMessage {
    text-align: center;
    margin-bottom: 1rem;
    font-size: 1rem;
}
.message {
    color: green;
}
.errorMessage {
    color: red;
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
        /* Make tooltip visible on hover */
        header.navbar .nav-links a[href="index.jsp"]:hover::after {
          opacity: 1;
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
            <a href="retrive.jsp">View</a>
            <a href="billing.jsp">Billing</a>
            <a href="guide.jsp">Guide</a>
            <a href="dashboard.jsp">Home</a>
        </nav>
    </header>
    
<div class="container">
    <h2>Search Record</h2>
    <!-- Search Section -->
    <div class="search-section">
        <form action="RetrivalServlet" method="get">
            <input type="text" name="searchId" placeholder="Enter ID" required />
            <button type="submit">Search</button>
        </form>
    </div>

    <!-- Display messages if available -->
    <% if(request.getAttribute("message") != null) { %>
        <div class="message"><%= request.getAttribute("message") %></div>
    <% } %>
    <% if(request.getAttribute("errorMessage") != null) { %>
        <div class="errorMessage"><%= request.getAttribute("errorMessage") %></div>
    <% } %>

    <!-- Conditionally display edit form based on search result -->
      <%
        String recordType = (String) request.getAttribute("recordType");
        if(recordType != null) {
            if(recordType.equals("customer")) {
                com.megacitycab.model.Customer customer = (com.megacitycab.model.Customer) request.getAttribute("customer");
    %>
                <h2>Retrive and Update Customer Details</h2>
                <div class="form-section">
                    <form action="RetrivalServlet" method="post">
                        <label>Registration Number</label>
                        <input type="text" name="custId" value="<%= customer.getCustId() %>" readonly />
                        
                        <label>Customer Name</label>
                        <input type="text" name="name" value="<%= customer.getName() %>" required />
                        
                        <label>Customer Address</label>
                        <input type="text" name="address" value="<%= customer.getAddress() %>" required />
                        
                        <label>NIC Number</label>
                        <input type="text" name="nic" value="<%= customer.getNic() %>" required />
                        
                        <label>Contact Number</label>
                        <input type="text" name="phoneno" value="<%= customer.getPhoneno() %>" required />
                        
                        <button type="submit">Update Customer Details</button>
                    </form>
                        
                        
                </div>
    <%
            } else if(recordType.equals("driver")) {
                com.megacitycab.model.Driver driver = (com.megacitycab.model.Driver) request.getAttribute("driver");
    %>
                <h2>Retrive and Update Driver Details</h2>
                <div class="form-section">
                    <form action="RetrivalServlet" method="post">
                        <label>Driver ID</label>
                        <input type="text" name="driverId" value="<%= driver.getDriverId() %>" readonly />
                        
                        <label>Driver Name</label>
                        <input type="text" name="name" value="<%= driver.getName() %>" required />
                        
                        <label>Driver License Number</label>
                        <input type="text" name="lisce" value="<%= driver.getLisce() %>" required />
                        
                        <label>Driver Contact Number</label>
                        <input type="text" name="phoneno" value="<%= driver.getPhoneno() %>" required />
                        
                        <label>Driver Address</label>
                        <input type="text" name="address" value="<%= driver.getAddress() %>" required />
                        
                        <label>Assigned Vehicle ID</label>
                        <input type="text" name="assignedVehicleId" value="<%= driver.getAssignedVehicleId() %>" required />
                        
                        <button type="submit">Update Driver Details</button>
                    </form>
                        
                        
                </div>
    <%
            } else if(recordType.equals("vehicle")) {
                com.megacitycab.model.Vehicle vehicle = (com.megacitycab.model.Vehicle) request.getAttribute("vehicle");
    %>
                <h2>Retrive and Update Vehicle Details</h2>
                <div class="form-section">
                    <form action="RetrivalServlet" method="post">
                        <label>Vehicle Registration ID</label>
                        <input type="text" name="vehicleId" value="<%= vehicle.getVehicleId() %>" readonly />
                        
                        <label>Vehicle Type</label>
                        <input type="text" name="vehicleType" value="<%= vehicle.getVehicleType() %>" required />
                        
                        <label>License Plate</label>
                        <input type="text" name="lisce" value="<%= vehicle.getLisce() %>" required />
                        
                        <label>Vehicle Brand</label>
                        <input type="text" name="vehicleBrand" value="<%= vehicle.getVehicleBrand() %>" required />
                        
                        <label>Vehicle Model</label>
                        <input type="text" name="vehicleModel" value="<%= vehicle.getVehicleModel() %>" required />
                        
                        <label>Vehicle Color</label>
                        <input type="text" name="color" value="<%= vehicle.getColor() %>" required />
                        
                        <label>Seat </label>
                        <input type="number" name="seat" value="<%= vehicle.getSeat() %>" required />
                        
                        <button type="submit">Update Vehicle Details</button>
                    </form>
                </div>
    <%
            }
        }
    %>
</div>
</body>
</html>
