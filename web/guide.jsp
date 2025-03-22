<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>System Help & User Guide</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">
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

        .container {
            margin-top: 100px;
            max-width: 900px;
            margin: auto;
            background: rgba(255, 255, 255, 0.95);
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1, h2 { color: #333; text-align: center; }
        ul { padding-left: 20px; }
        .section { margin-bottom: 20px; }

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

        footer {
            background: rgba(0, 0, 0, 0.9);
            padding: 15px;
            text-align: center;
            width: 100%;
            position: fixed;
            bottom: 0;
            left: 0;
        }

        footer p { margin: 0; color: #fff; }
    </style>
</head>
<body>
    <div class="navbar">
        <a class="brand" href="#">Mega City Cab</a>
    </div>
    
    <div class="container">
        <h1>System Help & User Guide</h1>
        <div class="section">
            <h2>Login & Dashboard</h2>
            <ul>
                <li>Access different sections like Booking, Registration, Billing, and more.</li>
            </ul>
        </div>
        <div class="section">
            <h2>Booking Management</h2>
            <ul>
                <li>Auto-generated Booking ID</li>
                <li>Customer and vehicle selection process</li>
                <li>Viewing and managing existing bookings</li>
            </ul>
        </div>
        <div class="section">
            <h2>Registration</h2>
            <ul>
                <li>Customer, Vehicle, and Driver registration process</li>
            </ul>
        </div>
        <div class="section">
            <h2>Billing</h2>
            <ul>
                <li>Enter Booking Number and calculate fare</li>
                <li>Print or download the invoice</li>
            </ul>
        </div>
        <a class="back-link" href="dashboard.jsp">Return to Dashboard</a>
    </div>

    <footer>
        <p>&copy; 2025 Mega City Cab. All rights reserved.</p>
    </footer>
</body>
</html>

