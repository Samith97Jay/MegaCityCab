<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.io.FileWriter"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.File"%>
<!DOCTYPE html>
<%
    // Define the file path to store the last generated number
    String filePath = application.getRealPath("/") + "lastCustomerId.txt";
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
    String vehicleId = "V" + timestamp + "_" + newId ;
%>

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
    <header class="navbar">
        <a class="brand" href="dashboard.jsp">Mega City Cab</a>
        <nav class="nav-links">
           <a href="booking.jsp">New Booking</a>
            <a href="BookingServlet?action=list">View Bookings</a>
           
            <a href="retrive.jsp">View</a>
            <a href="billing.jsp">Billing</a>
            <a href="guide.jsp">Guide</a>
            <a href="dashboard.jsp">Home</a>
    </header>

    <div class="form-container">
        <h2>Vehicle Registration</h2>
        <form action="VehicleServlet" method="post" >
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
