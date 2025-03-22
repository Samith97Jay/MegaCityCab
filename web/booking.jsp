<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.io.FileWriter"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.File"%>
<%@page import="com.megacitycab.service.BookingService"%>
<%@page import="com.megacitycab.model.Booking"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.io.IOException"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // Define the file path to store the last generated number
    String filePath = application.getRealPath("/") + "lastBookingId.txt";
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
    String bookingId = "BK" + timestamp + "_" + newId ;
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Registration</title>
     <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="styles.css">
     
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

/* Booking Form Container */
.form-container {
    background: rgba(255, 255, 255, 0.95);
    padding: 2rem;
    border-radius: 10px;
    width: 450px;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
    margin: auto;
    margin-top: 120px;
    text-align: left;
}

.form-container h2 {
    text-align: center;
    color: #333;
    margin-bottom: 1rem;
}

.form-container label {
    font-weight: bold;
    display: block;
    margin-bottom: 5px;
    color: #333;
}

.form-container input[type="text"],
.form-container input[type="date"],
.form-container input[type="time"],
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

/* Back Link */
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

footer p {
    margin: 0;
    color: #fff;
}
    </style>
    <script>
       

        function fetchCustomerDetails() {
            var custId = document.getElementById("custId").value;
            if (custId.trim() === "") {
                return;
            }
            var url = "<%= request.getContextPath() %>/CustomerServlet?action=getCustomer&custId=" + encodeURIComponent(custId);
            fetch(url)
                .then(response => {
                    if (!response.ok) {
                        throw new Error("HTTP error, status = " + response.status);
                    }
                    return response.json();
                })
                .then(data => {
                    if (data && data.name) {
                        document.getElementById("name").value = data.name;
                        document.getElementById("phoneno").value = data.phoneno;
                    } else {
                        document.getElementById("name").value = "";
                        document.getElementById("phoneno").value = "";
                        alert("Customer not found");
                    }
                })
                .catch(error => {
                    console.error("Error fetching customer details:", error);
                    alert("Error fetching customer details: " + error.message);
                });
        }

        function fetchVehicleDetails() {
            var vehicleType = document.getElementById("vehicleType").value;
            if (vehicleType.trim() === "") {
                document.getElementById("vehicleId").value = "";
                document.getElementById("vehicleBrand").value = "";
                document.getElementById("vehicleModel").value = "";
                document.getElementById("seat").value = "";
                return;
            }
            // Calls VehicleServlet with an action to get an available and not-yet-booked vehicle.
            var url = "<%= request.getContextPath() %>/VehicleServlet?action=getAvailableNotBookedVehicle&vehicleType=" + encodeURIComponent(vehicleType);
            fetch(url)
                .then(response => {
                    if (!response.ok) {
                        throw new Error("HTTP error, status = " + response.status);
                    }
                    return response.json();
                })
                .then(data => {
                    // If the response includes a message indicating all vehicles are booked, show an alert.
                    if (data && data.message && data.message === "All vehicles are booked") {
                        alert("All vehicles are booked for the selected type.");
                        document.getElementById("vehicleId").value = "";
                        document.getElementById("vehicleBrand").value = "";
                        document.getElementById("vehicleModel").value = "";
                        document.getElementById("seat").value = "";
                    } else if (data && data.vehicleId) {
                        // Populate the fields with the available vehicle details.
                        document.getElementById("vehicleId").value = data.vehicleId;
                        document.getElementById("vehicleBrand").value = data.vehicleBrand;
                        document.getElementById("vehicleModel").value = data.vehicleModel;
                        document.getElementById("seat").value = data.seat;
                    } else {
                        // If no vehicle was returned, clear the fields and alert.
                        document.getElementById("vehicleId").value = "";
                        document.getElementById("vehicleBrand").value = "";
                        document.getElementById("vehicleModel").value = "";
                        document.getElementById("seat").value = "";
                        alert("No available vehicle found for the selected type.");
                    }
                })
                .catch(error => {
                    console.error("Error fetching vehicle details:", error);
                    alert("Error fetching vehicle details: " + error.message);
                });
        }
        

        window.onload = function() {
            generateBookingNumber();
        }
    </script>
    <script>
        window.onload = function() {
            // Check if the form was already submitted
            if (!sessionStorage.getItem("formSubmitted")) {
                document.getElementById("autoSubmitForm").submit();
                sessionStorage.setItem("formSubmitted", "true"); // Prevent duplicate submission
                    }
        };
    </script>
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
    
    <div class="form-container">
        <h2>New Booking</h2>
        
        <%-- Display dynamic messages if present --%>
        <% String message = (String) request.getAttribute("message"); %>
        <% if (message != null) { %>
            <div class="message"><%= message %></div>
        <% } %>
        
         <form action="BookingServlet" method="post" id="autoSubmitForm">
            <!-- Auto-generated Customer ID -->
          <label for="bookingId" id="bookingIdLabel" style="display: none;">Booking Id</label>
<input type="text" id="bookingId" name="bookingId" value="<%=bookingId%>" readonly style="display: none;">
<input type="hidden" name="bookingId" value="<%=bookingId%>">

<script>
    window.onload = function() {
        // Hide the Customer ID label and input field
        document.getElementById("bookingIdLabel").style.display = "none";
        document.getElementById("bookingId").style.display = "none";
    };
</script>

          
            
            <label for="custId">Customer ID / NIC</label>
            <input type="text" id="custId" name="custId" required onchange="fetchCustomerDetails()">
            
            <label for="name">Customer Name</label>
            <input type="text" id="name" name="name" readonly>
            
            <label for="phoneno">Contact Number</label>
            <input type="text" id="phoneno" name="phoneno" readonly>
            
            <label for="vehicleType">Vehicle Type</label>
            <select id="vehicleType" name="vehicleType" required onchange="fetchVehicleDetails()">
                
                <option value="">Select Vehicle Type</option>
                <option value="Car">Car</option>
                <option value="SUV">SUV</option>
                <option value="Van">Van</option>
            </select>
            
             <label for="vehicleModel">Vehicle Model</label>
            <input type="text" id="vehicleModel" name="vehicleModel" readonly>
            
            <label for="vehicleBrand">Vehicle Brand</label>
            <input type="text" id="vehicleBrand" name="vehicleBrand" readonly>
            
            <label for="vehicleId">Vehicle ID</label>
            <input type="text" id="vehicleId" name="vehicleId" readonly>
            
            <label for="seat">Vehicle Seating Capacity</label>
            <input type="text" id="seat" name="seat" readonly>
            
            
            <label for="pickupLocation">Pickup Location</label>
            <input type="text" id="pickupLocation" name="pickupLocation" required>
            
            <label for="destination">Destination</label>
            <input type="text" id="destination" name="destination" required>
          
            <label for="bookingDate">Booking Date</label>
            <input type="date" id="bookingDate" name="bookingDate">
           
       <button type="submit">Reserve Booking</button>
           
        </form>

    <!-- Footer -->
    <footer class="footer">
        <p>&copy; 2025 Mega City Cab. All Rights Reserved.</p>
    </footer>
    
</body>
</html>
