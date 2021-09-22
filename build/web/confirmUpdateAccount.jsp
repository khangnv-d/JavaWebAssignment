
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Update</title>
    </head>
    <body>
        
        <c:url var="updateAccountUrl" value="Update">
            <c:param name="txtUsername" value="${param.txtUsername}"></c:param>
            <c:param name="txtPassword" value="${param.txtPassword}"></c:param>
            <c:param name="txtFullname" value="${param.txtFullname}"></c:param>
            <c:param name="checkAdmin" value="${param.checkAdmin}"></c:param>
            <c:param name="lastSearchValue" value="${param.lastSearchValue}"></c:param>
        </c:url>
        <c:url var="cancelUpdateUrl" value="EditAccount">
            <c:param name="txtUsername" value="${param.txtUsername}"></c:param>
            <c:param name="txtPassword" value="${param.txtPassword}"></c:param>
            <c:param name="txtFullname" value="${param.txtFullname}"></c:param>
            <c:param name="checkAdmin" value="${param.checkAdmin}"></c:param>
            <c:param name="lastSearchValue" value="${param.lastSearchValue}"></c:param>
        </c:url>
        <h1>Confirm update!</h1>
        <a href="${updateAccountUrl}">Confirm</a>
        <a href="${cancelUpdateUrl}">Cancel</a>

    </body>
</html>
