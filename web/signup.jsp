<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Signup</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">User Signup</h2>
        <form action="registerUser.jsp" method="post">
            <div class="mb-3">
                <label>Full Name</label>
                <input type="text" class="form-control" name="fullname" required>
            </div>
            <div class="mb-3">
                <label>Username</label>
                <input type="text" class="form-control" name="username" required>
            </div>
            <div class="mb-3">
                <label>Email</label>
                <input type="email" class="form-control" name="email" required>
            </div>
            <div class="mb-3">
                <label>Password</label>
                <input type="password" class="form-control" name="password" required>
            </div>
            <div class="mb-3">
                <label>Address</label>
                <input type="text" class="form-control" name="address" required>
            </div>
            <div class="mb-3">
                <label>Mobile No</label>
                <input type="text" class="form-control" name="mobileno" required>
            </div>
            <div class="mb-3">
                <label>NIC</label>
                <input type="text" class="form-control" name="nic" required>
            </div>
            <div class="mb-3">
                <label>Gender</label>
                <select class="form-control" name="gender">
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Signup</button>
        </form>
        <p class="mt-3">Already have an account? <a href="login.jsp">Login</a></p>
    </div>
</body>
</html>