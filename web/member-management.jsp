

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PizzaExpress</title>
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
                padding-left: 40px; /* Adding padding to the left */
                padding-right: 40px; /* Adding padding to the right */
            }

            h2 {
                text-align: center;
                color: #e64a19; /* Dark orange color for a pizza theme */
            }

            a.button {
                background-color: green;
                border-radius: 5px;
                color: white;
                padding: 10px;
                text-decoration: none;
            }

            a.button:hover {
                background-color: darkgreen;
            }

            table {
                width: 100%;
                border-collapse: collapse;
            }

            table th,
            table td {
                padding: 10px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }

            table th {
                background-color: #f2f2f2;
                color: #333;
            }

            table tr:hover {
                background-color: #f9f9f9;
            }

            .actions a {
                text-decoration: none;
                color: #333;
                margin-right: 10px;
            }

            .actions a:hover {
                color: blue;
            }
        </style>
        <script type="text/javascript">
            function doDelete(id) {
                if (confirm("Are you sure to delete member with id = " + id)) {
                    window.location = "./delete-member?accountId=" + id;
                }
            }
        </script>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <br/>
        <h2>Members</h2>
        <br/>

        <a style="background-color: red; border-radius: 10px; color: white; padding: 10px 30px; text-decoration: none; float: right; margin-right: 50px " href="./create-member" >Create new</a>
        <br/>
        <br/>
    <th:if test="${message != null}">
        <p>${message}</p>
    </th:if>
    <th:if test="${accounts != null}">
        <br/>
        <table>
            <tr >
                <th>ID</th>
                <th>Username</th>
                <th>Full Name</th>
                <th>Type</th>
                <th>Actions</th>
            </tr>
            <c:forEach items="${accounts}" var="account">
                <tr style="border-bottom: #777777 1px solid">
                    <td style="padding: 5px 10px;">${account.accountId}</td>
                    <td style="padding: 5px 10px;">${account.username}</td>
                    <td style="padding: 5px 10px;">${account.fullName}</td>
                    <td style="padding: 5px 10px;">
                        ${account.type == (1) ? 'Staff' : 'Normal User'}
                    </td>

                    <td>
                        <a href="./edit-member?accountId=${account.accountId}">Edit | </a>
                        <a onclick="doDelete('${account.accountId}')" href="#">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </th:if>
</body>
</html>
