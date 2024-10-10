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
    <style>
        .filter-forms{
            display: flex !important;
            justify-content: center;
        }
        .filter-forms
        input[type="date"]
        {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            transition: border-color 0.3s;
            margin-left: 15px;
        }
        .filter-forms
        select
        {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            transition: border-color 0.3s;
        }
        .filter-btn{
            background-color: #2c2828;
            color: white;
            border-radius: 3px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="title">Requests List 123</h1>
    <div class="filter-forms">
        <form action="${pageContext.request.contextPath}/requests" method="post" class="filter-form">
            <input type="hidden" name="action" value="filterByStatus">
            <select name="status" id="status">
                <option value="">Select Status</option>
                <c:forEach var="statuses" items="${statuses}">
                    <option value="${statuses.id}">${statuses.status}</option>
                </c:forEach>
            </select>
            <button type="submit" class="filter-btn">Filter by Status</button>
        </form>
        <form action="${pageContext.request.contextPath}/requests" method="post" class="filter-form">
            <input type="hidden" name="action" value="filterByDate">
            <input name="birth_date" id="birth_date" type="date">
            <button type="submit" class="filter-btn">Filter by Date</button>
        </form>
    </div>
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
                        <a href="${pageContext.request.contextPath}/request/status?action=requestStatusForm&id=${request.id}"
                           class="btn details-btn"><img class="icon"
                                                        src="${pageContext.request.contextPath}/icons/detail.png"></a>
                        <a href="${pageContext.request.contextPath}/requests?action=updateForm&id=${request.id}"
                           class="btn update-btn">
                            <img class="icon" src="${pageContext.request.contextPath}/icons/update.png">
                        </a>

                        <form action="${pageContext.request.contextPath}/requests" method="post" class="delete-form">
                            <input type="hidden" value="${request.id}" name="id">
                            <input type="hidden" value="delete" name="action">
                            <button class="btn delete-btn"><img class="icon"
                                                                src="${pageContext.request.contextPath}/icons/delete.png">
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="actions">
        <a href="${pageContext.request.contextPath}/requests?action=createForm" class="btn btn-primary">Create New
            Request</a>
    </div>
</div>
</body>
</html>
