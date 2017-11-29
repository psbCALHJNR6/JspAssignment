<%-- 
    Document   : question_view
    Created on : 2017/11/27, 下午 07:44:23
    Author     : hong
--%>

<%@page import="ict.bean.QuestionBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>E-Learning System - View all questions</title>
        <jsp:include page="import.jsp" />
        <jsp:useBean id="questionList" scope="request" class="java.util.ArrayList<ict.bean.QuestionBean>" />
    </head>
    <body>
        <jsp:include page="navbar.jsp" />
        <div class="container">
             <div class="jumbotron">
    <h1>Manage question</h1> 
    <p>View and manage question here.</p> 
  </div>
        <table class="table table-bordered">
            <tr>
                <th>Question ID</th>
                <th>Quiz ID</th>
                <th>Question</th>
                <th>Option A</th>
                <th>Option B</th>
                <th>Option C</th>
                <th>Correct answer</th>
                <th>Action</th>
            </tr>
             <%
                        for (int i = 0; i < questionList.size(); i++)
                        {
                            QuestionBean _que = new QuestionBean();
                            _que = questionList.get(i);
                            out.print("<tr>"
                                    + "<td>" +_que.getQuestID()+ "</td>"
                                    + "<td>" +_que.getQID() + "</td>"
                                    + "<td>" + _que.getQuestion()+ "</td>"
                                    + "<td>" + _que.getOptA()+ "</td>"
                                    + "<td>" + _que.getOptB()+ "</td>"
                                    + "<td>" + _que.getOptC()+ "</td>"
                                    + "<td>" + _que.getAns()+ "</td>"
                                    +"<td><a href=\"QuestionController?action=view&id="+_que.getQuestID()+"\">Edit</a>"
                                    +" | <a href=\"QuestionController?action=delete&id="+_que.getQuestID()+"\">Delete</a></td>"
                                   
                                    + "</tr>");
                        }
                    %>
        </table>
        <a href="QuestionController?action=create" style="float: right;">+ Add a new question</a>
        </div>
         <jsp:include page="footer.jsp" />
    </body>
</html>
