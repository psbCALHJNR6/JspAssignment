<%-- 
    Document   : question_assign
    Created on : 2017/12/1, 上午 02:27:34
    Author     : hong
--%>

<%@page import="ict.bean.QuestionBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Question assign</title>
        <jsp:include page="import.jsp" />
         
    </head>
    <body>
        <jsp:include page="navbar.jsp" />
        <jsp:useBean id="questionList" scope="request" class="java.util.ArrayList<ict.bean.QuestionBean>" />
        <form action="QuestionController" method="post">
        <input type="hidden" name='action' value='updateAssign'>
        <input type="hidden" name="quizID" value="<%=request.getParameter("quizID")%>">
        <table class="table table-bordered">
            <th>
            <td>Question</td>
            <td>Option A</td>
            <td>Option B</td>
            <td>Option C</td>
            <td>Answer</td>
            </th>
            <%
                for(int i=0;i<questionList.size();i++){
                    QuestionBean qBean = questionList.get(i);
                    out.print("<tr>");
                    out.print("<td><input type=\"checkbox\" id="+qBean.getQuestID()+" name=\"allQ\" value=\""+qBean.getQID()+"\" /></td>"
                    +"<td>"+qBean.getQuestion()+"</td>"
                    +"<td>"+qBean.getOptA()+"</td>"
                    +"<td>"+qBean.getOptB()+"</td>"
                    +"<td>"+qBean.getOptC()+"</td>"
                    +"<td>"+qBean.getAns()+"</td>");
                    out.print("</tr>");
                }

                %>
        </table>
        <input type="submit" value="Submit">
        </form>
        <jsp:include page="footer.jsp" />
    </body>
</html>
