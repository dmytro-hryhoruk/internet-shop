<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="items" scope="request" type="java.util.List<mate.academy.internetshop.model.Item>"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Items</title>
    <style>
        td {
            text-align: center;
        }

        button {
            width: 100px;
        }
    </style>
</head>
<body>
<table border="1">
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Add to Bucket</th>
        <th>Delete Item</th>
    </tr>
    <c:forEach var="item" items="${items}">
        <tr>
            <td>
                <c:out value="${item.name}"/>
            </td>
            <td>
                <c:out value="${item.price}"/>
            </td>
            <td>
                <a href="/internet_shop_war_exploded/servlet/addItemToBucket?item_id=${item.id}"
                   style="color: limegreen;
                   text-align: center ;"
                >ADD</a>
            </td>
            <td>
                <a href="/internet_shop_war_exploded/servlet/deleteItem?item_id=${item.id}"
                   style="color: limegreen;
                   text-align: center ;"
                >DELETE</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br><br>
<button onclick="location.href='/internet_shop_war_exploded/servlet/showBucket'" type="button">
    Go To Bucket
</button>
<br><br>
<button onclick="location.href='/internet_shop_war_exploded/servlet/Menu'" type="button">
    back to menu
</button>
</body>
</html>
