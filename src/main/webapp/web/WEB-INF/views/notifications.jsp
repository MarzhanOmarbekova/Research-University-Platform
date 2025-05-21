<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 23.12.2024
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Notifications</title>
</head>
<body>
<h1>Notifications</h1>
<ul>
    <c:forEach items="${announcements}" var="announcement">
        <li>${announcement.message} </li>
    </c:forEach>
</ul>
<a href="/journals">Back to Journals</a>
</body>
</html>
