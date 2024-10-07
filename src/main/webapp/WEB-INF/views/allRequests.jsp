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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/getAllStyle.css">
</head>
<body>
<div class="container">
    <h1 class="title">Requests List 123</h1>
    <div border="1" class="table-container">
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Project Name</th>
                <th>User Type</th>
                <th>Loan Amount</th>
                <th>Loan Duration</th>
                <th>Monthly Payment</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
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
                    <td class="action-buttons">
                        <a href="${pageContext.request.contextPath}/requests/${request.id}" class="btn details-btn"><img class="icon" src="${pageContext.request.contextPath}/icons/detail.png"></a>
                        <a href="${pageContext.request.contextPath}/requests?action=updateForm&id=${request.id}" class="btn update-btn">
                            <img class="icon" src="${pageContext.request.contextPath}/icons/update.png">
                        </a>

                        <form action="${pageContext.request.contextPath}/requests" method="post" class="delete-form">
                            <input type="hidden" value="${request.id}" name="id">
                            <input type="hidden" value="delete" name="action">
                            <button class="btn delete-btn"><img class="icon" src="${pageContext.request.contextPath}/icons/delete.png"></button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="actions">
        <a href="${pageContext.request.contextPath}/WEB-INF/views/create.jsp" class="btn create-btn">Create New Request</a>
        <a href="${pageContext.request.contextPath}/WEB-INF/views/allRequests.jsp" class="btn all-requests-btn">All Requests</a>
    </div>
</div>
</body>
</html>
