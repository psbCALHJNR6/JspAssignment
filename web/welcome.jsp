<%-- 
    Document   : welcome
    Created on : Nov 14, 2017, 11:07:31 AM
    Author     : a1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="userInfo" class="ict.bean.UserInfo" scope="session"/>
        
        <h1>Welcome to ICT, <jsp:getProperty name="userInfo" property="username" /></h1>
        
        <form method="post" action="main">
            <input type="hidden" name="action" value="logout">
            <input type="submit" value="Logout" name="logoutButton">
        </form>
        <hr>
        <a href="brandController?action=list">getAllBrands</a><br>
    </body>
</html>
