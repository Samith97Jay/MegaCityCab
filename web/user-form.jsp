<%-- 
    Document   : user-form
    Created on : Mar 9, 2025, 1:20:32 AM
    Author     : OZT00090
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Management</title>
    </head>
    <body>
        <form action="UserController" method="post">
    <input type="hidden" name="action" value="add">
    <input type="text" name="name" placeholder="Enter Name" required>
    <input type="email" name="email" placeholder="Enter Email" required>
    <input type="password" name="password" placeholder="Enter Password" required>
    <button type="submit">Add User</button>
</form>
    </body>
</html>
