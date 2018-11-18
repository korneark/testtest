<%-- 
    Document   : MyAccount
    Created on : Nov 18, 2018, 7:58:02 PM
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
        <h1>MyAccount!</h1>
        name:${ac.name}<br>
        balance:${ac.balance}<br>
        
        <a href="Deposit">Deposit<br></a>
        <a href="Withdraw">Withdraw<br></a>
        <a href="History">History<br></a>
        <a href="Logout">Logout<br></a>
    </body>
</html>
