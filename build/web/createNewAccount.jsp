<%-- 
    Document   : createNewAccount
    Created on : Jul 10, 2021, 10:35:08 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h1>Create Account</h1>
        <form action="register" method="POST">
            <c:set var="errors" value="${requestScope.INSERT_ERRORS}" />
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" /><br>
            <c:if test="${not empty errors.usernameLengthError}" >
                <font color="red">
                    ${errors.usernameLengthError}
                </font><br>
            </c:if>
            <c:if test="${not empty errors.usernameIsExisted}" >
                <font color="red">
                    ${errors.usernameIsExisted}
                </font><br>
            </c:if>
            Password* <input type="password" name="txtPassword" value="" /><br>
            <c:if test="${not empty errors.passwordLengthError}">
                <font color="red">
                    ${errors.passwordLengthError}
                </font><br>
            </c:if>
            Confirm*  <input type="password" name="txtConfirm" value="" /><br>
            <c:if test="${not empty errors.confirmNotMatch}">
                <font color="red">
                    ${errors.confirmNotMatch}
                </font><br>
            </c:if>
            Lastname  <input type="text" name="txtLastname" value="${param.txtLastname}" /><br>
            <c:if test="${not empty errors.lastnameLengthErr}">
                <font color="red">
                    ${errors.lastnameLengthErr}
                </font><br>
            </c:if>
                <input type="button" value="Back" onClick="location.href='loginPage'"/>&nbsp;&nbsp;&nbsp;
            <input type="submit" value="Create New Account" />&nbsp;&nbsp;&nbsp;
            <input type="reset" value="Reset"/>
        </form>
    </body>
</html>
