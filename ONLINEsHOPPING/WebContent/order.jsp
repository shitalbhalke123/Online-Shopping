<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:forEach items="${orderlist}" var="order">
<table border="2">


<tr>
<td>order_id: ${ order.order_id}</td>
 <td>OrderDate:${ order.date}</td>
 </tr>
<tr>
<td>Customer name:shital bhalke</td>
 <td>Mobile number:9284220548</td>
 </tr>
 <tr>
 

 
 <tr>
 <td>ProductId</td>
 <td>ProductName</td>
 <td>Price</td>
 <td>Quantity</td>

 </tr>
 <c:forEach items="${ order.addToCartList}"  var="addToCart" > 
 <tr>
 <td>${ addToCart.product_id}</td>
 <td>${ addToCart.order_id}</td>
 <td>${ addToCart.product_id}</td>
 <td>${ addToCart.quantity}</td>

 </tr>
 </c:forEach>   
 
 







</table>
</c:forEach>
</body>
</html>