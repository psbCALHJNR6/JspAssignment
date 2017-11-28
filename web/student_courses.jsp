<%-- 
    Document   : admin_main
    Created on : Nov 19, 2017, 8:56:52 PM
    Author     : psb
--%>
<%@page import = "ict.bean.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DashBroad</title>
        <jsp:include page="import.jsp" />
    </head>
    <body>
        <jsp:useBean id="courses" scope="request" class="java.util.ArrayList<ict.bean.CourseBean>"/>
        
        <jsp:include page="navbar.jsp" />
        
        <div class="container">
            <ul>
                <%out.print(courses.size());
             for (int i = 0; i < courses.size(); i++)
                        {
                            CourseBean _c = new CourseBean();
                                                      
                            _c = courses.get(i);
                            out.print("<li><h1>"
                                    + _c. getcName() + "</h1></li>");
                            
                        }
            
            
            
            %>
            </ul>
        </div>
        <a href="download.jsp">download the jsp file</a>
        <jsp:include page="footer.jsp" />
    </body>
</html>
