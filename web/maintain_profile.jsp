<%-- 
    Document   : maintain_profile
    Created on : Nov 30, 2017, 12:47:12 AM
    Author     : psb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit User</title>
        <jsp:include page="import.jsp" />
    </head>
    <body>
        <jsp:include page="navbar.jsp" />
        <jsp:useBean id="userDetail" scope="request" class="ict.bean.UserInfo" />

        <div class="container">
            <h2>Edit User</h2>
            <form class="form-horizontal" action="getUser" method="post">
                <input type="hidden" name="action" value="maintainprofile">
                <input type="hidden" name="id" value="<%= userDetail.getId()%>">
                <input type="hidden" name="role" value="<%= userDetail.getRole()%>">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="username">Username:</label>
                    <div class="col-sm-12">          
                        <input type="text" class="form-control" id="username" placeholder="Enter Username" name="username" value="<%= userDetail.getUsername()%>" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="email">Email:</label>
                    <div class="col-sm-12">
                        <input type="email" class="form-control" id="email" placeholder="Enter email" name="email" value="<%= userDetail.getEmail()%>" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="pwd">Password:</label>
                    <div class="col-sm-12">          
                        <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password" value="<%= userDetail.getPassword()%>" required>
                    </div>
                </div>
                <div class="form-group">        
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-success">Update</button>
                    </div>
                </div>
            </form>
                    <% if (userDetail.getRole().equals("STUDENT"))
                            { %>
        <a href="student_main.jsp">main page</a>
        <% }
                    else
                    { %>
        <a href="teacher_main.jsp">main page</a>
        <% }%>
        </div>
        

        <jsp:include page="footer.jsp" />
    </body>
</html>
