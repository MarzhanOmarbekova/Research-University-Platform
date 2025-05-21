<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 16.12.2024
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="link.Edit"/></title>
</head>
<body>

<a href="?lang=en">English</a> |
<a href="?lang=ru">Русский</a> |
<a href="?lang=kz">Қазақша</a>

<h1>Edit User</h1>
<form action="/admin/users/update/${user.id}" method="post">
    <label for="username"><spring:message code="label.Username"/></label>
    <input type="text" id="username" name="username" value="${user.username}" required>
    <br>
    <label for="password"><spring:message code="label.Password"/></label>
    <input type="password" id="password" name="password" value="${user.password}">
    <br>
    <label for="role"><spring:message code="label.Role"/></label>
    <select id="role" name="role">
        <option value="MANAGER" ${user.role == 'MANAGER' ? 'selected' : ''}><spring:message code="role.Manager"/></option>
        <option value="LIBRARIAN" ${user.role == 'LIBRARIAN' ? 'selected' : ''}><spring:message code="role.Librarian"/></option>
        <option value="TEACHER" ${user.role == 'TEACHER' ? 'selected' : ''}><spring:message code="role.Teacher"/></option>
        <option value="STUDENT" ${user.role == 'STUDENT' ? 'selected' : ''}><spring:message code="role.Student"/></option>
    </select>
    <br>
    <label for="school"><spring:message code="label.School"/></label>
    <select id="school" name="school">
        <c:forEach items="${schools}" var="school">
            <option value="${school.name()}" ${school.name() == user.school.name() ? 'selected' : ''}>${school.name()}</option>
        </c:forEach>
    </select>
    <br>
    <button type="submit"><spring:message code="button.Update"/></button>
</form>
</body>
</html>
