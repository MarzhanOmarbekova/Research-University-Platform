<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 16.12.2024
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sping" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
  <title><spring:message code="title.Users"/></title>
</head>
<a href="?lang=en">English</a> |
<a href="?lang=ru">Русский</a> |
<a href="?lang=kz">Қазақша</a>

<h1>All Users</h1>
<a href="/admin/users/new"><spring:message code="link.AddNewUser"/></a><br><br>
<TABLE border="1">
  <tr>
    <th>ID</th>
    <th><spring:message code="th.Username"/></th>
    <th><spring:message code="th.Role"/> </th>
    <th><spring:message code="th.School"/></th>
    <th><spring:message code="label.actions"/></th>
  </tr>
  <tbody>
  <c:forEach items="${users}" var="user">
    <tr>
      <td><c:out value = "${user.id}"/></td>
      <td><c:out value = "${user.username}"/></td>
      <td><c:out value = "${user.role}"/></td>
      <td><c:out value = "${user.school}"/></td>
      <td>
        <a href="/admin/users/edit/${user.id}"><spring:message code="link.Edit"/></a>
        <a href="/admin/users/delete/${user.id}"><spring:message code="link.Delete"/></a>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</TABLE>
<br><br>
<a href="/admin"><spring:message code="link.GoToOptions"/></a>
</html>
