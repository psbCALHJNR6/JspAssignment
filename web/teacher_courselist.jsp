<%-- 
    Document   : teacher_courselist
    Created on : Nov 22, 2017, 11:04:26 PM
    Author     : psb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "ict.bean.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course overview</title>
        <jsp:include page="import.jsp" />
    </head>
    <body style="">
        <h1></h1>
        <jsp:include page="navbar.jsp" />
        <div class="container">
            <jsp:useBean id="courselist" scope="request" class="java.util.ArrayList<ict.bean.CourseBean>" />


            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Courses</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (int i = 0; i < courselist.size(); i++)
                        {
                            CourseBean _course = new CourseBean();
                            _course = courselist.get(i);
                            out.print("<tr><td>"
                                    + "<a href=\"CourseController?action=coursedetail&courseID=" + _course.getCid() + "\"> " + _course.getcName() + "</a>" + "</td></tr>");
                        }
                    %>
                </tbody>
            </table>
             
                <p><a href="teacher_createcourse.jsp">Create new course</a></p>
                <p><a href="teacher_main.jsp">main page</a></p>
        </div>
                
        <jsp:include page="footer.jsp" />
    </body>
</html>
