<%@page import="java.sql.*"%>
<%
    // Retrieve form data
    String username = request.getParameter("email");
    String password = request.getParameter("password");

    try {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/megacitycab";
        String uname = "root";
        String passwd = "1234";

        // Load MySQL driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, uname, passwd);

        // Check if the email and password match
        String query = "SELECT email, fullname FROM users WHERE email = ? AND password = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, username);
        stmt.setString(2, password);

        ResultSet result = stmt.executeQuery();

        if (result.next()) {
            // Login successful -> Store user data in session
            session.setAttribute("userEmail", result.getString("email"));
            session.setAttribute("userFullName", result.getString("fullname"));

            // Redirect to welcome page
            response.sendRedirect("welcome.jsp");
        } else {
            // Login failed -> Redirect back to login page with error message
            response.sendRedirect("login.jsp?message=Invalid username or password.");
        }

        // Close resources
        stmt.close();
        conn.close();
    } catch (Exception e) {
        out.println("Error: " + e.getMessage());
    }
%>
