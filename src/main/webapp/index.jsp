<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Requests</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/home.scss">
</head>
<body>
<div class="container">
    <h1 class="main-title">WELCOME IN SMART BANK!</h1>
    <ol class="links">
        <li><a href="${pageContext.request.contextPath}/requests?action=createForm" class="btn btn-primary">Create New Request</a></li>
        <li><a href="requests">All Request</a></li>
        <li><a href="status">All Status</a></li>
        <li><a href="/hello-servlet">helloservlet</a></li>
    </ol>
    <p class="note">
        <strong>Important Notice:</strong> Please be aware that taking credit from our bank may be considered haram (forbidden) in Islam. We encourage individuals to seek financial solutions that align with their beliefs. If you have any questions or need guidance, please consult with a knowledgeable authority on Islamic finance.
    </p>
</div>

</body>
</html>