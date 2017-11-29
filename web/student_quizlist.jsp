<%-- 
    Document   : student_quizlist
    Created on : Nov 25, 2017, 12:09:53 AM
    Author     : psb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "ict.bean.*" %>
<%@page import = "java.text.*" %>
<%@page import = "java.util.Date" %>
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
                            
                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            Date today = new Date();

                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                            String str_startDate = _quiz.getStartDate();
                            String str_endDate = _quiz.getEndDate();
                            
                            Date startDate = formatter.parse(str_startDate);
                            Date endDate = formatter.parse(str_endDate);

                            String startString = "";
                            if(today.after(startDate) && today.before(endDate)) {
                                // In between
                                startString = "<a href=\"QuizController?action=startquiz&stuID=" + userInfo.getId() + "&quizID=" + _quiz.getQID()+ "\">Start | </a>";
                            }else{
                                startString = "Expired Quiz | ";
                            }
                            
                            
                            
                            
                            out.print("<tr>"
                                    + "<td>" + _quiz.getDescription() + "</td>"
                                    + "<td>" + _quiz.getStartDate()+ "</td>"
                                    + "<td>" + _quiz.getEndDate()+ "</td>"
                                    + "<td>" + _quiz.getDuration()+ " minutes </td>"
                                    + "<td>" + _quiz.getAttemptTime()+ "</td>"
                                    + "<td>" +  startString
                                    + "<a href=\"QuizController?action=stu_quizresult&stuID="+ userInfo.getId() +"&quizID="+ _quiz.getQID()+"\">result</a>" + "</td>"
                                    + "</tr>");
                        }
//                        
                    %>
                
                </tbody>
            </table>
                    
                    <a href="student_main.jsp">main page</a>
        </div>

        <jsp:include page="footer.jsp" />
    </body>
</html>
