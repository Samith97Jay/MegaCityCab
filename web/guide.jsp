<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Mega City Cab</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="icon" type="image/png" href="img/MCC.png">
  <!-- Google Fonts for a modern look -->
  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">
  <style>
    /* Reset & Base Styles */
    * {
      box-sizing: border-box;
      margin: 0;
      padding: 0;
    }
    body {
      font-family: 'Roboto', sans-serif;
      background: #f2f2f2;
      color: #333;
      padding: 2rem;
      line-height: 1.6;
    }
    a {
      text-decoration: none;
      color: inherit;
    }
    ul {
      list-style: none;
      padding-left: 1rem;
    }
    ul li::before {
      content: "\2022";
      color: #5563DE;
      font-weight: bold;
      display: inline-block;
      width: 1em;
      margin-left: -1em;
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
      font-size: 1.4rem;
      font-weight: 500;
      color: #333;
    }
    header.navbar .nav-links {
      display: flex;
      gap: 1rem;
    }
    header.navbar .nav-links a {
      color: #5563DE;
      font-weight: 500;
      transition: color 0.3s;
      position: relative;
      padding-left: 30px;
      background-repeat: no-repeat;
      background-position: left center;
      background-size: 20px 20px;
    }
    header.navbar .nav-links a:hover {
      color: #444;
    }
    header.navbar .nav-links a:hover::after {
      opacity: 1;
    }
    header.navbar .nav-links a[href="dashboard.jsp"] {
      background-image: url('https://img.icons8.com/?size=100&id=S5D5w5vFLhYp&format=png&color=000000');
    }
    header.navbar .nav-links a[href="index.jsp"] {
      background-image: url('https://img.icons8.com/?size=100&id=111473&format=png&color=000000');
    }
    header.navbar .nav-links a::after {
      content: attr(href);
      position: absolute;
      bottom: -28px;
      left: 50%;
      transform: translateX(-50%);
      background: #333;
      color: #fff;
      padding: 3px 7px;
      border-radius: 4px;
      font-size: 0.75rem;
      white-space: nowrap;
      opacity: 0;
      transition: opacity 0.3s;
      pointer-events: none;
    }

    /* Help container styling */
    .help-container {
      background: #fff;
      max-width: 900px;
      margin: 100px auto 2rem auto;
      padding: 2rem;
      border-radius: 8px;
      box-shadow: 0 6px 20px rgba(0, 0, 0, 0.1);
    }
    .help-container h1 {
      text-align: center;
      margin-bottom: 2rem;
      color: #5563DE;
      font-size: 2rem;
    }
    .help-section {
      border-bottom: 1px solid #eee;
      padding-bottom: 1.5rem;
      margin-bottom: 1.5rem;
    }
    .help-section:last-child {
      border-bottom: none;
      margin-bottom: 0;
      padding-bottom: 0;
    }
    .help-section h2 {
      margin-bottom: 0.75rem;
      font-size: 1.4rem;
      color: #444;
      border-left: 4px solid #5563DE;
      padding-left: 0.5rem;
    }
    .help-section ul {
      margin-top: 0.5rem;
    }
    .help-section p {
      margin-bottom: 0.8rem;
      font-size: 1rem;
      color: #555;
    }
    .back-link {
      display: inline-block;
      text-align: center;
      margin-top: 1rem;
      font-size: 1rem;
      font-weight: 500;
      color: #5563DE;
      transition: color 0.3s ease;
      border: 1px solid #5563DE;
      padding: 0.5rem 1rem;
      border-radius: 4px;
    }
    .back-link:hover {
      color: #fff;
      background-color: #5563DE;
    }
    
    footer {
            background-color: #fff;
            text-align: center;
            padding: 1rem;
        }

    footer p {
            color: #666;
            font-size: 0.9rem;
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
      <a href="ret.jsp">View</a>
      <a href="billing.jsp">Billing</a>
      <a href="help.jsp">Help</a>
      <a href="dashboard.jsp"></a>
      <a href="index.jsp"></a>
    </nav>
  </header>
  
  <div class="help-container">
    <h1>System Help & Guidelines</h1>
    
    <!-- Dashboard Overview -->
    <div class="help-section">
      <h2>Login & Dashboard</h2>
      <p>
        After logging in, you are taken directly to the dashboard. From here, you can quickly access the following functions:
      </p>
      <ul>
        <li>New Booking</li>
        <li>View Bookings</li>
        <li>Registration</li>
        <li>View</li>
        <li>Billing</li>
        <li>Help</li>
      </ul>
    </div>
    
    <!-- New Booking Section -->
    <div class="help-section">
      <h2>New Booking</h2>
      <ul>
        <li><strong>Auto-generated Booking Number:</strong> A unique number is automatically generated.</li>
        <li><strong>Customer Details:</strong> 
          <ul>
            <li>If you enter your Customer Registration Number or NIC and are registered, your name and contact auto-populate.</li>
            <li>If not recognized, please register first or view customer details in the View section.</li>
          </ul>
        </li>
        <li><strong>Vehicle Selection:</strong> Select your desired Vehicle Type (e.g., Car, SUV). The system automatically assigns a vehicle and retrieves its details.</li>
        <li><strong>Booking Information:</strong> Insert Pickup Location, Destination, and choose the Booking Date to finalize your booking.</li>
      </ul>
    </div>
    
    <!-- View Bookings Section -->
    <div class="help-section">
      <h2>View Bookings</h2>
      <ul>
        <li>See a complete list of all appointed bookings.</li>
        <li>Each entry shows the Booking Number and Customer Registration Number/NIC.</li>
        <li>Click on a booking to view detailed information including customer, vehicle, and trip details.</li>
      </ul>
    </div>
    
    <!-- Registration Section -->
    <div class="help-section">
      <h2>Registration</h2>
      <ul>
        <li>
          <strong>Customer Registration:</strong> 
          <ul>
            <li>An auto-generated Registration Number is provided.</li>
            <li>Enter Customer Name, Address, NIC Number, and Contact Number.</li>
          </ul>
        </li>
        <li>
          <strong>Vehicle Registration:</strong>
          <ul>
            <li>Select the Vehicle Type (Car, SUV, Van, or Bus) to auto-generate a Registration ID (e.g., C1234, S1234, V3992, B1234).</li>
            <li>Enter License Plate Number, Brand, Model, Color, and Seating Capacity.</li>
          </ul>
        </li>
        <li>
          <strong>Driver Registration:</strong>
          <ul>
            <li>A Driver ID (e.g., DI1234) is auto-generated.</li>
            <li>Enter Name, License Number, Contact Number, Address and assign an existing Vehicle Registration ID (vehicle must be registered first).</li>
          </ul>
        </li>
      </ul>
    </div>
    

    <div class="help-section">
      <h2>View Records</h2>
      <ul>
        <li>Search using Customer Registration ID/NIC, Driver ID, or Vehicle Registration ID.</li>
        <li>View and edit details for customers, drivers, and vehicles.</li>
      </ul>
    </div>
    

    <div class="help-section">
      <h2>Billing</h2>
      <ul>
        <li>Enter the Booking Number (e.g., BKN123456) and the trip distance (in miles).</li>
        <li>The system calculates the total fare including base charges, taxes, and any discounts.</li>
        <li>You can print or download the bill.</li>
      </ul>
    </div>
    
    
    <div class="help-section">
      <h2>Navigation & Logout</h2>
      <ul>
        <li>A dashboard icon in the navigation bar always takes you back to the dashboard.</li>
        <li>A person icon is available for logging out.</li>
      </ul>
    </div>
    
    <div style="text-align: center;">
      <a class="back-link" href="dashboard.jsp">Return to Dashboard</a>
    </div>
    <br>
    <footer>
        <p>© 2025 Mega City Cab. All rights reserved.</p>
    </footer>
  </div>
</body>
</html>
