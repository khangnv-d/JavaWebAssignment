

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Map"%>
<%@page import="khangnv.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Shopping</title>
    </head>
    <body>
        <h1>Your Cart Include</h1>

        <c:set var="cart" value= "${sessionScope.CART}" />
        <c:if test="${not empty cart.items}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Total</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                <form action="removeItem">          
                    <c:forEach var="dto" items="${cart.items.keySet()}" varStatus="counter">
                        <tr>
                            <td> 
                                ${counter.count}
                            </td>                                
                            <td>  
                                ${dto.name}
                            </td>                                  
                            <td>  
                                ${dto.price}
                            </td>                                  
                            <td>  
                                ${cart.items.get(dto)}
                            </td>                                  
                            <td>  
                                ${dto.price* cart.items.get(dto)}
                            </td>                                                          
                            <td>
                                <input type="checkbox" name="checkItem" 
                                       value="${dto.ID}" />  
                            </td>
                        </tr>  
                    </c:forEach>
                    <tr>
                        <td colspan="5">
                            <button><a style="text-decoration: none" href="onlineShopping.jsp">
                                    Add more Items to Cart</a></button>

                        </td>
                        <td>
                            <input type="submit" value="Remove" name="btAction" /> 

                        </td>
                    </tr>

                </form>

            </tbody>
        </table>   
        <form action="CheckOut">        
            <button>Check Out</button>
        </form>

    </c:if>
    <c:if test="${empty cart.items}">
        <h2>
            No cart is existed!!!<br/>
            <a href="onlineShopping.jsp">Come Back To Store Online</a>
        </h2>
    </c:if>
</body>
</html>

