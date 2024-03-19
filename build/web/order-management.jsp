<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PizzaStore</title>
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
                if(confirm("Are you sure to delete member with id = " + id)) {
                    window.location = "./delete-member?accountId=" + id;
                }
            }
        </script>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <br/>
        <h2>Orders</h2>
        <br/>

        <br/>
        <br/>
        <th:if test="${message != null}">
            <p>${message}</p>
        </th:if>
        <th:if test="${orderDetails != null}">
            <br/>
        <table>
            <tr >
                <th>ID</th>
                <th>Contact Name</th>
                <th>Ship Address</th>
                <th>Phone</th>
                <th>Product</th>
                <th>Category</th>
                <th>Unit Price</th>
                <th>Quantity</th>
                <th>Order Date</th>

            </tr>
            <c:forEach items="${orderDetails}" var="orderDetail">
                <tr style="border-bottom: #777777 1px solid">
                    <td style="padding: 5px 10px;">${orderDetail.order.orderId}</td>
                    <td style="padding: 5px 10px;">${orderDetail.order.customer.contactName}</td>
                    <td style="padding: 5px 10px;">${orderDetail.order.customer.address}</td>
                    <td style="padding: 5px 10px;">${orderDetail.order.customer.phone}</td>
                    <td style="padding: 5px 10px;">${orderDetail.product.productName}</td>
                    <td style="padding: 5px 10px;">${orderDetail.product.category.categoryName}</td>
                    <td style="padding: 5px 10px;">${orderDetail.product.unitPrice}</td>
                    <td style="padding: 5px 10px;">${orderDetail.quantity}</td>
                    <td style="padding: 5px 10px;">${orderDetail.order.orderDate}</td>

                </tr>
            </c:forEach>
        </table>
        </th:if>
    </body>
</html>
