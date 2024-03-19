<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
        <title>header</title>
        <link rel="stylesheet" href="./asset/styles.css">
        <style>
        </style>
    </head>
    <body>
        <div id="header">
          
            <nav>
                <ul id="main-menu" style="">
                    <li>
                        <a href="./product-management"><h2>Pizza Express</h2></a>
                    </li>
                    <li>
                        <a href="./product-management">Pizzas</a>
                    </li>
                    <li>
                        <a href="./member-management">Members</a>
                    </li>
                    <li>
                        <a href="./order-management">Orders</a>
                    </li>
                    <li>
                        <a href="./sales-report">Sales Report</a>
                    </li>
                </ul>

                <div class="header_login">
                    <c:if test="${sessionScope.account == null}">
                        
                        <a href="register.jsp">Register</a>
                        /
                        <a href="login.jsp">Login</a>
                    </c:if>
                        <c:if test="${sessionScope.account != null}">
                            Welcome, <a href="account"> ${sessionScope.account.username}</a>
                            /
                            <a href="./logout">Logout</a>
                            
                        </c:if>    
                   
                </div>
            </nav>
        </div>
        
        <script>
        $(document).ready(function () {
            $('#toggle').click(function () {
                $('nav').slideToggle();
            })
        })
        </script>
    </body>
</html>
