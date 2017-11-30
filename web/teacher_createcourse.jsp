<%-- 
    Document   : teacher_createcourse
    Created on : Nov 22, 2017, 11:04:18 PM
    Author     : psb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Course</title>
        <jsp:include page="import.jsp" />
    </head>
    <body>
        <jsp:include page="navbar.jsp" />

        <div class="container">
            <h2>Create Course</h2>
            <form class="form-horizontal" action="CourseController" method="post">
                <input type="hidden" name="action" value="create">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="coursename">Course name</label>
                    <div class="col-sm-12">          
                        <input type="text" class="form-control" id="coursename" placeholder="Enter Course Name" name="coursename" required>
                    </div>
                </div>
                
                <div class="form-group">        
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Submit</button>
                    </div>
                </div>
            </form>
        </div>

        <jsp:include page="footer.jsp" />
    </body>
</html>

