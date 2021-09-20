<%-- 
    Document   : shop
    Created on : Jul 11, 2021, 2:18:07 PM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="Resource/css/style.css" />" />
    </head>
    <body>
        <c:if test="${not empty sessionScope.ACCOUNT.lastname}" >
            <font color="red">
            Welcome, ${sessionScope.ACCOUNT.lastname}
            </font>
        </c:if>
        <h1>Book Store</h1>
        <div>
            <form action="addProduct" method="post">
                <div class="flex-row">
                    <c:forEach var="item" items="${requestScope.LIST_PRODUCT}">
                        <div class="box">
                            <img src="<c:url value="Resource/Photo/SE001.jpg" />">
                            <h2 class="center">
                                ${item.name}
                            </h2>
                            Buy <input type="checkbox" name="productChoose" value="${item.sku}" />
                        </div>
                    </c:forEach>
                    <c:url var="decre" value="shoppingPage" >
                        <c:param name="pageNumber" value="${param.pageNumber - 1}" />
                    </c:url>
                    <c:url var="incre" value="shoppingPage" >
                        <c:param name="pageNumber" value="${param.pageNumber + 1}" />
                    </c:url><br>
                </div>
                <input type="button" value='<' onclick="location.href='shoppingPage?pageNumber=${param.pageNumber - 1}'" <c:if test="${param.pageNumber <= 1}">disabled='disabled'</c:if> <c:if test="${requestScope.maxPageNumber < param.pageNumber}">disabled='disabled'</c:if> />
                <input type="button" value='>' onclick="location.href='shoppingPage?pageNumber=${param.pageNumber + 1}'" <c:if test="${param.pageNumber < 1}">disabled='disabled'</c:if><c:if test="${requestScope.maxPageNumber <= param.pageNumber}">disabled='disabled'</c:if> /><br><br>
                <input type="submit" value="Add Product to Cart" />
                <input type="button" value="View Cart" onclick="location.href = 'viewCart'"/>
            </form>
        </div>
    </body>
</html>
