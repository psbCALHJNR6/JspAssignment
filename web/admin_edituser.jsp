<%-- 
    Document   : admin_edituser
    Created on : Nov 19, 2017, 11:23:04 PM
    Author     : psb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create User</title>
        <jsp:include page="import.jsp" />
    </head>
    <body>
        <jsp:include page="navbar.jsp" />
        <jsp:useBean id="userDetail" scope="request" class="ict.bean.UserInfo" />

        <div class="container">
            <h2>Edit User</h2>
            <%= userDetail.getEmail() %>
        </div>

        <jsp:include page="footer.jsp" />
    </body>
</html>
