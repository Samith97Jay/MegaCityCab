<%@page import="java.sql.*"%>
<%
    // Retrieve form parameters
    String fullname = request.getParameter("fullname");
    String username = request.getParameter("username");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String address = request.getParameter("address");
    String mobileno = request.getParameter("mobileno");
    String nic = request.getParameter("nic");
    String gender = request.getParameter("gender");

    try {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/megacitycab";
        String uname = "root";
        String passwd = "1234";

        // Load MySQL driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, uname, passwd);

        // Insert query with auto-incremented ID
        String query = "INSERT INTO users (fullname, username, email, password, address, mobileno, nic, gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);

        // Set values for the prepared statement
        stmt.setString(1, fullname);
        stmt.setString(2, username);
        stmt.setString(3, email);
        stmt.setString(4, password);
        stmt.setString(5, address);
        stmt.setString(6, mobileno);
        stmt.setString(7, nic);
        stmt.setString(8, gender);

        // Execute the query
        int inserted = stmt.executeUpdate();
        if (inserted > 0) {
            // Redirect to login page after successful signup
            response.sendRedirect("login.jsp?message=Registration Successful! Please login.");
        } else {
            out.println("Registration failed. Try again.");
        }

        // Close resources
        stmt.close();
        conn.close();
    } catch (Exception e) {
        out.println("Error: " + e.getMessage());
    }
%>