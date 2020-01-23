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
                        <menuitem><a href="/internet_shop_war_exploded/registration">Create new User</a></menuitem>
                        <menuitem><a href="/internet_shop_war_exploded/logout">Logout</a></menuitem>
                    </menu>
                </menuitem>
                <menuitem id="demo2">
                    <a>Purchase</a>
                    <menu>
                        <menuitem><a href="/internet_shop_war_exploded/servlet/getAllItems">All Items</a></menuitem>
                        <menuitem><a href="/internet_shop_war_exploded/servlet/showBucket">Bucket</a></menuitem>
                        <menuitem><a href="/internet_shop_war_exploded/servlet/allOrders">Orders</a></menuitem>
                    </menu>
                </menuitem>
            </menu>
        </menuitem>
    </menu>
</nav>
</body>
</html>
