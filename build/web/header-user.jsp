<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>JSP Page</title>
        <link rel="stylesheet" href="./asset/styles.css">

    </head>
    <body>
        <div id="header">
            <div class="header__user-name">
                <div class="header__user--left">
                    <div class="header__name--logo">
                        <img class="logo" src="asset/img/logo.jpg">
                    </div>
                    <div class="header__user-name--brand">
                        <a href="./home"><h2>Pizza Express</h2></a>
                    </div>
                </div>
                <div class="header__user--middle">
                    <h4 style ="color: #e64a19">Cheap delicious pizza delivered to your</h4>
                </div>
                <div class="header__user--right">
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
            </div>
            <nav>
                <ul id="main-menu">
                    <li>
                        <a href="./home">Home</a>
                    </li>
                    <li>
                        <c:if test="${sessionScope.account != null}">
                            <a href="./my-order?accountId=${sessionScope.account.username}">My Orders</a>
                        </c:if>
                    </li>
                    <li>
                        <a href="https://www.pizzaexpress.vn/chinh-sach/">Policy</a>
                    </li>
                    <li>
                        <a href="https://www.facebook.com/profile.php?id=100038669987144">BLOG</a>
                    </li>
                    <li>
                        <a href="#">About Us</a>
                    </li>
                    <li class="callShop">
                        <div class = "callShop__top">Call Now - Ship (24/7)</div>
                        <div class = "callShop__bottom">08.6782.4950</div>
                    </li>

                </ul>

            </nav>

            <div class="banner">
                <img height="30%" class="banner__img" src="asset/img/pizza-banner.jpg">
            </div>
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
