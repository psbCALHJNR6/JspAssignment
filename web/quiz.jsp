<%-- 
    Document   : quiz
    Created on : Nov 28, 2017, 10:52:39 PM
    Author     : psb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz</title>
        <link rel="stylesheet" href="quizcss/bootstrap.min.css">
        <script src="quizjs/jquery.min.js"></script>
        <script src="quizjs/bootstrap.min.js"></script>
        <script src="quizjs/templatemo_script.js"></script>
    </head>
    <script>

        function startCount()
        {
        timer = setInterval(count, 1000);
        }
        function count()
        {
        var time_shown = $("#usedTime").val();
        var time_chunks = time_shown.split(":");
        var hour, mins, secs;
        hour = Number(time_chunks[0]);
        mins = Number(time_chunks[1]);
        secs = Number(time_chunks[2]);
        secs++;
        if (secs == 60) {
        secs = 0;
        mins = mins + 1;
        }
        if (mins == 60) {
        mins = 0;
        hour = hour + 1;
        }
        if (hour == 13) {
        hour = 1;
        }

        $("#usedTime").val(hour + ":" + plz(mins) + ":" + plz(secs));
        }

        function plz(digit) {

        var zpad = digit + '';
        if (digit < 10) {
        zpad = "0" + zpad;
        }
        return zpad;
        }
    </script>
    <body style="background-color: white" >
        <jsp:useBean id="quizDetail" scope="request" class="ict.bean.QuizBean" />
        <jsp:useBean id="quizQuestions" scope="request" class="java.util.ArrayList<ict.bean.QuestionBean>" />
        <div class="">
            <!-- Stack the columns on mobile by making one full-width and the other half-width -->
            <div class="row">
                <div class="col-md-8 col-md-offset-2">
                    <div class="panel panel-default" style="margin-top: 50px">
                        <div class="panel-heading">
                            <button class="btn btn-info" data-toggle="collapse" data-target="#examDetail">Exam Details</button>
                            <h4>
                                <span class="float-right">Time left : <span id="time">Ready</span></span>
                            </h4>
                            <div id="examDetail" class="collapse">
                                <table class="table datatable">
                                    <tr>
                                        <th width="50%">Quiz Description</th><td width="50%"><%= quizDetail.getDescription()%> </td>
                                    </tr>
                                    <tr>
                                        <th>Duration</th><td><%= quizDetail.getDuration() %> Minute</td>
                                    </tr>
                                    <tr>
                                        <th>Number of question</th><td><%= quizQuestions.size() %></td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <div class="panel-body">
                            <form action="" method="post" id="examForm">
                                <div class="tab-content">
                                    <!-- question content -->
                                    <input type="hidden" name="usedTime" id="usedTime" value="00:00:00">
                                    <% for (int i = 0; i < quizQuestions.size(); i++)
                                        {%> 
                                    <div id="menu<%= i + 1%>" class="tab-pane fade">
                                        <h1 class="margin-bottom-30">Question No.<%= i + 1%></h1>
                                        <h2 class="text-muted"><%= quizQuestions.get(i).getQuestion()%></h2>
                                        <table class="table table-bordered" width="100%" id="examTable">
                                            <thead>
                                                <tr>
                                                    <th width="10%">Select</th>
                                                    <th width="90%">Option</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr><td><input type="radio" name="answer<%= i + 1%>" value="A"></td><td><%= quizQuestions.get(i).getOptA()%></td></tr>
                                                <tr><td><input type="radio" name="answer<%= i + 1%>" value="B"></td><td><%= quizQuestions.get(i).getOptB()%></td></tr>
                                                <tr><td><input type="radio" name="answer<%= i + 1%>" value="C"></td><td><%= quizQuestions.get(i).getOptC()%></td></tr>
                                            </tbody>
                                        </table>
                                            <input type="hidden" name="correctAnswer<%= i + 1%>" value="<%= quizQuestions.get(i).getAns()%>">
                                    </div>
                                    <% } %>

                                    <button type="button" id="btn_previous" class="btn btn-primary">Previous</button>
                                    <button type="button" id="btn_next" class="btn btn-primary">Next</button>
                                    <!--<button href="javascript:;" data-toggle="modal" data-target="#confirmModal"><i class="fa fa-sign-out"></i>Submit</a>
                                    </button>-->
                                    <button type="button" onclick="window.location.href = 'javascript:;'" data-toggle="modal" data-target="#confirmModal" id="btn_submit" class="btn btn-success">Submit</button>
                                    <div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                                    <h4 class="modal-title" id="myModalLabel">Are you sure you want to submit?</h4>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="submit" id="btn_submit" class="btn btn-success">Yes</button>
                                                    <button type="button" class="btn btn-danger" data-dismiss="modal">No</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </form>
                        </div>
                    </div>


                </div>
                <div class="col-md-offset-2 col-md-8">
                    <ul class="pagination pagination-lg" id="questionbar">
                        <!-- question menu -->
                        <% for (int j = 0; j < quizQuestions.size(); j++)
                            {%>
                        <li id="tab<%= j + 1%>"><a href="#menu<%= j + 1%>" data-toggle="tab">Q<%= j + 1%></a></li>
                            <%  }
                            %>

                    </ul>
                </div>
            </div>
        </div>
    </body>
    <script>
        $(document).ready(function () {
        startCount();
        // $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        //   var target = $(e.target).attr("href") // activated tab
        //   alert(target);
        // });
        $("#tab1").addClass('active');
        $("#menu1").addClass('in active');
        $("#btn_previous").click(function () {
        $('#questionbar > .active').prev('li').find('a').trigger('click');
        });
        $("#btn_next").click(function () {
        $('#questionbar > .active').next('li').find('a').trigger('click');
        });
        });
        var interval;
        var minutes = <%= quizDetail.getDuration() %>;
        var seconds = 0;
        var hours = 0;
        window.onload = function () {
        countdown('time');
        }

        function countdown(element) {
        interval = setInterval(function () {
        var el = document.getElementById(element);
        if (seconds == 0) {
        //finish action
        if (minutes == 0) {
        el.innerHTML = "countdown's over!";
        clearInterval(interval);
        alert("Time's up");
        $("#examForm").submit();
        return;
        }
        else {
        minutes--;
        seconds = 60;
        }
        }
        if (minutes > 0) {
        var minute_text = minutes + (minutes > 1 ? ' minutes' : ' minute');
        }
        else {
        var minute_text = '';
        }
        var second_text = seconds > 1 ? 'seconds' : 'second';
        el.innerHTML = minute_text + ' ' + seconds + ' ' + second_text;
        seconds--;
        }, 1000);
        }
    </script> 
</html>
