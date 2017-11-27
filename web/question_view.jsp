<%-- 
    Document   : question_view
    Created on : 2017/11/27, 下午 07:44:23
    Author     : hong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>E-Learning System - View all questions</title>
        <jsp:include page="import.jsp" />
        <jsp:useBean id="questionlist" scope="request" class="java.util.ArrayList<ict.bean.QuestionBean>" />
    </head>
    <body>
        <jsp:include page="navbar.jsp" />
        <h1>View all questions</h1>
        <table border="1">
            <tr>
                <th>Question ID</th>
                <th>Quiz ID</th>
                <th>Question</th>
                <th>Option A</th>
                <th>Option B</th>
                <th>Option c</th>
                <th>Correct answer</th>
            </tr>
        </table>
         <jsp:include page="footer.jsp" />
    </body>
</html>
