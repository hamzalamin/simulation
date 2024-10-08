<%--
  Created by IntelliJ IDEA.
  User: hamza
  Date: 08/10/2024
  Time: 01:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Update Request</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/updateTable.css">

</head>

<form class="update-form" action="${pageContext.request.contextPath}/status" method="POST">
    <input type="hidden" name="action" value="update"/>
    <input type="hidden" name="id" value="${request.id}">

    <label for="status">Project Name:</label>
    <input type="text" id="status" name="status" value="${request.status}" required/><br/>

    <input type="submit" value="Update"/>
</form>
</body>
</html>
