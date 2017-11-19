<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="import.jsp" />
    </head>
    <body>

        <jsp:include page="navbar.jsp" />

        <div class="container">
            
            <div class="jumbotron text-center">
                <img src="img/elearning-header.jpg" alt="">
                <h1>Welcome to E-Learning platform</h1>
                <div class="row justify-content-md-center">
                    <form method="post" action="main">
                        <input type="hidden" name="action" value="authenticate" />
                        <div class="form-group">
                        <input type="text" class="form-control" name="username" maxlength="10" size="15" placeholder="Username">
                        </div>
                        <div class="form-group">
                        <input type="text" name="password" class="form-control" maxlength="10" size="15" placeholder="password">
                        </div>
                        <div class="form-group">
                        <input type="submit" class="btn btn-success" value="Login">
                        </div>
                    </form>
                </div>
                <p>You are not logged in.</p>
            </div>
            <center>
                <!--<img src="img/elearning-header.jpg.jpg" alt="">-->
            </center>
        </div>

        <jsp:include page="footer.jsp" />


    </body>
</html>
