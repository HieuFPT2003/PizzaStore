

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
          
            <nav>
                <ul id="main-menu">
                    <li>
                        <a href="./login"><h2>Pizza Express</h2></a>
                    </li>
                    <li>
                        <a href="./login">Login</a>
                    </li>
                   <li>
                        <a href="register.jsp">Register</a>
                    </li>
                </ul>
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
