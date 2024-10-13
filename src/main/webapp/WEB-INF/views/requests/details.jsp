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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/details.css">
</head>
<body>
<div class="container">
    <h1>Request Details</h1>
    <div class="request-info">
        <h2>Project Id #${request.id}</h2>
        <p>Project Name: ${request.projectName}</p>
        <p>Client Type: ${request.userType}</p>
        <p>Loan Amount: ${request.loanAmount}</p>
        <p>Loan Duration: ${request.loanDuration}</p>
        <p>Monthly Payment: ${request.monthlyPayment}</p>
        <p>Client Email: ${request.email}</p>
        <p>Client Phone: ${request.phone}</p>
        <p>Civility: ${request.civility}</p>
        <p>Client Name: ${request.fName} ${request.lName}</p>
        <p>Client CIN: ${request.cin}</p>
        <p>Birth Date: ${request.birthDate}</p>
        <p>Employment Start Date: ${request.employmentStartDate}</p>
        <p>Has Credits: ${request.hasCredits}</p>
    </div>

    <div class="status-form">
        <h3>Update Status</h3>
        <form action="${pageContext.request.contextPath}/request/status" method="POST">
            <input type="hidden" name="requestId" value="${request.id}"/>
            <input type="hidden" name="action" value="create"/>
            <select id="statusSelect" name="statusId">
                <option value="Open">Open</option>
                <c:forEach var="status" items="${status}">
                    <option value="${status.id}">${status.status}</option>
                </c:forEach>
            </select>
            <textarea id="statusDescription" name="description" placeholder="Status description" rows="4"></textarea>
            <input type="submit" value="New Status"/>
        </form>
    </div>
    <div class="status-history">
        <h3>Status History</h3>
        <table id="statusTable">
            <thead>
            <tr>
                <th>Date</th>
                <th>Status</th>
                <th>Description</th>
            </tr>
            </thead>
            <tbody id="statusList">
            <c:forEach var="requestStatus" items="${requestStatusList}">
                <tr>
                    <td>${requestStatus.status.status}</td>
                    <td>${requestStatus.description}</td>
                    <td>${requestStatus.date}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
