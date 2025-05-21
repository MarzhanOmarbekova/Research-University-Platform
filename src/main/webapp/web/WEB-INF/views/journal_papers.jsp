
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 22.12.2024
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Research Papers of ${journal.journal_title}</title>
</head>
<body>

<h1>Research Papers of ${journal.journal_title}</h1>

<a href="/journals">Back to Journals</a><br><br>

<form method="get" action="/journals/${journal.journal_id}/paper">
    <label for="sortBy">Sort By:</label>
    <select name="sortBy" id="sortBy" onchange="this.form.submit()">
        <option value="citationNum" ${sortBy == 'citationNum' ? 'selected' : ''}>Citations</option>
        <option value="articleLength" ${sortBy == 'articleLength' ? 'selected' : ''}>Article Length</option>
        <option value="publishedDate" ${sortBy == 'publishedDate' ? 'selected' : ''}>Published Date</option>
    </select>
</form>

<TABLE border="1">
    <thead>
        <tr>
            <th>Title</th>
            <th>Article</th>
            <th>Citations</th>
            <th>Published Date</th>
            <th>Add Citation</th>
        </tr>
    </thead>

    <tbody>
    <c:forEach items="${journal.journal_papers}" var="paper">
        <tr>
            <td>${paper.title}</td>
            <td><a href="/rps/article/${paper.researchPaperId}">Read Article</a> </td>
            <td>${paper.citationNum}</td>
            <td>${paper.publishedDate}</td>
            <td>
                <a href="/rps/citations/add/${paper.researchPaperId}">Add Citation</a>
            </td>
        </tr>
    </c:forEach>

    </tbody>


</TABLE>


</body>
</html>
