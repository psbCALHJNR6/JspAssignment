<%-- 
    Document   : login
    Created on : Nov 14, 2017, 10:53:54 AM
    Author     : a1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <a href="test.jsp">test bootstrap</a>
        <p> Login name is abc and password is 123</p>
        
        <form method="post" action="main">
            <input type="hidden" name="action" value="authenticate" />
            
            <table border="0">
                <tr>
                    <td>Login :</td>
                    <td><input type="text" name="username" maxlength="10" size="15"></td>
                </tr>
                <tr>
                    <td>Password :</td>
                    <td><input type="text" name="password" maxlength="10" size="15"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Login"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
