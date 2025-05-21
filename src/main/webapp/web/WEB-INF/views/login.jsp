<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 16.12.2024
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="link.Login"/> </title>
</head>
<body>
<a href="?lang=en">English</a> |
<a href="?lang=ru">Русский</a> |
<a href="?lang=kz">Қазақша</a><br><br>
<h2><spring:message code="title.LoginPage"/></h2>
<form action="/login" method="post">
    <label for="username"><spring:message code="label.Username"/></label>
    <input type="text" id="username" name="username" required>
    <br>
    <label for="password"><spring:message code="label.Password"/> </label>
    <input type="password" id="password" name="password" required>
    <br>
    <button type="submit"><spring:message code="link.Login"/></button>
    </form>
</body>
</html>