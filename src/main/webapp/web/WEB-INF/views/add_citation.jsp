<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 22.12.2024
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Citation</title>
</head>
<body>

<h1>Add Citation for ${paper.title}</h1>

<form action="/rps/citations/add/${paper.researchPaperId}" method="post">
  <label for="citation">Citation:</label><br>
  <input type="text" id="citation" name="citation" required><br><br>
  <button type="submit">Add Citation</button>
</form>

</body>
</html>
