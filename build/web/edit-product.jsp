<%@ taglib prefix="th" uri="http://java.sun.com/jsp/jstl/core" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit</title>
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


        </style>
    </head>
    <body>
    <%@include file="header.jsp" %>
    <br/><br/>

        <h1>Edit Pizza</h1>
        <th:set var="product" value="${requestScope.product}" />
    <br/><br/>

        <form action="./edit-product" method="post">
            <table>

                <tbody>
                    <input type="hidden" name="productId" value="${product.productId}" />
                    <tr>
                        <td>Pizza Name</td>
                        <td><input type="text" name="productName" value="${product.productName}" required/></td>
                    </tr>
                    <tr>
                        <td>Price</td>
                        <td><input type="text" name="unitPrice" value="${product.unitPrice}"  required /></td>
                    </tr>
                    <tr>
                        <td>Quantity</td>
                        <td><input type="text" name="quantityPerUnit" value="${product.quantityPerUnit}" required /></td>
                    </tr>
                    <tr>
                        <td>Category</td>
                        <td>
                            <select name="categoryId" required>
                                <th:forEach items="${categories}" var="category">
                                    <th:if test="${category.categoryId.equals(product.category.categoryId) }">
                                        <option value="${category.categoryId}" selected>${category.categoryName}</option>
                                    </th:if>
                                    <option value="${category.categoryId}" >${category.categoryName}</option>

                                </th:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Supplier</td>
                        <td>
                            <select name="supplierId" required>
                                <th:forEach items="${suppliers}" var="supplier">
                                    <th:if test="${supplier.supplierId.equals(product.supplier.supplierId) }">
                                        <option value="${supplier.supplierId}" selected>${supplier.companyName}</option>
                                    </th:if>
                                    <option value="${supplier.supplierId}" >${supplier.companyName}</option>

                                </th:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Image Url</td>
                        <td><input type="text" name="productImage" required value="${product.productImage}"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Edit" class="btn"/></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
