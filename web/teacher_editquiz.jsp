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
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/multi-select.css" rel="stylesheet">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha/js/bootstrap.min.js"></script>
        <script src="js/jquery.multi-select.js"></script>
    </head>
    <body>
        <jsp:useBean id="courselist" scope="request" class="java.util.ArrayList<ict.bean.CourseBean>" />
        <jsp:useBean id="quizDetail" scope="request" class="ict.bean.QuizBean" />
        <jsp:useBean id="quizstudent" scope="request" class="java.util.ArrayList<ict.bean.UserInfo>" />
        <jsp:useBean id="nQuizstudent" scope="request" class="java.util.ArrayList<ict.bean.UserInfo>" />
        <jsp:include page="navbar.jsp" />

        <div class="container">

            <h2>Edit Quiz</h2>
            <form class="form-horizontal" action="QuizController" method="post">
                <input type="hidden" name="action" value="edit">
                <input type="hidden" name="qid" value="<%= quizDetail.getQID()%>">

                <div class="form-group">
                    <label class="control-label col-sm-2" for="desc">Description</label>
                    <div class="col-sm-12">          
                        <input type="text" class="form-control" id="desc" placeholder="Enter Description" name="description" value="<%= quizDetail.getDescription()%>" required>
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

                                for (int i = 0; i < courselist.size(); i++)
                                {
                                    if (quizDetail.getCid() == courselist.get(i).getCid())
                                    {
                                        out.print("<option selected value=\"" + courselist.get(i).getCid() + "\">" + courselist.get(i).getcName() + "</option>");
                                    }
                                    else
                                    {
                                        out.print("<option value=\"" + courselist.get(i).getCid() + "\">" + courselist.get(i).getcName() + "</option>");
                                    }

                                }
                            %>
                        </select>
                    </div>
                </div>
                
                        <h3>Add Student</h3>
                <div class="form-group">
                    <select multiple="multiple" class="form-control" id="student-select" name="student">
                        <%
                            for(int i = 0; i < quizstudent.size(); i++){
                                out.print("<option selected value=\"" + quizstudent.get(i).getId() + "\">" + quizstudent.get(i).getUsername() + " - " + quizstudent.get(i).getEmail() +"</option>");
                            }
                        %>
                        <%
                            for(int i = 0; i < nQuizstudent.size(); i++){
                                out.print("<option value=\"" + nQuizstudent.get(i).getId() + "\">" + nQuizstudent.get(i).getUsername() + " - " + nQuizstudent.get(i).getEmail() +"</option>");
                            }
                        %>
                    </select>
                </div>

                <div class="form-group">        
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Submit</button>
                    </div>
                </div>
            </form>
        </div>
<script type="text/javascript">
  // run pre selected options
  $(document).ready(function(){
     $('#student-select').multiSelect({
         selectableHeader: "<div class='custom-header'>All Students</div>",
        selectionHeader: "<div class='custom-header'>Quiz Students</div>",
     }); 
  });
  </script>
        <jsp:include page="footer.jsp" />
    </body>
</html>
