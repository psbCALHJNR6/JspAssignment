<%-- 
    Document   : question_viewone
    Created on : 2017/11/28, 上午 11:49:37
    Author     : hong
--%>

<%@page import="ict.bean.QuestionBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
                <jsp:include page="import.jsp" />

        <jsp:useBean id="qBean" scope="request" class="ict.bean.QuestionBean" />
   
    </head>
    
    
    <body>
         <jsp:include page="navbar.jsp" />
         <div class="container">
        Welcome to page question_viewone.jsp
        <%
        QuestionBean qB = qBean;
        if(qB.getAns().equals(""))
        out.print("fuck?");
       
        %>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
