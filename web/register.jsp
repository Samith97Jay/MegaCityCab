<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Mega City Cab - Register</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        /* Reset defaults */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f2f2f2;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
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

        .container {
            flex: 1;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            padding: 2rem;
        }

        .welcome-message {
            background-color: #fff;
            padding: 2rem;
            border-radius: 8px;
            max-width: 600px;
            width: 100%;
            text-align: center;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }

        .welcome-message h1 {
            margin-bottom: 1rem;
            color: #333;
        }

        .welcome-message p {
            margin-bottom: 1.5rem;
            color: #555;
            line-height: 1.6;
        }

        .buttons {
            display: flex;
            flex-wrap: wrap;
            gap: 1rem;
            justify-content: center;
        }

        .buttons a {
            text-decoration: none;
            color: #fff;
            background-color: #5563DE;
            padding: 0.75rem 1.5rem;
            border-radius: 4px;
            transition: background-color 0.3s;
        }

        .buttons a:hover {
            background-color: #444fb7;
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

        /* Responsive design */
        @media (max-width: 600px) {
            .navbar {
                flex-direction: column;
                align-items: flex-start;
            }

            .nav-links {
                margin-top: 1rem;
                flex-direction: column;
                align-items: flex-start;
            }
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

    <div class="container">
        <div class="welcome-message">
            <h1>Mega City Cab Registration!</h1>
            <p>
                Hello, 
                <% 
                    // Display the logged-in username from the session, if available
                    String loggedInUser = (String) session.getAttribute("username");
                    if (loggedInUser != null) {
                        out.print(loggedInUser);
                    } else {
                        out.print("Guest");
                    }
                %>!
                <br><br>
                This is your registration hub for registering customers, cars and drivers. 
                Use the buttons below to access different registrations.
            </p>
            <div class="buttons">
                <a href="customerReg.jsp">Customer Register</a>
                <a href="carReg.jsp">Car Register</a>
                <a href="driverReg.jsp">Driver Register</a>
            </div>
        </div>
    </div>

    <footer>
        <p>© <%= java.time.Year.now() %> Mega City Cab. All rights reserved.</p>
    </footer>
</body>
</html>
