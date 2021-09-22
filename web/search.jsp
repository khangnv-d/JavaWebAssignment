
<%--<%@page import="khangnv.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search </title>
    </head>
    <body>

        <font color="red"> 
        Welcome, ${sessionScope.FULLNAME} 
        </font> 

        <h1>Search Page</h1><br/>

        <form action="search">
            Search Value <input type="text" name="txtSearchValue" 
                                value="${ param.txtSearchValue }" />

            <input type="submit" value="Search" name="btAction" />
        </form>   

        <form action="logOut">       
            <input type="submit" value="Log Out" name="btAction" />
        </form>   <br/>

        <c:set var="searchValue" value="${param.txtSearchValue}"/>

        <c:if test="${not empty searchValue}">

            <!--            get searchValue tu user-->
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>  
            <c:if test="${not empty result}">

                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full Name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                            <th>Edit</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="UpdateTableAccountDispatcher">
                            <tr>
                                <td>
                                    ${counter.count}
                                    .</td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" 
                                           value="${dto.username}" />

                                </td>
                                <td>
                                    <input type="text" name="txtPassword" 
                                           value="${dto.password}" />
                                    <c:if test="${UERROR_INDEX eq dto.username}">
                                        <br/> <font color="red"> ${UERROR.incorrectPassword} </font 
                                    </c:if>
                                </td>
                                <td>
                                    ${dto.lastname}
                                    <input type="hidden" name="txtFullname" 
                                           value="${dto.lastname}" />
                                </td>
                                <td>
                                    <input type="checkbox" name="checkAdmin" value="ON" 
                                           <c:if  test="${dto.role}">
                                               checked="checked"
                                           </c:if>
                                           />                                   
                                </td>
                                <td>
                                    <c:url var="deleteLink" value="delete">                                      
                                        <c:param name="username" value="${dto.username}"/>
                                        <c:param name="lastSearchValue" value="${searchValue}"/>
                                    </c:url> 
                                    <a href="${deleteLink}">Delete</a>
                                </td> 
                                <td>
                                    <input type="submit" value="Update" name="btAction" />
                                    <input type="hidden" name="lastSearchValue" 
                                           value="${searchValue}" />
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${dto.role}">
                                            <c:url var="editAccountUrl" value="EditAccount">
                                                <c:param name="txtUsername" value="${dto.username}"></c:param>
                                                <c:param name="txtPassword" value="${dto.password}"></c:param>
                                                <c:param name="txtFullname" value="${dto.lastname}"></c:param>
                                                <c:param name="checkAdmin" value="${dto.role}"></c:param>
                                                <c:param name="lastSearchValue" value="${searchValue}"></c:param>
                                            </c:url>
                                        </c:when>
                                        <c:otherwise>
                                            <c:url var="editAccountUrl" value="EditAccount">
                                                <c:param name="txtUsername" value="${dto.username}"></c:param>
                                                <c:param name="txtPassword" value="${dto.password}"></c:param>
                                                <c:param name="txtFullname" value="${dto.lastname}"></c:param>
                                                <c:param name="lastSearchValue" value="${searchValue}"></c:param>
                                            </c:url>
                                        </c:otherwise>
                                    </c:choose>

                                    <a href="${editAccountUrl}">
                                        Edit
                                    </a>
                                </td>
                            </tr>
                        </form>    
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
        <c:if test="${empty result}"> 
            <h2>
                No record is existed!!!
            </h2>
        </c:if>
        <c:if test="${empty searchValue}"> 
            <h2>
                Search Value is Empty !!!
            </h2>
        </c:if>
    </c:if>

</body>
</html>
