<%--
  Created by IntelliJ IDEA.
  User: hamza
  Date: 03/10/2024
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Requests</title>
</head>
<body>
<h1>Requests List</h1>

<table border="1">
  <tr>
    <th>ID</th>
    <th>Project Name</th>
    <th>User Type</th>
    <th>Loan Amount</th>
    <th>Loan Duration</th>
    <th>Monthly Payment</th>
    <th>Email</th>
    <th>Phone</th>
  </tr>

  <c:forEach var="request" items="${requests}">
    <tr>
      <td>${request.id}</td>
      <td>${request.projectName}</td>
      <td>${request.userType}</td>
      <td>${request.loanAmount}</td>
      <td>${request.loanDuration}</td>
      <td>${request.monthlyPayment}</td>
      <td>${request.email}</td>
      <td>${request.phone}</td>
      <td>
        <a href="${pageContext.request.contextPath}/requests/${request.id}">Details</a>
        <a href="${pageContext.request.contextPath}/requests/${request.id}">Update</a>
        <form action="${pageContext.request.contextPath}/requests" method="post">
          <input type="hidden" value="${request.id}" name="id">
          <input type="hidden" value="delete" name="action">
          <button>Delete</button>
        </form>
      </td>
    </tr>
  </c:forEach>
</table>

<a href="${pageContext.request.contextPath}/views/create.jsp">Create New Request</a>
<a href="${pageContext.request.contextPath}/views/allRequests.jsp">All Request</a>

</body>
</html>
