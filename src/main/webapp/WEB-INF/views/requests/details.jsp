<%--
  Created by IntelliJ IDEA.
  User: hamza
  Date: 08/10/2024
  Time: 21:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Request Details</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/updateTable.css">
</head>
<body>
<h1>Request Details</h1>

<p>Project Name: ${request.projectName}</p>
<p>User Type: ${request.userType}</p>

<h2>Update Status</h2>
<form action="${pageContext.request.contextPath}/requests/updateStatus" method="POST">
  <input type="hidden" name="id" value="${request.id}"/>

  <label for="status">Select Status:</label>
  <select id="status" name="statusId" required>
    <c:forEach var="status" items="${statuses}">
      <option value="${status.id}" <c:if test="${status.id == request.status.id}">selected</c:if>>${status.status}</option>
    </c:forEach>
  </select><br/>

  <input type="submit" value="Update Status"/>
</form>
</body>
</html>
