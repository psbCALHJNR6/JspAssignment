

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
        <div class="container">
        <form action="qController" method="post">
            <h1 class="my-4">Create question</h1>
            <label for="qName">Question:</label><input type="text" class="form-control" name="qName" id="qName"> <br>
            <label for="optA">Option A</label><input type="text" class="form-control" name="optA" id="optA"> <br>
            <label for="optB">Option B</label><input type="text" class="form-control" name="optB" id="optB"> <br>
            <label for="optC">Option C</label><input type="text" class="form-control" name="optC" id="optC"> <br>
            <label for="corrAns">Correct Ans</label>
            <select name="corrAns" id="corrAns" class="form-control">
                <option value="A">A</option>
                <option value="B">B</option>
                <option value="C">C</option>
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