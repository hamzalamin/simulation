<%--
  Created by IntelliJ IDEA.
  User: hamza
  Date: 08/10/2024
  Time: 01:48
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Status</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/getAllStyle.css">
</head>
<body>
<div class="container">
    <h1 class="title">Status</h1>
    <div border="1" class="table-container">
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Status Name</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="status" items="${status}">
                <tr>
                    <td>${status.id}</td>
                    <td>${status.status}</td>
                    <td class="action-buttons">
                        <a href="${pageContext.request.contextPath}/status/${status.id}" class="btn details-btn"><img
                                class="icon" src="${pageContext.request.contextPath}/icons/detail.png"></a>
                        <a href="${pageContext.request.contextPath}/status?action=updateStatusForm&id=${status.id}"
                           class="btn update-btn">
                            <img class="icon" src="${pageContext.request.contextPath}/icons/update.png">
                        </a>

                        <form action="${pageContext.request.contextPath}/status" method="post" class="delete-form">
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
        <a href="${pageContext.request.contextPath}/status?action=createStatusForm" class="btn btn-primary">Create
            New Status</a>
    </div>
</div>
</body>
</html>
