<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="users" scope="request" type="java.util.List<mate.academy.internetshop.model.User>"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Users</title>
    <style>
        button {
            width: 100px;
        }
    </style>
</head>
<body>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Login</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>
                <c:out value="${user.id}"/>
            </td>
            <td>
                <c:out value="${user.login}"/>
            </td>
            <td>
                <c:out value="${user.name}"/>
            </td>
            <td>
                <c:out value="${user.surname}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/servlet/deleteUser?user_id=${user.id}"
                   style="color: darkred" method="post"
                >DELETE</a>
            </td>
        </tr>
    </c:forEach>
</table>
<form action="${pageContext.request.contextPath}/registration">
    <button type="submit" style="
    color: limegreen;
    margin-left: 5px;
    margin-top: 5px;
    width: 76px;
    height: 30px;
    border: cadetblue;
    font-size:14px;
    font-weight:700;">Add User
    </button>
</form>
<button onclick="location.href='${pageContext.request.contextPath}/servlet/Menu'" type="button">
    back to menu
</button>
</body>
</html>
