<%-- 
    Document   : Login
    Created on : Nov 18, 2018, 7:56:01 PM
    Author     : Windows 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="Login" method="post">
            username:<input type="Text" name="username"/>
            password:<input type="password" name="password"/>
            <input type="submit" value="login"/>
        </form>
        ${message}
    </body>
</html>
