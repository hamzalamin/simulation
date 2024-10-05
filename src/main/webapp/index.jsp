<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Requests</title>
</head>
<body>
<h1>WELCOME IN SMART BANK!</h1>

<a href="${pageContext.request.contextPath}/views/create.jsp">Create New Request</a>
<a href="requests">All Request</a>

</body>
</html>