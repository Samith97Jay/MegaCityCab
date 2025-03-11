<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registered Users</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Registered Users</h2>
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Full Name</th>
                    <th>Username</th>
                    <th>Email</th>
                    <th>Address</th>
                    <th>Mobile No</th>
                    <th>NIC</th>
                    <th>Gender</th>
                    <th></th>
                    <th></th>
                    
                </tr>
            </thead>
            <tbody>
                <%
                    try {
                        String url = "jdbc:mysql://localhost:3306/megacitycab";
                        String uname = "root";
                        String passwd = "1234";
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection conn = DriverManager.getConnection(url, uname, passwd);

                        String query = "SELECT id, fullname, username, email, address, mobileno, nic, gender FROM users ORDER BY id DESC";
                        PreparedStatement stmt = conn.prepareStatement(query);
                        ResultSet rs = stmt.executeQuery();

                        while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getInt("id") %></td>
                    <td><%= rs.getString("fullname") %></td>
                    <td><%= rs.getString("username") %></td>
                    <td><%= rs.getString("email") %></td>
                    <td><%= rs.getString("address") %></td>
                    <td><%= rs.getString("mobileno") %></td>
                    <td><%= rs.getString("nic") %></td>
                    <td><%= rs.getString("gender") %></td>
                    <td>
                        <a href="editUser.jsp?id=<%= rs.getInt("id") %>" class="btn btn-warning btn-sm">Edit</a>
                    </td>
                    
                     <td>
                        <a href="deleteUser.jsp?id=<%= rs.getInt("id") %>" class="btn btn-danger btn-sm">Delete</a>
                    </td>
                </tr>
                <%
                        }
                        rs.close();
                        stmt.close();
                        conn.close();
                    } catch (Exception e) {
                        out.println("<tr><td colspan='9' class='text-danger text-center'>Error: " + e.getMessage() + "</td></tr>");
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>