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
        <title>PizzaStore</title>
        <script type="text/javascript">
            function doDelete(id) {
                if (confirm("Are you sure to delete product with id = " + id)) {
                    window.location = "./delete-product?productId=" + id;
                }
            }
        </script>
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

    </head>
    <body>
        <%@include file="header.jsp" %>
        <br/>
        <h2>Pizzas</h2>
        <br/>

        <a style="background-color: red; border-radius: 10px; color: white; padding: 10px 30px; text-decoration: none; float: right; margin-right: 50px " href="./create-pizza" >Add</a>
        <br/>
        <br/>
    <th:if test="${message != null}">
        <p>${message}</p>
    </th:if>
    <th:if test="${pizzas != null}">
        <br/>
        <table>
            <tr >
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Description</th>
                <th>Image Url</th>
                <th>Category</th>
                <th>Actions</th>
            </tr>
            <c:forEach items="${pizzas}" var="pizza">
                <tr style="border-bottom: #777777 1px solid">
                    <td style="padding: 5px 10px;">${pizza.productId}</td>
                    <td style="padding: 5px 10px;">${pizza.productName}</td>
                    <td style="padding: 5px 10px;">$${pizza.unitPrice}</td>
                    <td style="padding: 5px 10px;">${pizza.category.description}</td>
                    <td style="padding: 5px 10px;">${pizza.productImage}</td>
                    <td style="padding: 5px 10px;">${pizza.category.categoryName}</td>

                    <td>
                        <a href="./edit-product?productId=${pizza.productId}">Edit | </a>
                        <a href="./detail-product?productId=${pizza.productId}">Details | </a>
                        <a onclick="doDelete('${pizza.productId}')" href="#">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </th:if>
</body>
</html>
