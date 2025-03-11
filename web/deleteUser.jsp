<%-- 
    Document   : deleteUser
    Created on : Feb 24, 2025, 10:54:38 PM
    Author     : OZT00090
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Delete User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Delete User</h2>
        
        <form  action="deleteUser.jsp" method="post">
            <div class="mb-3">
                <label>Enter User ID to Delete</label>
                <input type="number" class="form-control" name="userId" required>
            </div>
            <button type="submit" class="btn btn-danger" a href="deleteUser.jsp">Delete User</button>
         
           <button type="button" class="btn btn-primary" onclick="window.location.href='users.jsp'">Cancel</button>
        </form>
    </div>

    <%-- Backend Logic for Deleting User --%>
    <%
        String userId = request.getParameter("userId");
        if (userId != null && !userId.isEmpty()) {
            
            
            try {
                
                
                String url = "jdbc:mysql://localhost:3306/megacitycab";
                String uname = "root";
                String passwd = "1234";
                
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url, uname, passwd);
                
                
                
                String query = "DELETE FROM user WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, Integer.parseInt(userId));
                
                int rowsDeleted = stmt.executeUpdate();
                if (rowsDeleted > 0) {
                    out.println("<br><br><div class='alert alert-success'>User deleted successfully!</div>");
                } else {
                    out.println("<br><br><div class='alert alert-warning'>No user found with that ID.</div>");
                }
                
                stmt.close();
                conn.close();
            } catch (Exception e) {
                out.println("<br><br><div class='alert alert-danger'>Error: " + e.getMessage() + "</div>");
            }
        }
    %>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
