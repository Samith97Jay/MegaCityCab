<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Mega City Cab - New Booking</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>

   .booking-container {
    background: rgba(0, 0, 0, 0.85);
    color: #fff;
    padding: 2rem;
    border-radius: 8px;
    width: 400px;
    max-width: 100%;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.5);
    margin-top: 75px;
    position: relative;
    text-align: center;
}

.booking-container h2 {
    color: #ffcc00;
    margin-bottom: 1.5rem;
}

.booking-container form {
    display: flex;
    flex-direction: column;
}

.booking-container label {
    font-weight: bold;
    margin-bottom: 0.5rem;
    text-align: left;
    display: block;
}

.booking-container input[type="text"],
.booking-container input[type="date"],
.booking-container select {
    padding: 0.75rem;
    margin-bottom: 1rem;
    border: 1px solid #ffcc00;
    border-radius: 4px;
    font-size: 1rem;
    background: #222;
    color: #fff;
}

.booking-container input:focus, 
.booking-container select:focus {
    outline: none;
    border-color: #ffd633;
}

.booking-container button {
    padding: 0.75rem;
    background: #ffcc00;
    color: #333;
    border: none;
    border-radius: 4px;
    font-size: 1rem;
    cursor: pointer;
    transition: background 0.3s ease;
    font-weight: bold;
}

.booking-container button:hover {
    background: #ffd633;
}

.message {
    text-align: center;
    margin-bottom: 1rem;
    font-size: 1rem;
    color: #4caf50;
}

.error-message {
    text-align: center;
    margin-bottom: 1rem;
    font-size: 1rem;
    color: #ff4d4d;
    
    .booking-container {
    background: rgba(255, 255, 255, 0.95);
    padding: 2rem;
    border-radius: 8px;
    width: 400px;
    max-width: 100%;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
    
    /* Centering the form */
    position: absolute;
    top: 50%;
    left: 50%;
    right: 50%;
    transform: translate(-50%, -50%);
}
    
    
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
            var regNo = document.getElementById("custId").value;
            if (regNo.trim() === "") {
                return;
            }
            var url = "<%= request.getContextPath() %>/CustomerServlet?action=getCustomer&custId=" + encodeURIComponent(regNo);
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
                        document.getElementById("phoneno").value = data.phoneno;
                    } else {
                        document.getElementById("customerName").value = "";
                        document.getElementById("phoneno").value = "";
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
                        document.getElementById("vehicleRegId").value = "";
                        document.getElementById("vehicleBrand").value = "";
                        document.getElementById("vehicleModel").value = "";
                        document.getElementById("seat").value = "";
                    } else if (data && data.vehicleRegId) {
                        // Populate the fields with the available vehicle details.
                        document.getElementById("vehicleRegId").value = data.vehicleRegId;
                        document.getElementById("vehicleBrand").value = data.vehicleBrand;
                        document.getElementById("vehicleModel").value = data.vehicleModel;
                        document.getElementById("seat").value = data.seat;
                    } else {
                        // If no vehicle was returned, clear the fields and alert.
                        document.getElementById("vehicleRegId").value = "";
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
            <label for="custId">Customer Registration Number / NIC</label>
            <input type="text" id="custId" name="custId" placeholder="Customer Id or NIC" required onblur="fetchCustomerDetails()">

            <!-- Auto-filled Customer Name -->
            <label for="customerName">Customer Name</label>
            <input type="text" id="customerName" name="customerName" placeholder="Customer Name" readonly>

            <!-- Auto-filled Telephone Number -->
            <label for="phoneno">Contact Number</label>
            <input type="text" id="phoneno" name="phoneno" placeholder="Contact Number" readonly>

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

            <label for="vehicleBrand">Vehicle Brand</label>
            <input type="text" id="vehicleBrand" name="vehicleBrand" placeholder="Brand" readonly>

            <label for="vehicleModel">Vehicle Model</label>
            <input type="text" id="vehicleModel" name="vehicleModel" placeholder="Model" readonly>

            <label for="seat">Vehicle Seating Capacity</label>
            <input type="text" id="seat" name="seat" placeholder="Seating Capacity" readonly>

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
