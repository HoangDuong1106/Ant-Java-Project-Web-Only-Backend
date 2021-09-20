<%-- 
    Document   : viewCart
    Created on : Jul 11, 2021, 8:30:34 PM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.ACCOUNT.lastname}
        </font>
        <h1>View Cart</h1>

        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Action</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="total" value="${0}"/>
            <form action="deleteItem">
                <c:forEach var="item" items="${sessionScope.CART.items}" varStatus="loop">
                    <tr>
                        <td>${loop.count}</td>
                        <td>${item.key.name}</td>
                        <td>${item.key.price}</td>
                        <td >
                            <c:url var="plusLink" value="plus" >
                                <c:param name="skuPlus" value="${item.key.sku}"/>
                            </c:url>
                            <c:url var="minusLink" value="minus" >
                                <c:param name="skuMinus" value="${item.key.sku}"/>
                            </c:url>
                            <button type="button" onclick="location.href = '${minusLink}'">-</button>
                            <input type="number" name="" value="${item.value}" width="5px" style="text-align: center;"/>
                            <button type="button" onclick="location.href = '${plusLink}'">+</button>
                        </td>
                        <td><input type="checkbox" name="chkItem" value="${item.key.sku}" /></td>
                        <td>${item.value * item.key.price}</td>
                        <c:set var="total" value="${total + item.value * item.key.price}" />
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="4" style="text-align: center;">
                        <c:url var="ViewProductLink" value="shoppingPage" >
                            <c:param name="pageNumber" value="1" />
                            <c:param name="numInPage" value="4" />
                        </c:url>
                        <a href="${ViewProductLink}">Add More Item</a>
                    </td>
                    <td>
                        <input type="submit" value="Delete" />
                    </td>
                    <c:forEach var="item" items="${sessionScope.CART.items}" varStatus="loop"></c:forEach>
                    <td style="color: green;" >${total}</td>
                </tr>
            </form>
            <c:url var="checkoutLink" value="checkout" >
                <c:param name="total" value="${total}" />
            </c:url>
            <input type="button" value="Checkout" onClick="location.href = '${checkoutLink}'" />
        </tbody>
    </table>



</body>
</html>
