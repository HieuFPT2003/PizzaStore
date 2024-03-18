<%@ taglib prefix="th" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Document   : create-pizza
    Created on : Feb 25, 2024, 7:20:19 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit</title>
       <style>
    body {
        font-family: 'Roboto', sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f9f5f2; /* Light beige color for a warm feel */
    }

    .container {
        width: 80%;
        margin: 0 auto;
        padding: 20px;
    }

    h1 {
        text-align: center;
        color: #d84315; /* Dark red color for a pizza theme */
    }

    form {
        width: 50%;
        margin: 0 auto;
        padding: 20px;
        background-color: #fff;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    table {
        width: 100%;
        border-collapse: collapse;
    }

    table td {
        padding: 10px;
        border-bottom: 1px solid #ddd;
    }

    table td:first-child {
        width: 30%;
    }

    input[type="text"],
    input[type="password"],
    select {
        width: calc(100% - 20px);
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-sizing: border-box;
        font-family: 'Roboto', sans-serif;
    }

    select {
        padding: 10px;
    }

    .btn {
        padding: 10px;
        background-color: #d84315; /* Dark red color for a pizza theme */
        color: #fff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    .btn:hover {
        background-color: #bf360c; /* Darker shade of red for hover effect */
    }
</style>

</head>
<body>
<%@include file="header.jsp" %>
<br/><br/>

<h1>Edit Member</h1>
<br/><br/>

<form action="./edit-member" method="post">
    <table>
        <th:set var="account" value="${account}" />
        <tbody>
        <tr>
            <td>AccountID</td>
            <td><input type="text" name="accountId" value="${account.accountId}" readonly/></td>
        </tr>
        <tr>
            <td>Username</td>
            <td><input type="text" name="username" value="${account.username}" required/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" value="${account.password}" required /></td>
        </tr>
        <tr>
            <td>Full Name</td>
            <td><input type="text" name="fullName" value="${account.fullName}" required /></td>
        </tr>

        <tr>
            <td>Type</td>
            <td>
                <select name="type" required>
                    <th:if test="${account.type == 1}">
                        <option value="1" selected >Staff</option>
                        <option value="2">Normal User</option>
                    </th:if>
                    <th:if test="${account.type == 2}">
                        <option value="1">Staff</option>
                        <option value="2" selected>Normal User</option>
                    </th:if>
                </select>
            </td>
        </tr>
        <td></td>
        <td><input type="submit" value="Edit" class="btn" /></td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>
