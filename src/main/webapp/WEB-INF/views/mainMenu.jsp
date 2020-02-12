<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Menu</title>
    <style>
        <%@include file="/WEB-INF/css/menuCss.css" %>
    </style>
</head>
<body>
<nav>
    <menu>
        <menuitem id="demo1">
            <a>Menu</a>
            <menu>
                <menuitem id="demo2">
                    <a>Account</a>
                    <menu>
                        <menuitem><a href="${pageContext.request.contextPath}/registration">Create new User</a>
                        </menuitem>
                        <menuitem><a href="${pageContext.request.contextPath}/logout">Logout</a></menuitem>
                    </menu>
                </menuitem>
                <menuitem id="demo2">
                    <a>Purchase</a>
                    <menu>
                        <menuitem><a href="${pageContext.request.contextPath}/servlet/getAllItems">All Items</a>
                        </menuitem>
                        <menuitem><a href="${pageContext.request.contextPath}/servlet/showBucket">Bucket</a></menuitem>
                        <menuitem><a href="${pageContext.request.contextPath}/servlet/allOrders">Orders</a></menuitem>
                    </menu>
                </menuitem>
            </menu>
        </menuitem>
    </menu>
</nav>
</body>
</html>
