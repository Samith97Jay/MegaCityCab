<%@page import="java.sql.*"%>
<%
    String id = request.getParameter("id");
    String fullname = request.getParameter("fullname");
    String username = request.getParameter("username");
    String email = request.getParameter("email");
    String address = request.getParameter("address");
    String mobileno = request.getParameter("mobileno");
    String nic = request.getParameter("nic");
    String gender = request.getParameter("gender");

    try {
        String url = "jdbc:mysql://localhost:3306/megacitycab";
        String uname = "root";
        String passwd = "1234";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, uname, passwd);

        String query = "UPDATE user SET fullname=?, username=?, email=?, address=?, mobileno=?, nic=?, gender=? WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(query);

        stmt.setString(1, fullname);
        stmt.setString(2, username);
        stmt.setString(3, email);
        stmt.setString(4, address);
        stmt.setString(5, mobileno);
        stmt.setString(6, nic);
        stmt.setString(7, gender);
        stmt.setInt(8, Integer.parseInt(id));

        int updated = stmt.executeUpdate();
        if (updated > 0) {
            response.sendRedirect("users.jsp");
        } else {
            out.println("Update failed!");
        }

        stmt.close();
        conn.close();
    } catch (Exception e) {
        out.println("Error: " + e.getMessage());
    }
%>