<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="items" scope="request" type="java.util.List<mate.academy.internetshop.model.Item>"/>
<jsp:useBean id="bucket" scope="request" type="mate.academy.internetshop.model.Bucket"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bucket</title>
    <style>
        button {
            width: 100px;
        }
    </style>
</head>
<body>
<h1>ITEMS IN YOUR BUCKET:</h1>
<table border="1">
    <tr>
        <th>Item name</th>
        <th>Item Price</th>
        <th>DELETE ITEM</th>
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
                <a href="/internet_shop_war_exploded/servlet/deleteItemFromBucket?item_id=${item.id}"
                   style="color: darkred;
                   background-color: lightcoral" ;
                >DELETE</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<button onclick="location.href='/internet_shop_war_exploded/servlet/checkout'" type="button">
    COMPLETE ORDER
</button>
<br><br>
<button onclick="location.href='/internet_shop_war_exploded/servlet/getAllItems'" type="button">
    All Items
</button>
<br><br>
<button onclick="location.href='/internet_shop_war_exploded/servlet/Menu'" type="button">
    back to menu
</button>
</body>
</html>

