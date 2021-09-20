<%-- 
    Document   : search
    Created on : July 7, 2021, 12:47:02 PM
    Author     : admin
--%>

<%--<%@page import="duongmh.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.ACCOUNT.lastname}
        </font>
        <form action="searchResult">
            Search Value <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" />
            <input type="submit" value="Search" name="btAction" />
        </form><br/>

        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}" >
            <c:set var="result" value="${requestScope.SEARCH_VALUE}" />
            <c:set var="errors" value="${requestScope.INSERT_ERRORS}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Lastname</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                            <th>Edit</th>
                                <c:if test="${not empty errors.passwordLengthError}">
                                <th>Nortification</th>
                                </c:if>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="update">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" value="${dto.username}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" 
                                           <c:if test="${not empty errors.passwordLengthError and dto.username==param.txtUsername}" >
                                               value="${param.txtPassword}" 
                                           </c:if>
                                           <c:if test="${empty errors.passwordLengthError or dto.username!=param.txtUsername}">
                                               value="${dto.password}"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    ${dto.lastname}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="${dto.username}" 
                                           <c:if test="${dto.role}" >
                                               checked="checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:if test="${sessionScope.ACCOUNT.role}" >
                                        <c:url var="deleteLink" value="delete">
                                            <c:param name="username" value="${dto.username}" />
                                            <c:param name="lastSearchValue" value="${searchValue}" />
                                        </c:url>
                                        <a href="${deleteLink}">Delete</a>
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${sessionScope.ACCOUNT.role}" >
                                        <input type="submit" value="Update" name="btAction" />
                                        <input type="hidden" name="txtSearchValue" value="${searchValue}" />
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${sessionScope.ACCOUNT.role or sessionScope.ACCOUNT.username eq dto.username}" >
                                        <c:url var="editLink" value="editPage">
                                            <c:param name="username" value="${dto.username}" />
                                            <c:param name="password" value="${dto.password}" />
                                            <c:param name="lastname" value="${dto.lastname}" />
                                            <c:if test="${dto.role}" >
                                                <c:param name="role" value="true" />
                                            </c:if>
                                            <c:param name="txtSearchValue" value="${searchValue}" />
                                        </c:url>
                                        <a href="${editLink}">Edit</a>
                                    </c:if>
                                </td>

                                <c:if test="${not empty errors.passwordLengthError and dto.username==param.txtUsername}" >
                                    <td>    
                                        <font color="red">
                                        ${errors.passwordLengthError}
                                        </font>
                                    </td>
                                </c:if>

                            </tr>
                        </form>

                    </c:forEach>
                </tbody>
            </table>

        </c:if>
        <c:if test="${empty result}">
            <h2>
                No Record 
            </h2>
        </c:if>
    </c:if>
    <a href="shoppingPage?numInPage=4&pageNumber=1">Shopping Now!!!</a>
</body>
</html>
