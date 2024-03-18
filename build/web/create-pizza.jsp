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
        <title>Create Pizza</title>
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
            select {
                width: calc(100% - 20px);
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-sizing: border-box;
            }

            select {
                padding: 10px;
            }
            .btn {
                padding: 10px;
                background-color: green;
                color: white;
                border: none;
                border-radius: 5px
            }

        </style><style>
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

        <h1>Create Pizza</h1>
        <br/><br/>

        <form action="./create-pizza" method="post">
            <table>

                <tbody>
                    <tr>
                        <td>Pizza Name</td>
                        <td><input type="text" name="productName" required/></td>
                    </tr>
                    <tr>
                        <td>Price</td>
                        <td><input type="text" name="unitPrice" required /></td>
                    </tr>
                    <tr>
                        <td>Quantity</td>
                        <td><input type="text" name="quantityPerUnit" required /></td>
                    </tr>
                    <tr>
                        <td>Category</td>
                        <td>
                            <select name="categoryId" required>
                                <th:forEach items="${categories}" var="category">
                                    <option value="${category.categoryId}">${category.categoryName}</option>
                                </th:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Supplier</td>
                        <td>
                            <select name="supplierId" required>
                                <th:forEach items="${suppliers}" var="supplier">
                                    <option value="${supplier.supplierId}">${supplier.companyName}</option>
                                </th:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Image Url</td>
                        <td><input type="text" name="productImage" required /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Create" class="btn"/></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
