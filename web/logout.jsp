<%
    // Invalidate session
    session.invalidate();

    // Redirect to login page
    response.sendRedirect("login.jsp?message=You have been logged out.");
%>