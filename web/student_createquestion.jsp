<%-- 
    Document   : student_createquestion
    Created on : Nov 30, 2017, 9:33:07 PM
    Author     : psb
--%>



<%@page import="ict.bean.QuizBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create question </title>
        <jsp:include page="import.jsp" />
    </head>
    <body>
        <jsp:include page="navbar.jsp" />
        <jsp:useBean id="quizDetail" scope="request" class="ict.bean.QuizBean" />
        <jsp:useBean id="userInfo" scope="session" class="ict.bean.UserInfo" />
        <div class="container">
            
        <form action="QuestionController" method="post">
            <h1 class="my-4">Congratulations, you got <%= request.getAttribute("mark") %> mark in <%= quizDetail.getDescription() %></h1>
            
            <h6 class="text-muted">You can feel free to create a question for <%= quizDetail.getDescription() %></h6>
            <input type="hidden" name="action" value="stu_create">
            <label for="qName">Question:</label><input type="text" class="form-control" name="qName" id="qName" required> <br>
            <label for="optA">Option A</label><input type="text" class="form-control" name="optA" id="optA" required> <br>
            <label for="optB">Option B</label><input type="text" class="form-control" name="optB" id="optB" required> <br>
            <label for="optC">Option C</label><input type="text" class="form-control" name="optC" id="optC" required> <br>
            <label for="corrAns">Correct Ans</label>
            <select name="corrAns" id="corrAns" class="form-control" required>
                <option value="" disabled selected>Correct answer...</option>
                <option value="A">A</option>
                <option value="B">B</option>
                <option value="C">C</option>
            </select>
            
            <input type="hidden" name="quiz" value="<%= request.getAttribute("quizID") %>">
            
            <input type="hidden" name="stuid" value="<%= userInfo.getId() %>">
            
            <input type="submit" value="Submit" class="form-control" style="cursor:pointer;">
            <br>
            <input type="reset" value="Reset" class="form-control" style="cursor:pointer;">
        </form>
            </div>
      <jsp:include page="footer.jsp" />
    </body>
</html>
