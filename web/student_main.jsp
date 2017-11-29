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
        <title>DashBroad</title>
        <jsp:include page="import.jsp" />
    </head>
    <body>
        <jsp:useBean id="userInfo" scope="session" class="ict.bean.UserInfo" />
        
        <jsp:include page="navbar.jsp" />
        
        <div class="container">
            
            <ul>
                
             
            <ict:tag1 link="QuizController?action=stuquizlist&id=${userInfo.getId()}" desc="My Quiz"/>
                <ict:tag1 link="CourseController?action=courses&id=${userInfo.getId()}" desc="Courses"/>
            </ul>
            
        </div>
        
        <jsp:include page="footer.jsp" />
    </body>
</html>
