<<<<<<< HEAD

 <%@ page language="java" errorPage="noquizresult.jsp" %>
=======
<%-- 
    Document   : student_quizresult
    Created on : Nov 29, 2017, 9:13:01 PM
    Author     : psb
--%>
 <%@ page errorPage="noquizresult.jsp" %>
>>>>>>> 7d797856d8a5073fa7c45f6a5202ec49c8b8c047
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz Result</title>
        <jsp:include page="import.jsp" />
    </head>
    <body>

        <jsp:include page="navbar.jsp" />
        <div class="container">
          
     
            <canvas id="myChart"></canvas>
            
            Remaining attempt times : <%= request.getAttribute("canAttemptTime")%>
        </div>
        
       
        <jsp:include page="footer.jsp" />
    </body>
</html>
<script src="js/Chart.bundle.js"></script>
<script src="js/utils.js"></script>

<script type="text/javascript">
var ctx = document.getElementById("myChart").getContext('2d');
var myChart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: ["Lowest", "Average", "Highest"],
        datasets: [{
            label: 'Score ',
            data: ["<%out.print(request.getAttribute("lowest"));%>", "<%out.print(request.getAttribute("average"));%>", "<%out.print(request.getAttribute("highest"));%>"],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255,99,132,1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero:true
                }
            }]
        }
    }
});
</script>