<%-- 
    Document   : History
    Created on : Nov 18, 2018, 8:32:01 PM
    Author     : Windows 10
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <table>
            <tr>No</tr>
            <tr>method</tr>
            <tr>amount</tr>
            <tr>time</tr>
            <tr>balance</tr>
            </table>
        <c:forEach items="${ac.historyList}" var="h" varStatus="vs">
        <tr>
            <td>${vs.count}</td>
            <td>${h.method}</td>
            <td>${h.amount}</td>
            <td>${h.time}</td>
            <td>${h.balance}</td>
        </tr>
        </c:forEach>
        <a href="MyAccount">Home</a>
    </body>
</html>
