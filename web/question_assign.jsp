<%-- 
    Document   : question_assign
    Created on : 2017/12/1, 上午 02:27:34
    Author     : hong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Question assign</title>
    </head>
    <body>
        <jsp:useBean id="questionList" scope="request" class="java.util.ArrayList<ict.bean.QuestionBean>" />
        <table>
            <%
                for(int i=0;i<questionList.size();i++){
                    out.print("<tr>");
                    out.print("<td></td>");
                    out.print("</tr>");
                }
                %>
        </table>
    </body>
</html>
