<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // Check if user is logged in
    String userFullName = (String) session.getAttribute("userFullName");
    if (userFullName == null) {
        response.sendRedirect("login.jsp?message=Please log in first.");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>Welcome, <%= userFullName %>! 🎉</h2>
        <p>You have successfully logged in.</p>
        <a href="users.jsp" class="btn btn-primary">View Users</a>
        <a href="logout.jsp" class="btn btn-danger">Logout</a>
    </div>
</body>
</html>