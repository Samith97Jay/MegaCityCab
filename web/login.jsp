<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">User Login</h2>
        <form action="validateUser.jsp" method="post">
            <div class="mb-3">
                <label>Email</label>
                <input type="email" class="form-control" name="email" required>
            </div>
            <div class="mb-3">
                <label>Password</label>
                <input type="password" class="form-control" name="password" required>
            </div>
            <button type="submit" class="btn btn-primary">Login</button>
        </form>
        <p class="mt-3">Don't have an account? <a href="signup.jsp">Signup</a></p>
        
        <%-- Success message display --%>
        <%
            String success = request.getParameter("success");
            if ("true".equals(success)) {
        %>
            <div class="alert alert-success mt-3" role="alert">
                You have successfully logged into the system.
            </div>
        <%
            }
        %>
    </div>
</body>
</html>
