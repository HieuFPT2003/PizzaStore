<%--
    Document   : orderpage
    Created on : Feb 26, 2024, 8:38:15 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sale Report</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 10px 20px;
                background-color: #f4f4f4;
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
                text-align: center;
                margin-bottom: 20px;
            }

            form label {
                margin-right: 10px;
            }

            form input[type="date"],
            form input[type="submit"] {
                padding: 10px;
                margin-right: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }

            form input[type="submit"] {
                background-color: #333;
                color: white;
                cursor: pointer;
            }

            form input[type="submit"]:hover {
                background-color: #555;
            }

            table {
                width: 100%;
                border-collapse: collapse;
            }

            table th, table td {
                padding: 10px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }

            table th {
                background-color: #f2f2f2;
                color: #333;
            }

            table tr:hover

        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <br/>
        <h1>Sales Report</h1>
        <br/>
        <br/>
        <form method="post" action="./sales-report">
            <label>From Date</label>
            <input type="date" name="fromDate" value="${param.fromDate}" required/>
            <label>To Date</label>
            <input type="date" name="toDate" value="${param.toDate}" required/>
            <input type="submit" value="Find" style="background-color: red"/>
        </form>
        <br/><br/>
    <th:if test="${message != null}">
        <p>${message}</p>
    </th:if>
    <th:if test="${salesReport != null}">
        <br/>
        <table>
            <tr >
                <th>OrderId</th>
                <th>Order Date</th>
                <th>Total Sales</th>

            </tr>
            <c:forEach items="${salesReport}" var="salesReportItem">
                <tr style="border-bottom: #777777 1px solid">
                    <td style="padding: 5px 10px;">${salesReportItem.orderId}</td>
                    <td style="padding: 5px 10px;">${salesReportItem.orderDate}</td>
                    <td style="padding: 5px 10px;">$${salesReportItem.totalSales}</td>

                </tr>
            </c:forEach>
        </table>
    </th:if>
</body>
</html>
