<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Mega City Cab - Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/png" href="img/MCC.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

    <style >
        body {
            font-family: 'Poppins', sans-serif;
            margin: 0;
            padding: 0;
            background: url('img/car-bg.jpg') no-repeat center center/cover;
            background-size: cover;
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

        /* User Info (Right Corner) */
        .user-info {
            display: flex;
            align-items: center;
            color: #fff;
            font-size: 1rem;
            font-weight: bold;
            padding-right: 20px;
        }

        .user-info i {
            margin-right: 10px;
            color: #ffcc00;
        }

        /* Sidebar Navigation */
        .sidebar {
            position: fixed;
            left: 0;
            top: 60px;
            width: 250px;
            height: 100%;
            background: rgba(0, 0, 0, 0.85);
            display: flex;
            flex-direction: column;
            padding-top: 15px;
            box-shadow: 2px 0 5px rgba(0, 0, 0, 0.2);
        }

        .sidebar a {
            display: flex;
            align-items: center;
            padding: 15px 20px;
            color: #fff;
            text-decoration: none;
            font-size: 1.2rem;
            border-radius: 6px;
            transition: 0.3s;
        }

        .sidebar a i {
            margin-right: 12px;
            font-size: 1.4rem;
        }

        .sidebar a:hover {
            background: #ffcc00;
            color: #333;
        }

        /* Main Content */
        .main-content {
            margin-left: 270px;
            margin-top: 80px;
            padding: 20px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100vh;
            text-align: center;
            color: white;
        }

        .main-content h1 {
            font-size: 3rem;
            margin-bottom: 15px;
            text-shadow: 2px 2px 8px rgba(0, 0, 0, 0.8);
        }

        .main-content p {
            font-size: 1.5rem;
            width: 60%;
            text-shadow: 2px 2px 8px rgba(0, 0, 0, 0.6);
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
    </style>
</head>
<body>
    <!-- Navigation Bar -->
    <div class="navbar">
        <a href="#" class="brand"><i class="fas fa-taxi"></i> Mega City Cab</a>
        
        <!-- User Info on Right Corner -->
        <div class="user-info">
            <i class="fas fa-user"></i> 
            <%
                String loggedInUser = (String) session.getAttribute("username");
                if (loggedInUser != null) {
                    out.print(loggedInUser);
                } else {
                    out.print("Guest");
                }
            %>
        </div>
    </div>

 
    <div class="sidebar">
        <a href="booking.jsp"><i class="fas fa-car"></i> Book a Ride</a>
        <a href="BookingServlet?action=list"><i class="fas fa-list-ul"></i> Your Rides</a>
        <div class="dropdown">
        <a href="javascript:void(0);" class="dropbtn"><i class="fas fa-user"></i> Register <i class="fas fa-caret-down"></i></a>
        <div class="dropdown-content">
            <a href="customerReg.jsp">Driver</a>
            <a href="vehicleReg.jsp">Vehicle</a>
            <a href="customerReg.jsp">Customer</a>
        </div>
    </div>
        <a href="billing.jsp"><i class="fas fa-receipt"></i> Billing</a>
        <a href="guide.jsp"><i class="fas fa-info-circle"></i> Help Center</a>
    </div>

    <!-- Main Content -->
    <div class="main-content">
        <h1>Welcome to Mega City Cab</h1>
       
    </div>

    <footer>
        <p>© 2025 Mega City Cab. All rights reserved.</p>
    </footer>
</body>
</html>
