<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 17.12.2024
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Research Paper</title>
</head>
<body>
<h1>Edit Research Paper</h1>
<form method="post" action="/rps/update/${researchPaper.researchPaperId}">
  <label for="title">Title:</label>
  <input type="text" id="title" name="title" value="${researchPaper.title}" required>
  <br>
  <label for="article">Article:</label>
  <input type="text" id="article" name="article" value="${researchPaper.article}" required>
  <br>
  <button type="submit">Update Paper</button>
</form>
</body>
</html>
