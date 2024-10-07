<%--
  Created by IntelliJ IDEA.
  User: hamza
  Date: 04/10/2024
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Update Request</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/updateTable.css">

</head>
<body>
<h1>Update Request</h1>
<form class="update-form" action="${pageContext.request.contextPath}/requests" method="POST">
    <input type="hidden" name="action" value="update"/>
    <input type="hidden" name="id" value="${request.id}"/>

    <label for="project_name">Project Name:</label>
    <input type="text" id="project_name" name="project_name" value="${request.projectName}" required/><br/>

    <label for="user_type">User Type:</label>
    <input type="text" id="user_type" name="user_type" value="${request.userType}" required/><br/>

    <label for="loan_amount">Loan Amount:</label>
    <input type="number" step="0.01" id="loan_amount" name="loan_amount" value="${request.loanAmount}" required/><br/>

    <label for="loan_duration">Loan Duration:</label>
    <input type="number" step="1" id="loan_duration" name="loan_duration" value="${request.loanDuration}" required/><br/>

    <label for="monthly_payment">Monthly Payment:</label>
    <input type="number" step="0.01" id="monthly_payment" name="monthly_payment" value="${request.monthlyPayment}" required/><br/>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" value="${request.email}" required/><br/>

    <label for="phone">Phone:</label>
    <input type="tel" id="phone" name="phone" value="${request.phone}" required/><br/>

    <label for="civility">Civility:</label>
    <select id="civility" name="civility" required>
        <option value="MADAM" <c:if test="${request.civility == 'MADAM'}">selected</c:if>>MADAM</option>
        <option value="MISS" <c:if test="${request.civility == 'MISS'}">selected</c:if>>MISS</option>
        <option value="SIR" <c:if test="${request.civility == 'SIR'}">selected</c:if>>SIR</option>
    </select><br/>

    <label for="f_name">First Name:</label>
    <input type="text" id="f_name" name="f_name" value="${request.fName}" required/><br/>

    <label for="l_name">Last Name:</label>
    <input type="text" id="l_name" name="l_name" value="${request.lName}" required/><br/>

    <label for="cin">CIN:</label>
    <input type="text" id="cin" name="cin" value="${request.cin}" required/><br/>

    <label for="birth_date">Birth Date:</label>
    <input type="date" id="birth_date" name="birth_date" value="${request.birthDate}" required/><br/>

    <label for="employment_start_date">Employment Start Date:</label>
    <input type="date" id="employment_start_date" name="local_date" value="${request.employmentStartDate}" required/><br/>

    <h4>Avez-vous des crédits en cours ?</h4>
    <div class="radio-group">
        <label class="radio-label">
            <input type="radio" name="has_credits" value="true" <c:if test="${request.hasCredits}">checked</c:if>/>
            <span class="radio-custom"></span>
            oui
        </label>
        <label class="radio-label">
            <input type="radio" name="has_credits" value="false" <c:if test="${!request.hasCredits}">checked</c:if>/>
            <span class="radio-custom"></span>
            non
        </label>
    </div>


    <input type="submit" value="Update"/>
</form>
</body>
</html>

