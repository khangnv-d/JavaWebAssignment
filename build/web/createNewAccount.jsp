

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Account</title>
    </head>
    <body>
        <h1>Create Account</h1>
        <form action="createAccount">
            <c:set var="errors" value="${requestScope.INSERT_ERRORS}" />
            Username* <input type="text" name="txtUsername" 
                             value="${param.txtUsername}" /> (6 - 20 chars) <br/>
            <c:if test="${not empty errors.usernameLengthErr}">
                <font color="red">
                ${errors.usernameLengthErr}
                </font>
            </c:if>

            <c:if test="${not empty errors.usernameIsExisted}">
                <font color="red">
                ${errors.usernameIsExisted}
                </font>
            </c:if>
            <br/>    
            Password* <input type="password" name="txtPassword" value="" /> 
            (6 - 30 chars) <br/>
            <c:if test="${not empty errors.passwordLengthErr}">
                <font color="red">
                ${errors.passwordLengthErr}
                </font>
            </c:if>
            <br/>    
            Confirm* <input type="password" name="txtConfirm" value="" />  <br/>
            <c:if test="${not empty errors.confirmNotMatch}">
                <font color="red">
                ${errors.confirmNotMatch}
                </font>
            </c:if>

            Full Name* <input type="text" name="txtFullname" 
                              value="${param.txtFullname}" /> (2 - 50 chars) <br/>
            <c:if test="${not empty errors.fullNameLengthErr}">
                <font color="red">
                ${errors.fullNameLengthErr}
                </font>
            </c:if>
            <br/>  

            <input type="submit" value="Create New Account" name="btAction" />
            <input type="reset" value="Reset" />


        </form>
        <form action="loginPage">        
            <button>Login Page</button>
        </form>

    </body>
</html>
