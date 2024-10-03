<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create/Update Request</title>
</head>
<body>
<h1>Create or Update Request</h1>

<form action="${pageContext.request.contextPath}/requests" method="post">
    <input type="hidden" name="action" value="create"/>

    <label for="project_name">Project Name:</label>
    <input type="text" id="project_name" name="project_name" required/><br/>

    <label for="user_type">User Type:</label>
    <input type="text" id="user_type" name="user_type" required/><br/>

    <label for="loan_amount">Loan Amount:</label>
    <input type="number" step="0.01" id="loan_amount" name="loan_amount" required/><br/>

    <label for="loan_duration">Loan Duration:</label>
    <input type="number" step="0.01" id="loan_duration" name="loan_duration" required/><br/>

    <label for="monthly_payment">Monthly Payment:</label>
    <input type="number" step="0.01" id="monthly_payment" name="monthly_payment" required/><br/>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required/><br/>

    <label for="phone">Phone:</label>
    <input type="text" id="phone" name="phone" required/><br/>

    <label for="f_name">First Name:</label>
    <input type="text" id="f_name" name="f_name" required/><br/>

    <label for="l_name">Last Name:</label>
    <input type="text" id="l_name" name="l_name" required/><br/>

    <label for="cin">CIN:</label>
    <input type="text" id="cin" name="cin" required/><br/>

    <label for="birth_date">Birth Date:</label>
    <input type="date" id="birth_date" name="birth_date" required/><br/>

    <label for="local_date">Employment Start Date:</label>
    <input type="date" id="local_date" name="local_date" required/><br/>

    <label for="has_credits">Has Credits:</label>
    <input type="checkbox" value="true" id="has_credits" name="has_credits"/><br/>

    <button type="submit">Submit</button>
</form>
</body>
</html>
