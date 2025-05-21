<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 16.12.2024
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="title.AdminControlPage"/></title>
</head>
<body>

<a href="?lang=en">English</a> |
<a href="?lang=ru">Русский</a> |
<a href="?lang=kz">Қазақша</a>

<h1><spring:message code="title.AdminDashboard"/> </h1>
<p><spring:message code="welcome.AdminMessage"/></p>
<ul>
  <li><a href="/admin/users"><spring:message code="link.ManageUsers"/></a></li>
  <li><a href="/admin/users/new"><spring:message code="link.AddNewUser" /></a></li>
  <li><a href="/login"><spring:message code="link.Logout" /></a></li>
</ul>
</body>
</html>
