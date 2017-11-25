<%-- 
    Document   : teacher_editquiz
    Created on : Nov 25, 2017, 2:09:25 PM
    Author     : psb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Quiz</title>
        <jsp:include page="import.jsp" />
    </head>
    <body>
        <jsp:useBean id="courselist" scope="request" class="java.util.ArrayList<ict.bean.CourseBean>" />
        <jsp:useBean id="quizDetail" scope="request" class="ict.bean.QuizBean" />
        <jsp:include page="navbar.jsp" />

        <div class="container">
            <h2>Edit Quiz</h2>
            <form class="form-horizontal" action="QuizController" method="post">
                <input type="hidden" name="action" value="edit">
                <input type="hidden" name="qid" value="<%= quizDetail.getQID()%>">
                
                <div class="form-group">
                    <label class="control-label col-sm-2" for="desc">Description</label>
                    <div class="col-sm-12">          
                        <input type="text" class="form-control" id="desc" placeholder="Enter Description" name="description" value="<%= quizDetail.getDescription() %>" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="startdate">Start Date</label>
                    <div class="col-sm-12">          
                        <input type="date" class="form-control" id="startdate" placeholder="Enter Start Date" name="startdate" value="<%= quizDetail.getStartDate()%>" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="enddate">End Date</label>
                    <div class="col-sm-12">          
                        <input type="date" class="form-control" id="enddate" placeholder="Enter End Date" name="enddate" value="<%= quizDetail.getEndDate()%>" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="duration">Duration</label>
                    <div class="col-sm-12">          
                        <input type="number" class="form-control" id="duration" placeholder="Enter Duration" name="duration" value="<%= quizDetail.getDuration()%>" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="attemptime">Allow Attempt Time</label>
                    <div class="col-sm-12">          
                        <input type="number" class="form-control" id="attemptime" placeholder="Enter Allow Attempt Time" name="attemptime" value="<%= quizDetail.getAttemptTime()%>" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="cid">Course</label>
                    <div class="col-sm-12">
                        <select class="form-control" name="cid" id="cid" required>
                            <%
                                
                                for(int i = 0; i < courselist.size(); i++){
                                    if(quizDetail.getCid() == courselist.get(i).getCid()){
                                        out.print("<option selected value=\"" + courselist.get(i).getCid() + "\">" + courselist.get(i).getcName()+ "</option>");
                                    }else{
                                        out.print("<option value=\"" + courselist.get(i).getCid() + "\">" + courselist.get(i).getcName()+ "</option>");
                                    }
                                    
                                }
                            %>
                        </select>
                    </div>
                </div>
                
                <div class="form-group">        
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Submit</button>
                    </div>
                </div>
            </form>
        </div>

        <jsp:include page="footer.jsp" />
    </body>
</html>
