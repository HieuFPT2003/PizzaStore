<%-- 
    Document   : register
    Created on : Feb 25, 2024, 4:10:43 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Page</title>
        <style>
            body {
                font-family: 'Roboto', sans-serif;
                margin: 0;
                padding: 0;
                background-color: #fff7e5; /* Creamy color for a warm feel */
            }

            .container {
                width: 80%;
                margin: 0 auto;
                padding: 20px;
            }

           h1 {
                text-align: center;
                color: #e64a19; /* Dark orange color for a pizza theme */
            }

            form {
                width: 400px;
                margin: 0 auto;
                padding: 20px;
                background-color: #fff;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            form h1 {
                text-align: center;
                margin-bottom: 20px;
            }

            form table {
                width: 100%;
            }

            form table tr {
                margin-bottom: 20px;
            }

            form table td {
                padding: 10px 0;
            }

            form input[type="text"],
            form input[type="password"],
            form input[type="submit"] {
                width: calc(100% - 30px);
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-sizing: border-box;
            }

            form input[type="submit"] {
                background-color: #333;
                color: white;
                cursor: pointer;
            }

            form input[type="submit"]:hover {
                background-color: #555;
            }

            .error-message {
                color: red;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <%@include file="header-auth.jsp" %>
        <br/>
        <h1>Register here.</h1>
        <br/>
        <form action="./register" method="post">
            <table>
                <tr>
                    <td>Account Id</td>
                    <td style="padding: 5px 15px"><input type="text" name="accountId" required/></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td style="padding: 5px 15px"><input type="password" name="password" required/></td>
                </tr>
                <tr>
                    <td>Confirm Password</td>
                    <td style="padding: 5px 15px"><input type="password" name="confirmPassword" required/></td>
                </tr>
                <tr>
                    <td>Username</td>
                    <td style="padding: 5px 15px"><input type="text" name="username" required/></td>
                </tr>
                <tr>
                    <td>Full Name</td>
                    <td style="padding: 5px 15px"><input type="text" name="fullName" required/></td>
                </tr>
                <tr>
                    <td></td>
                    <td style="padding: 5px 15px"><input type="submit" value="Sign up" style="color: white; background: red; padding: 10px; border-radius: 5px"/></td>
                </tr>
            </table>
            <%--            AccountId: <input type="text" name="accountId" required/>--%>
            <%--            <br/>--%>
            <%--            Password: <input type="password" name="password" required/>--%>
            <%--            <br/>--%>
            <%--            Confirm Password: <input type="password" name="confirmPassword" required/>--%>
            <%--            <br/>--%>
            <%--            Username: <input type="text" name="username" required/>--%>
            <%--            <br/>--%>
            <%--            Full Name: <input type="text" name="fullName" required/>--%>
            <%--            <br/>--%>
            <%--<br/>--%>
            <%--            <input type="submit" value="Sign up" style="color: white; background: #333; padding: 5px; border-radius: 5px"/>--%>
            <th:if test="${message != null}">
                <p>${message}</p>
        </form>
    </body>
</html>
