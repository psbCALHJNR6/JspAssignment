<%-- 
    Document   : quizresult
    Created on : Nov 29, 2017, 11:58:01 PM
    Author     : psb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz Result</title>
        <jsp:include page="import.jsp" />
    </head>
    <body>

        <jsp:include page="navbar.jsp" />
        <div class="container">
            <h1>Hello World!</h1>
            <%= request.getAttribute("highest")%>
            <%= request.getAttribute("lowest")%>
            <%= request.getAttribute("average")%>
            <%= request.getAttribute("canAttemptTime")%>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
