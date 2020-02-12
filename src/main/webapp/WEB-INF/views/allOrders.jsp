<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="orders" scope="request" type="java.util.List<mate.academy.internetshop.model.Order>"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
    <style>
        button {
            width: 100px;
        }
    </style>
</head>
<body>
<h1>ORDERS</h1>
<table border="1">
    <tr>
        <th>Order ID</th>
        <th>Items</th>
        <th>DELETE ORDER</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>
                <c:out value="${order.id}"/>
            </td>
            <td>
                <c:forEach var="item" items="${order.items}">
                    <c:out value="${item}"/>
                </c:forEach>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/servlet/deleteOrderFromOrders?orderId=${order.id}"
                   style="color: darkred" method="post"
                >DELETE</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<button onclick="location.href='${pageContext.request.contextPath}/servlet/Menu'" type="button">
    back to menu
</button>
</body>
</html>
