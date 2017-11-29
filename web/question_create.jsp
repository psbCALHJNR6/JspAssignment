

<%@page import="ict.bean.QuizBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Question create</title>
        <jsp:include page="import.jsp" />
    </head>
    <body>
        <jsp:include page="navbar.jsp" />
        <jsp:useBean id="quizList" scope="request" class="java.util.ArrayList<ict.bean.QuizBean>" />
        <div class="container">
            
        <form action="QuestionController" method="post">
            <h1 class="my-4">Create question</h1>
            <input type="hidden" name="action" value="create">
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
            
            <label for="quiz">Quiz : </label>
            <select name="quiz" >
                <%
                for(int i =0;i<quizList.size();i++){
                    QuizBean qBean = quizList.get(i);
                    out.print("<option value=\""+qBean.getQID()+"\">"+qBean.getDescription()+"</option>");
                }
                %>
            </select>
            <br>
            <input type="submit" value="Submit" class="form-control" style="cursor:pointer;">
            <br>
            <input type="reset" value="Reset" class="form-control" style="cursor:pointer;">
        </form>
            </div>
      <jsp:include page="footer.jsp" />
    </body>
</html>
