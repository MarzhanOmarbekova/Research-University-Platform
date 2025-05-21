<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 17.12.2024
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>My Research Papers</title>
</head>
<body>
<h1>My Research Papers</h1>
<h2>H-index = ${hIndex}</h2>
<c:if test="${hIndex>3}" >
    <h3>you can be supervisor</h3>
</c:if>

<a href="/rps/new">Add New Research Paper</a><br><br>

<form method="get" action="/rps">
    <label for="sortBy">Sort By:</label>
    <select name="sortBy" id="sortBy" onchange="this.form.submit()">
        <option value="citationNum" ${sortBy == 'citationNum' ? 'selected' : ''}>Citations</option>
        <option value="articleLength" ${sortBy == 'articleLength' ? 'selected' : ''}>Article Length</option>
        <option value="publishedDate" ${sortBy == 'publishedDate' ? 'selected' : ''}>Published Date</option>
    </select>
</form>
<TABLE border="1">
    <tr>
        <th>Title</th>
        <th>Article</th>
        <th>Citations</th>
        <th>Published Date</th>
        <th>Actions</th>
    </tr>
    <tbody>
    <c:forEach items="${papers}" var="paper">
        <tr>
            <td>${paper.title}</td>
            <td><a href="/rps/article/${paper.researchPaperId}">${paper.title}</a> </td>
            <td>${paper.citationNum}</td>
            <td>
                <c:choose>
                    <c:when test="${paper.publishedDate != null}">
                        ${paper.publishedDate}
                    </c:when>
                    <c:otherwise>
                        Not Published
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <a href="/rps/edit/${paper.researchPaperId}">Edit</a>
                <a href="/rps/delete/${paper.researchPaperId}">Delete</a>
                <form action="/rps/publish/${paper.researchPaperId}" method="post" style="display:inline;">
                    <button type="submit"
                            <c:if test="${paper.journal != null}">Already Published</c:if>
                    >Publish</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</TABLE>
<br><br>
<a  href="/login">Logout</a>
</body>
</html>
