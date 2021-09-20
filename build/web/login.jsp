<%-- 
    Document   : login
    Created on : Jul 11, 2021, 11:37:58 PM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LOGIN</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <form action="login" method="POST">
            Username <input type="text" name="txtUsername" value="${param.txtUsername}" /><br>
            Password <input type="password" name="txtPassword" value="" /><br>
            <c:if test="${not empty requestScope.LOGIN_ERRORS}" >
                <font color="red" >
                    ${requestScope.LOGIN_ERRORS.usernamePasswordWrongErr}
                </font><br>
            </c:if>
            <input type="submit" value="Login" name="btAction" />
            <input type="reset" value="Reset" />
        </form><br/>
        <a href="shoppingPage?numInPage=4&pageNumber=1">Shopping Now!!!</a>
        <a href="createNewAccount.html">Sign Up</a>
    </body>
</html>
