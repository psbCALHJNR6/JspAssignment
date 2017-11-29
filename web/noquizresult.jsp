<%-- 
    Document   : noquizresult
    Created on : Nov 30, 2017, 12:17:07 AM
    Author     : psb
--%>
<%@ page isErrorPage="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result not found</title>
        <jsp:include page="import.jsp" />
    </head>
    <body>
        <jsp:include page="navbar.jsp" />
        <div class="container">
            <h1>No Result Found</h1>
        </div>
        <a href="QuizController?action=stuquizlist">Quiz List</a>
        <jsp:include page="footer.jsp" />
    </body>
</html>
