
package com.megacitycab.controller;

import com.megacitycab.service.AuthService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class SigninServlet extends HttpServlet {

 private static final long serialVersionUID = 1L;
    

    private AuthService authService;

    @Override
    public void init() throws ServletException {
        super.init();
     
        authService = AuthService.getInstance();
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
      
        String uname = request.getParameter("uname");
        String pwd = request.getParameter("pwd");
        
       
        if (uname == null || uname.trim().isEmpty() ||
            pwd == null || pwd.trim().isEmpty()) {
            
            request.setAttribute("errorMessage", "Username and password are required.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        
       
        boolean isAuthenticated = false;
     try {
         isAuthenticated = authService.authenticate(uname, pwd);
     } catch (SQLException ex) {
         Logger.getLogger(SigninServlet.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
         Logger.getLogger(SigninServlet.class.getName()).log(Level.SEVERE, null, ex);
     }
        
        if (isAuthenticated) {
            // Redirect or forward to the dashboard (or home page) on successful login.
            request.getSession().setAttribute("uname", uname);
            response.sendRedirect("dashboard.jsp");
        } else {
            // Return to login page with error message on failure.
            request.setAttribute("errorMessage", "Invalid username or password. Please try again.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

}
