<%--
  Created by IntelliJ IDEA.
  User: yulkino
  Date: 05.10.2021
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>registration</title>
    <style>
        .text1{
            font: 14pt sans-serif;
        }
    </style>
</head>
<body>
    <br>
    <h1 align="center">Registration</h1>
    <br>
    <form align="center" method="post">
        <div class="text1">
            <label for="nickname">nickname: </label>
            <input type="text" id="nickname" name="nickname" maxlength="12">
        </div>
        <br>
        <div class="text1">
            <label for="pass">password: </label>
            <input type="password" id="pass" name="pass" maxlength="12">
        </div>
        <br>
        <div class="text1">
            <label for="email">email: </label>
            <input type="email" id="email" name="email" >
        </div>
        <br>
        <div>
            <input class="text1" type="submit" value="register">
        </div>
        <br>
        <div>
            <% if(request.getAttribute("error") != null){ %>
            <%=request.getAttribute("error")%>
            <%}%>
        </div>
    </form>
</body>
</html>
