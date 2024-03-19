

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f4;
            }

            .container {
                width: 80%;
                margin: 0 auto;
                padding: 20px;
            }

            h1 {
                text-align: center;
                color: #333;
            }

            form {
                width: 300px;
                margin: 0 auto;
                padding: 20px;
                background-color: #fff;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            form p {
                text-align: center;
                color: #555;
            }

            form input[type="text"],
            form input[type="password"],
            form input[type="submit"] {
                width: 100%;
                padding: 10px;
                margin-bottom: 10px;
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
        <h1>Please log in here.</h1>
        <br/>
        <br/>

        <form action="./login" method="post">
            ID <input type="text" name="accountId" placeholder="Please input your id" required/>
            <br/>
            Password  <input type="password" name="password" placeholder="Please input your password" required/>
            <br/>
            <br/>

            <input type="submit" value="Log in" style="color: black; background: darkorange; padding: 10px; border-radius: 5px"/>
            <div style="margin-top:10px">
                <a class="register_form" href="register.jsp" style=" font-size:12px ;text-decoration: none;color: black; background: white; padding: 10px 125px; border-radius: 5px; border:1px solid darkorange">Register</a>
            </div>
            <th:if test="${message != null}">
                <p style="margin-top:20px ">${message}</p>
        </form>
       
    </body>

</html>
