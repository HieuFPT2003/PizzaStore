<%@ page import="dtos.AccountDTO" %>
<%@ page import="daos.AccountDAO" %>
<%@ page import="dtos.CustomerDTO" %>
<%@ page import="daos.CustomerDAO" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm</title>
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

            table {
                width: 50%;
                margin: 0 auto;
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
            input[type="submit"] {
                width: calc(100% - 20px);
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-sizing: border-box;
            }

            input[type="submit"] {
                background-color: #e64a19; /* Dark orange color for a pizza theme */
                color: #fff;
                cursor: pointer;
            }

            input[type="submit"]:hover {
                background-color: #bf360c; /* Darker shade of orange for hover effect */
            }

            input[type="text"][readonly] {
                background-color: #f4f4f4;
                cursor: not-allowed;
            }
        </style>

    </head>
    <body>
        <%@include file="header-user.jsp" %>
        <br/>
        <h1>Confirm Order</h1>
        <br/>
        <table>
            <form action="./order" method="post">
                <tbody>
                    <tr>
                        <td>Product Name</td>
                        <td style="padding: 5px 10px;">${product.productName}</td>
                    </tr>
                    <tr>
                        <td>Unit Price</td>
                        <td style="padding: 5px 10px;">$${product.unitPrice}</td>
                    </tr>
                    <tr>
                        <td>Category</td>
                        <td style="padding: 5px 10px;">${product.category.categoryName}</td>
                    </tr>
                    <tr>
                        <td>Supplier</td>
                        <td style="padding: 5px 10px;">${product.supplier.companyName}</td>
                    </tr>
                    <tr>
                        <td>Quantity</td>
                        <td style="padding: 5px 10px;">1</td>
                    </tr>
                <input type="hidden" value="1" name="quantity">
                <input type="hidden" value="${product.productId}" name="productId">
                <tr>
                    <td>Contact Name</td>
                    <td style="padding: 5px 10px;"><input type="text" name="contactName" value="${param.contactName}" required></td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td style="padding: 5px 10px;"><input type="text" name="address" value="${param.address}" required></td>
                </tr>
                <tr>
                    <td>Phone</td>
                    <%
                        AccountDTO account = (AccountDTO) session.getAttribute("account");
                        if (account.getCustomerId() > 0) {
                            CustomerDAO customerDAO = new CustomerDAO();
                            CustomerDTO customerDTO = customerDAO.getCustomerById(account.getCustomerId());
                    %>
                    <td style="padding: 5px 10px;"><input type="text" name="phone" value="<%=customerDTO.getPhone()%>" readonly></td>

                    <%
                    } else {
                    %>
                    <td style="padding: 5px 10px;"><input type="text" name="phone" value="${param.phone}" required /></td>
                        <%
                            }
                        %>
                </tr>
                <tr>
                    <td></td>
                    <td style="padding: 5px 10px;"><input type="submit" value="Order"/></td>
                </tr>

                </tbody>
            </form>
        </table>
    </body>
</html>
