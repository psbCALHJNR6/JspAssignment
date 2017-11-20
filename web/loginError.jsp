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
        <jsp:include page="import.jsp" />
    </head>
    <body>
        <jsp:include page="navbar.jsp" />
        <div class="container">
            <h1>Incorrect Password</h1>
            <p>
                <% out.println("<a href=\"" + request.getContextPath() + "/\">Login again</a>");%>
            </p>
        </div>

        <jsp:include page="footer.jsp" />
    </body>
</html>
