/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megacitycab.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.megacitycab.model.User;
import com.megacitycab.service.UserService;

/**
 *
 * @author OZT00090
 */
@WebServlet("/register")
public class UserServlet extends HttpServlet {

      private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullname = request.getParameter("fullname");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String mobileno = request.getParameter("mobileno");
        String nic = request.getParameter("nic");
        String gender = request.getParameter("gender");

        User user = new User(fullname, username, email, password, address, mobileno, nic, gender);
        boolean registered = userService.registerUser(user);

        if (registered) {
            response.sendRedirect("login.jsp?message=Registration Successful! Please login.");
        } else {
            response.sendRedirect("signup.jsp?error=Registration failed. Try again.");
        }
    }
}
