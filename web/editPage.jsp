<%-- 
    Document   : editPage
    Created on : Jul 10, 2021, 2:00:45 AM
    Author     : admin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EDIT PAGE</title>
    </head>
    <body>
        
        <form action="edit" method="POST" id="myForm">
            <c:set var="errors" value="${requestScope.INSERT_ERRORS}" />
            Username <input type="text" name="username" value="${param.username}" readonly /><br>
            Password <input type="password" name="txtPassword" value="${param.password}" /><br>
            <c:if test="${not empty errors.passwordLengthError}" >
                <font color="red">
                ${errors.passwordLengthError}
                </font><br>
            </c:if>
            Lastname <input type="text" name="lastname" value="${param.lastname}" /><br>
            <c:if test="${not empty errors.lastnameLengthErr}" >
                <font color="red">
                ${errors.lastnameLengthErr}
                </font><br>
            </c:if>
            Is Admin <input type="checkbox" name="role" value="ON"
                            <c:if test="${not empty param.role}" > checked="checked" </c:if>
                            /><br>
            <input type="hidden" name="txtSearchValue" value="${param.txtSearchValue}" />
            <input type="button" value="Back" onClick="location.href='searchResult?txtSearchValue=${param.txtSearchValue}'"/>&nbsp;&nbsp;&nbsp;
            <input type="button" value="Save" onclick="if (confirm('Are you sure to Register???')) document.getElementById('myForm').submit();"/>&nbsp;&nbsp;&nbsp;
        </form>
            
    </body>

</html>
