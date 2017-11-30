<%-- 
    Document   : userlist
    Created on : Nov 19, 2017, 11:15:24 PM
    Author     : psb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "ict.bean.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User overview</title>
        <jsp:include page="import.jsp" />
    </head>
    <body style="">
        <h1></h1>
        <jsp:include page="navbar.jsp" />
        <div class="container">
            <jsp:useBean id="userlist" scope="request" class="java.util.ArrayList<ict.bean.UserInfo>" />


            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Role</th>
                        <th>Email</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (int i = 0; i < userlist.size(); i++)
                        {
                            UserInfo _user = new UserInfo();
                            _user = userlist.get(i);
                            out.print("<tr><td>"
                                    + _user.getUsername() + "</td><td>"
                                    + _user.getRole() + "</td><td>"
                                    + _user.getEmail() + "</td><td>"
                                    + "<a href=\"getUser?action=editform&id=" +_user.getId()+ "\">Edit</a> <a href=\"getUser?action=delete&id=" + _user.getId() + "\">Delete</a> </td></tr>");
                        }
                    %>
                </tbody>
            </table>
                <a
            <a href="admin_createuser.jsp">Create new user</a>
        </div>
                
        <jsp:include page="footer.jsp" />
    </body>
</html>
