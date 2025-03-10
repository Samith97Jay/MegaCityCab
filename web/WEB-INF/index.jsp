<!--<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Car Booking System</title>
    <link rel="stylesheet" href="assets/css/login.css">
</head>
<body>
    <div class="center">
        <h1>Login</h1>
        <form method="post">
            <div class="txt_field">
                <input type="text" name="uname" required>
                <span></span>
                <label>Username</label>
            </div>
            <div class="txt_field">
                <input type="password" name="password" required>
                <span></span>
                <label>Password</label>
            </div>
            <div class="pass">Forgot Password?</div>
            <input type="submit" value="Login">
            <p style="color: red;">

            <%--
                String username = request.getParameter("uname");
                String password = request.getParameter("password");

                if (username != null && password != null) {
                    Connection conn = null;
                    PreparedStatement statement = null;
                    ResultSet result = null;

                    try {
                        String url = "jdbc:mysql://localhost:3306/carbooking";
                        String uname = "root";
                        String passwd = "1234";

                        Class.forName("com.mysql.cj.jdbc.Driver");
                        conn = DriverManager.getConnection(url, uname, passwd);

                        String query = "SELECT email, password, fullname FROM login WHERE username = ?";
                        statement = conn.prepareStatement(query);
                        statement.setString(1, username);

                        result = statement.executeQuery();

                        if (result.next()) {
                            String storedPassword = result.getString("password"); // Use column name instead of index
                            if (password.equals(storedPassword)) { // Compare correctly
                                session.setAttribute("AccEmail", result.getString("email"));
                                session.setAttribute("AccName", result.getString("fullname"));
                                response.sendRedirect("/WEB-INF/home.jsp" );
                                return; // Stop further execution
                            } else {
                                out.println("Wrong username/password");
                            }
                        } else {
                            out.println("Wrong username/password");
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        out.println("Error: " + e.getMessage());
                    } finally {
                        if (result != null) try { result.close(); } catch (SQLException ignored) {}
                        if (statement != null) try { statement.close(); } catch (SQLException ignored) {}
                        if (conn != null) try { conn.close(); } catch (SQLException ignored) {}
                    }
                }
           --%>

            </p>
            <div class="signup_link">
                Not a member? <a href="signup.jsp">Signup</a><br>
            </div>
        </form>
    </div>
            
    <form action="UserController" method="post">
    <input type="hidden" name="action" value="add">
    <input type="text" name="name" placeholder="Enter Name" required>
    <input type="email" name="email" placeholder="Enter Email" required>
    <input type="password" name="password" placeholder="Enter Password" required>
    <button type="submit">Add User</button>
</form>            
            
            
</body>
</html>-
            
        