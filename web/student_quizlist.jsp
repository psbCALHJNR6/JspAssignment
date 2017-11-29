<%-- 
    Document   : student_quizlist
    Created on : Nov 25, 2017, 12:09:53 AM
    Author     : psb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "ict.bean.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DashBroad</title>
        <jsp:include page="import.jsp" />
    </head>
    <body>

        <jsp:include page="navbar.jsp" />

        <div class="container">
            <jsp:useBean id="userInfo" scope="session" class="ict.bean.UserInfo" />
            <jsp:useBean id="quizlist" scope="request" class="java.util.ArrayList<ict.bean.QuizBean>" />


            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Description</th>
                        <th>Start</th>
                        <th>End</th>
                        <th>Duration</th>
                        <th>Attempt time</th>
                        <th>Start</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (int i = 0; i < quizlist.size(); i++)
                        {
                            QuizBean _quiz = new QuizBean();
                            _quiz = quizlist.get(i);
                            out.print("<tr>"
                                    + "<td>" + _quiz.getDescription() + "</td>"
                                    + "<td>" + _quiz.getStartDate()+ "</td>"
                                    + "<td>" + _quiz.getEndDate()+ "</td>"
                                    + "<td>" + _quiz.getDuration()+ " minutes </td>"
                                    + "<td>" + _quiz.getAttemptTime()+ "</td>"
                                    + "<td>" + "<a href=\"QuizController?action=startquiz&stuID=" + userInfo.getId() + "&quizID=" + _quiz.getQID()+ "\">Start</a>" +   "</td>"
                                    + "</tr>");
                        }
//                        "<a href=\"QuizController?action=stu_quizresult&quizID=4&stuID=3\">result</a>" +
                    %>
                
                </tbody>
            </table>

        </div>

        <jsp:include page="footer.jsp" />
    </body>
</html>
