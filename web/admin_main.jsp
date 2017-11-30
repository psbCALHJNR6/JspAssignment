<%-- 
    Document   : admin_main
    Created on : Nov 19, 2017, 8:56:52 PM
    Author     : psb
--%>
<%@ taglib uri="/WEB-INF/tlds/ict.taglib.tld" prefix="ict"%>
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
               
                <ict:tag1 link="getUser?action=list" desc="Existing Users"/>
                <ict:tag1 link="admin_createuser.jsp" desc="Create User"/>
            </ul>
            
            
            
            
        </div>
        
        <jsp:include page="footer.jsp" />
    </body>
</html>
