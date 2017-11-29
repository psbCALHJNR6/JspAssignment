<%-- 
    Document   : teacher_quizlist
    Created on : Nov 25, 2017, 1:28:56 PM
    Author     : psb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "ict.bean.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz overview</title>
        <jsp:include page="import.jsp" />
    </head>
    <body style="">
        <h1></h1>
        <jsp:include page="navbar.jsp" />
        <div class="container">
            <jsp:useBean id="quizlist" scope="request" class="java.util.ArrayList<ict.bean.QuizBean>" />


            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Description</th>
                        <th>Start</th>
                        <th>End</th>
                        <th>Duration</th>
                        <th>Attempt time</th>
                        <th>Course</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (int i = 0; i < quizlist.size(); i++)
                        {
                            QuizBean _quiz = new QuizBean();
                            _quiz = quizlist.get(i);
                            out.print("<tr>"
                                    + "<td>" + "<a href=\"QuizController?action=editForm&id=" + _quiz.getQID() + "\">" + _quiz.getDescription() + "</a>" + "</td>"
                                    + "<td>" + _quiz.getStartDate()+ "</td>"
                                    + "<td>" + _quiz.getEndDate()+ "</td>"
                                    + "<td>" + _quiz.getDuration()+ "</td>"
                                    + "<td>" + _quiz.getAttemptTime()+ "</td>"
                                    + "<td>" + _quiz.getCid()+ "</td>"
                                    +"<td>"+"<a href=\"QuestionController?action=createForm\">Create new Quiz</a><br />"
                                    +"<a href=\"QuestionController?action=list&id="+_quiz.getQID()+"\">View all questions</a>"
                                    +"<a href=\"QuestionController?action=quizresult&id="+_quiz.getQID()+"\">View quiz result</a>"
                                    +"</td>"
                                    + "</tr>");
                        }
                    %>
                    
                </tbody>
            </table>
                    
            <a href="QuizController?action=createForm">Create new Quiz</a>
        </div>
                
        <jsp:include page="footer.jsp" />
    </body>
</html>
