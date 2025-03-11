<%@page import="java.sql.*"%>
<%
    String id = request.getParameter("id");
    String fullname = "", username = "", email = "", address = "", mobileno = "", nic = "", gender = "";

    try {
        String url = "jdbc:mysql://localhost:3306/megacitycab";
        String uname = "root";
        String passwd = "1234";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, uname, passwd);

        String query = "SELECT * FROM users WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, Integer.parseInt(id));
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            fullname = rs.getString("fullname");
            username = rs.getString("username");
            email = rs.getString("email");
            address = rs.getString("address");
            mobileno = rs.getString("mobileno");
            nic = rs.getString("nic");
            gender = rs.getString("gender");
        }

        rs.close();
        stmt.close();
        conn.close();
    } catch (Exception e) {
        out.println("Error: " + e.getMessage());
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Edit User</h2>
        <form action="updateUser.jsp" method="post">
            <input type="hidden" name="id" value="<%= id %>">
            <div class="mb-3">
                <label>Full Name</label>
                <input type="text" class="form-control" name="fullname" value="<%= fullname %>" required>
            </div>
            <div class="mb-3">
                <label>Username</label>
                <input type="text" class="form-control" name="username" value="<%= username %>" required>
            </div>
            <div class="mb-3">
                <label>Email</label>
                <input type="email" class="form-control" name="email" value="<%= email %>" required>
            </div>
            <div class="mb-3">
                <label>Address</label>
                <input type="text" class="form-control" name="address" value="<%= address %>" required>
            </div>
            <div class="mb-3">
                <label>Mobile No</label>
                <input type="text" class="form-control" name="mobileno" value="<%= mobileno %>" required>
            </div>
            <div class="mb-3">
                <label>NIC</label>
                <input type="text" class="form-control" name="nic" value="<%= nic %>" required>
            </div>
            <div class="mb-3">
                <label>Gender</label>
                <select class="form-control" name="gender">
                    <option value="Male" <%= gender.equals("Male") ? "selected" : "" %>>Male</option>
                    <option value="Female" <%= gender.equals("Female") ? "selected" : "" %>>Female</option>
                </select>
            </div>
            <button type="submit" class="btn btn-success">Update</button>
            
            <button  class="btn btn-primary" a href="users.jsp">Cancel</button>
        </form>
    </div>
</body>
</html>