
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Online</title>
    </head>
    <body>
        <h1>Shopping Online </h1>

        <c:set var="result" value="${sessionScope.STORE}"/>  
        <c:if test="${not empty result}"> 
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>                           
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${result}" varStatus="counter">
                    <form action="addItem">
                        <tr>   
                            <td>
                                ${counter.count}
                            .</td>
                            <td>
                                ${dto.ID} 
                                 <input type="hidden" name="txtProducID" 
                                           value="${dto.ID}" />
                            </td>
                            <td>
                                ${dto.name} 
                                <input type="hidden" name="txtProductname" 
                                           value="${dto.name}" />
                            </td>
                            <td>
                                ${dto.price} 
                                <input type="hidden" name="txtProductprice" 
                                           value="${dto.price}" />
                            </td>
                            <td>
                                <input type="submit" value="Add Item To Your Cart" name="btAction" />
                            </td>                                
                        </tr>
                    </form>    
                </c:forEach>
            </tbody>
        </table>
        <button> <a style="text-decoration: none" href="loginPage">Login Page</a> </button>
        
<!--        <a href="viewCart">View Your Cart</a>-->
        <button> <a style="text-decoration: none" href="viewCart"> View your cart </a> </button>
    </c:if>
    <c:if test="${empty result}"> 
        <h2>
            Emtpy table in Database!!
        </h2>
    </c:if>
        
</body>
</html>
