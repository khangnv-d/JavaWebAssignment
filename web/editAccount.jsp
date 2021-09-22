

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="common.Config"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <body>
        <form action="UpdateAccountDispatcher">
            Username: ${param.txtUsername} 
            </br>
            <input name="txtUsername" type="hidden" value="${param.txtUsername}"/> 
            
            Password:  <input name="txtPassword" type="text" 
                              value="${param.txtPassword}"/>  
            <font color="red">${UERROR.incorrectPassword}</font>
            </br>
            
            Full Name: <input name="txtFullname" type="text" 
                              value="${param.txtFullname}"/>
            <font color="red">${UERROR.fullNameEmpty}</font>
            </br>
            
            Role: <input type="checkbox" name="checkAdmin" value="ON" 
                 <c:if  test="${not empty param.checkAdmin}">
                     checked="checked"
                 </c:if>
                 />         
            </br>   
            <input name="lastSearchValue" type="hidden" value="${param.lastSearchValue}"/> 
            <input type="submit" name="btAction" value="Update">   
        </form>
    </body>
</html>

