<%-- 
    Document   : loginError
    Created on : Nov 14, 2017, 11:03:30 AM
    Author     : a1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Error</title>
    </head>
    <body>
        <h1>Incorrect Password</h1>
        <p>
            <% out.println("<a href=\"" + request.getContextPath() + "/main\">Login again</a>"); %>
        </p>
    </body>
</html>
