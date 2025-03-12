<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Mega City Cab - New Booking</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        /* Reset defaults */
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
        /* Navigation styling */
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
        /* Booking container styling */
        .booking-container {
            background: #fff;
            padding: 2rem;
            border-radius: 8px;
            width: 400px;
            max-width: 100%;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            margin-top: 75px;
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
        .booking-container input[type="date"],
        .booking-container select {
            padding: 0.75rem;
            margin-bottom: 1rem;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 1rem;
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
    </style>
    <script>
        // Function to auto-generate a booking number in the format "BKN" followed by 6 digits.
        function generateBookingNumber() {
            var randomNum = Math.floor(100000 + Math.random() * 900000);
            document.getElementById("bookingNumber").value = "BKN" + randomNum;
        }

        // Function to fetch customer details based on Registration Number or NIC.
        function fetchCustomerDetails() {
            var regNo = document.getElementById("customerRegNo").value;
            if (regNo.trim() === "") {
                return;
            }
            var url = "<%= request.getContextPath() %>/CustomerRegistrationServlet?action=getCustomer&customerRegNo=" + encodeURIComponent(regNo);
            fetch(url)
                .then(response => {
                    if (!response.ok) {
                        throw new Error("HTTP error, status = " + response.status);
                    }
                    return response.json();
                })
                .then(data => {
                    if (data && data.customerName) {
                        document.getElementById("customerName").value = data.customerName;
                        document.getElementById("telephoneNumber").value = data.telephoneNumber;
                    } else {
                        document.getElementById("customerName").value = "";
                        document.getElementById("telephoneNumber").value = "";
                        alert("Customer not found");
                    }
                })
                .catch(error => {
                    console.error("Error fetching customer details:", error);
                    alert("Error fetching customer details: " + error.message);
                });
        }

        // Function to fetch vehicle details based on the selected Vehicle Type.
        // This function now calls a servlet that checks for vehicles not already booked.
        function fetchVehicleDetails() {
            var vehicleType = document.getElementById("vehicleType").value;
            if (vehicleType.trim() === "") {
                document.getElementById("vehicleRegId").value = "";
                document.getElementById("brand").value = "";
                document.getElementById("model").value = "";
                document.getElementById("seatingCapacity").value = "";
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
                        document.getElementById("vehicleRegId").value = "";
                        document.getElementById("brand").value = "";
                        document.getElementById("model").value = "";
                        document.getElementById("seatingCapacity").value = "";
                    } else if (data && data.vehicleRegId) {
                        // Populate the fields with the available vehicle details.
                        document.getElementById("vehicleRegId").value = data.vehicleRegId;
                        document.getElementById("brand").value = data.brand;
                        document.getElementById("model").value = data.model;
                        document.getElementById("seatingCapacity").value = data.seatingCapacity;
                    } else {
                        // If no vehicle was returned, clear the fields and alert.
                        document.getElementById("vehicleRegId").value = "";
                        document.getElementById("brand").value = "";
                        document.getElementById("model").value = "";
                        document.getElementById("seatingCapacity").value = "";
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
</head>
<body>
    <!-- Navigation Header -->


    <!-- Booking container -->
    <div class="booking-container">
        <h2>New Booking</h2>
        <% 
            String message = (String) request.getAttribute("message");
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (message != null) { 
        %>
            <div class="message"><%= message %></div>
        <% } else if (errorMessage != null) { %>
            <div class="error-message"><%= errorMessage %></div>
        <% } %>
        <form action="BookingServlet" method="post">
            <!-- Auto-generated Booking Number -->
            <label for="bookingNumber">Booking Number</label>
            <input type="text" id="bookingNumber" name="bookingNumber" readonly placeholder="Auto generated Booking Number">

            <!-- Customer Registration Number / NIC -->
            <label for="customerRegNo">Customer Registration Number / NIC</label>
            <input type="text" id="customerRegNo" name="customerRegNo" placeholder="Registration Number or NIC" required onblur="fetchCustomerDetails()">

            <!-- Auto-filled Customer Name -->
            <label for="customerName">Customer Name</label>
            <input type="text" id="customerName" name="customerName" placeholder="Customer Name" readonly>

            <!-- Auto-filled Telephone Number -->
            <label for="telephoneNumber">Contact Number</label>
            <input type="text" id="telephoneNumber" name="telephoneNumber" placeholder="Contact Number" readonly>

            <!-- Vehicle Details Section -->
            <label for="vehicleType">Vehicle Type</label>
            <select id="vehicleType" name="vehicleType" required onchange="fetchVehicleDetails()">
                <option value="">Select Vehicle Type</option>
                <option value="Car">Car</option>
                <option value="SUV">SUV</option>
                <option value="Van">Van</option>
                <option value="Bus">Bus</option>
            </select>

            <label for="vehicleRegId">Vehicle Registration ID</label>
            <input type="text" id="vehicleRegId" name="vehicleRegId" placeholder="Vehicle Registration ID" readonly>

            <label for="brand">Vehicle Brand</label>
            <input type="text" id="brand" name="brand" placeholder="Brand" readonly>

            <label for="model">Vehicle Model</label>
            <input type="text" id="model" name="model" placeholder="Model" readonly>

            <label for="seatingCapacity">Vehicle Seating Capacity</label>
            <input type="text" id="seatingCapacity" name="seatingCapacity" placeholder="Seating Capacity" readonly>

            <!-- Pickup Location -->
            <label for="pickupLocation">Pickup Location</label>
            <input type="text" id="pickupLocation" name="pickupLocation" placeholder="Enter Pickup Location" required>

            <!-- Destination -->
            <label for="destination">Destination</label>
            <input type="text" id="destination" name="destination" placeholder="Enter Destination" required>

            <!-- Booking Date -->
            <label for="bookingDate">Booking Date</label>
            <input type="date" id="bookingDate" name="bookingDate">

            <button type="submit">Reserve a Booking</button>
        </form>
    </div>
</body>
</html>
