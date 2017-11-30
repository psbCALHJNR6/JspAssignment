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
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/multi-select.css" rel="stylesheet">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha/js/bootstrap.min.js"></script>
        <script src="js/jquery.multi-select.js"></script>
    </head>
    <body>
        <jsp:useBean id="studentlist" scope="request"  class="java.util.ArrayList<ict.bean.UserInfo>" />
        <jsp:useBean id="coursedetail" scope="request"  class="ict.bean.CourseBean" />
        <jsp:useBean id="nCourseStudent" scope="request" class="java.util.ArrayList<ict.bean.UserInfo>" />
        <jsp:include page="navbar.jsp" />
        <div class="container">
            <h1>Course Student</h1>
            
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Email</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for(int i = 0; i < studentlist.size(); i++ ){
                            out.print("<tr><td>" + studentlist.get(i).getUsername() + "</td>" + "<td>" + studentlist.get(i).getEmail()
                                    + "</td></tr>");
                        }
                    %>
                </tbody>
            </table>
                
                <form class="form-horizontal" action="CourseController" method="post">
                    <input type="hidden" name="action" value="regcourse">
                    <input type="hidden" name="cid" value="<%= coursedetail.getCid()%>">
                    <div class="form-group">
                    <select multiple="multiple" class="form-control" id="student-select" name="student">
                        <%
                            for(int i = 0; i < studentlist.size(); i++){
                                out.print("<option selected value=\"" + studentlist.get(i).getId() + "\">" + studentlist.get(i).getUsername() + " - " + studentlist.get(i).getEmail() +"</option>");
                            }
                        %>
                        <%
                            for(int i = 0; i < nCourseStudent.size(); i++){
                                out.print("<option value=\"" + nCourseStudent.get(i).getId() + "\">" + nCourseStudent.get(i).getUsername() + " - " + nCourseStudent.get(i).getEmail() +"</option>");
                            }
                        %>
                    </select>
                </div>
                    <div class="form-group">        
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Submit</button>
                    </div>
                </div>
                </form>
                    <p><a href="CourseController?action=list">course list</a></p>
        </div>
        <script type="text/javascript">
  // run pre selected options
  $(document).ready(function(){
     $('#student-select').multiSelect({
         selectableHeader: "<div class='custom-header'>All Students</div>",
        selectionHeader: "<div class='custom-header'>Quiz Students</div>",
     }); 
  });
  </script>
        <jsp:include page="footer.jsp" />
    </body>
</html>
