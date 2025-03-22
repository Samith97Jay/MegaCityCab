<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Calendar" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Mega City Cab</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/png" href="img/MCC.png">
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
            align-items: center;
            justify-content: center;
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
            width: 400px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
            text-align: left;
        }

        .form-container h2 {
            text-align: center;
            color: #333;
        }

        .form-container input {
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

        .error-message {
            color: red;
            font-size: 0.9rem;
            text-align: center;
            margin-bottom: 1rem;
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

    <div class="form-container">
        <h2>Login</h2>
        <p style="text-align:center;">Welcome to Mega City Cab.</p>

        <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
        <% if (errorMessage != null) { %>
            <div class="error-message"><%= errorMessage %></div>
        <% } %>

        <form action="SigninServlet" method="post">
            <label for="uname">Username</label>
            <input type="text" id="uname" name="uname" placeholder="Enter your username" required>

            <label for="pwd">Password</label>
            <input type="password" id="password" name="pwd" placeholder="Enter your password" required>

            <button type="submit">Login</button>
        </form>
    </div>

    <footer>
        <p>&copy; 2025 Mega City Cab. All Rights Reserved.</p>
    </footer>

</body>
</html>