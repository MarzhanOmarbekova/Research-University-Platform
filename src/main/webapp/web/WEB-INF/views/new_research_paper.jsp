<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 17.12.2024
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CREATE NEW RESEARCH PAPER</title>
</head>
<body>
<h1>New Research Paper</h1>
<form method="post" name="/rps/new">
    <label for="title">Title:</label>
    <input type="text" id="title" name="title" required>
    <br>
    <label for="article">Article:</label>
    <input type="text" id="article" name="article" required>
    <br>
    <button type="submit">Add paper</button>
</form>

</body>
</html>
