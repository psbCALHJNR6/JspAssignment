<%-- 
    Document   : admin_main
    Created on : Nov 19, 2017, 8:56:52 PM
    Author     : psb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Management</title>
        <jsp:include page="import.jsp" />
    </head>
    <body>
        
        <jsp:include page="navbar.jsp" />
        
        <div class="container">
            
            <ul>
                <li><h1><a href="getUser?action=list">Existing Users</a></h1></li>
                <li><h1><a href="admin_createuser.jsp">Create User</a></h1></li>
            </ul>
            
            
            
            
        </div>
        
        <jsp:include page="footer.jsp" />
    </body>
</html>
