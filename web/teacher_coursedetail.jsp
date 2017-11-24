<%-- 
    Document   : course_detail
    Created on : Nov 24, 2017, 8:25:48 PM
    Author     : psb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>course name</title>
        <jsp:include page="import.jsp" />
    </head>
    <body>
        <jsp:useBean id="studentlist" scope="request"  class="java.util.ArrayList<ict.bean.UserInfo>" />
        <jsp:include page="navbar.jsp" />
        <div class="container">
            <h1>Course Student</h1>
            <%
                for(int i = 0; i < studentlist.size(); i++ ){
                    out.print(studentlist.get(i).getUsername());
                }
            %>
        </div>
        
        <jsp:include page="footer.jsp" />
    </body>
</html>
