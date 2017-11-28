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
        <title>DashBroad</title>
        <jsp:include page="import.jsp" />
    </head>
    <body>
        <jsp:useBean id="userInfo" scope="session" class="ict.bean.UserInfo" />
        
        <jsp:include page="navbar.jsp" />
        
        <div class="container">
            
            <ul>
                <li><h1><a href="QuizController?action=stuquizlist&id=<%= userInfo.getId() %>">My Quiz</a></h1></li>
                 <li><h1><a href="CourseController?action=courses&id=<%= userInfo.getId() %>">Courses</a></h1></li>
            </ul>
            
        </div>
        
        <jsp:include page="footer.jsp" />
    </body>
</html>
