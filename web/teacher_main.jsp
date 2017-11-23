<%-- 
    Document   : teacher_main
    Created on : Nov 20, 2017, 10:54:29 PM
    Author     : psb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Teacher management</title>
        <jsp:include page="import.jsp" />
    </head>
    <body>

        <jsp:include page="navbar.jsp" />

        <div class="container">

            <ul>
                <li><h1><a href="CourseController?action=list">Existing courses</a></h1></li>
                <li><h1><a href="teacher_createcourse.jsp">Create Course</a></h1></li>
                <li><h1><a href="">Existing quiz</a></h1></li>
                <li><h1><a href="">Create Quiz</a></h1></li>
            </ul>




        </div>

        <jsp:include page="footer.jsp" />
    </body>
</html>




<!--<div class="row">
    <div class="col-md-12">
        BOX
        <div class="box">
            BOX HEADER
            <div class="box-header">
                BOX TITLE
                <div class="box-title pull-left">
                    <p><i class="glyphicon glyphicon-book"></i></p>
                    <p>title</p>
                </div>
            </div>
            end: BOX HEADER
            BOX CONTENT
            <div class="box-content">
                content

            </div>
            end: BOX CONTENT
        </div>
        end: BOX  
    </div>
</div>-->
