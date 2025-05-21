<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 16.12.2024
  Time: 13:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>Research-Oriented University</title>
</head>
<body>
    <img src="/images/logo.png" height=400px width="500px"><br><br>
    <a href="?lang=en">English</a> |
    <a href="?lang=ru">Русский</a> |
    <a href="?lang=kz">Қазақша</a><br><br>
    <form action="/login" method="get">
        <button type="submit" > <spring:message code="link.Login"/></button>
    </form>
</body>
</html>
