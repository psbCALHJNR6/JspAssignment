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
             <div class="pageheader"><h1>Edit Question</h1></div>
             <form action="QuestionController"  method="post" class="form-horizontal">
                 <input type="hidden" name="action" value="update">
                 <div class="form-group">
                 Question ID:<input type="text" name="questid" readonly="readonly" value="<%=qBean.getQuestID()%>">
                 </div>
                 <div class="form-group">
                 Question : <input type="text" name="question" value="<%=qBean.getQuestion()%>">
                 </div>
                 <div class="form-group">
                 Option A : <input type="text" name="optA" value="<%=qBean.getOptA()%>">
                 </div>
                 <div class="form-group">
                 Option B : <input type="text" name="optB" value="<%=qBean.getOptB()%>">
                 </div>
                 <div class="form-group">
                 Option C : <input type="text" name="optC" value="<%=qBean.getOptC()%>">
                 </div>
                 <div class="form-group">
                 Correct Answer: <select name="corrAns" id="corrAns">
                     <option value="" disabled>Choose correct answer...</option>
                     <option value="A">A</option>
                     <option value="B">B</option>
                     <option value="C">C</option>
                 </select>
                 </div>
                 <input type="submit" value="Submit">
                 <input type="reset" value="Reset">
             </form>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>

<script type="text/javascript">
    $(document).ready(function(){
        var corrAns = "<%=qBean.getAns()%>";
        $("select#corrAns").val(corrAns);
    })
</script>