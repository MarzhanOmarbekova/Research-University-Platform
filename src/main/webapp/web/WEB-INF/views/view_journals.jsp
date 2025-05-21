<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 17.12.2024
  Time: 23:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>University Journals</title>
</head>
<body>
<h1>University Journals</h1>
<TABLE border="1">
    <tr>
        <th>Journal Title</th>
        <th>School</th>
        <th>Research Papers</th>
        <th>Actions</th>
    </tr>
    <c:forEach items="${journals}" var="journal" varStatus="status">
        <tr>
            <td>${journal.journal_title}</td>
            <td>${journal.school}</td>
            <td>
                <a href="/journals/${journal.journal_id}/paper">View Papers</a>
            </td>
            <td>
                <c:choose>
                    <c:when test="${subscriptionsStatus[status.index]}">
                        <form method="post" action="/journals/unsubscribe/${journal.journal_id}">
                            <button type="submit">Unsubscribe</button>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form method="post" action="/journals/subscribe/${journal.journal_id}">
                            <button type="submit">Subscribe</button>
                        </form>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
</TABLE>
<a href="/login">Log Out</a>
</body>
</html>
