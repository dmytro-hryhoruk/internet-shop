<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Menu</title>
    <style><%@include file="/WEB-INF/css/menuCss.css"%></style>
</head>
<body>
<nav>
    <menu>
        <menuitem id="demo1">
            <a>Admin Menu</a>
            <menu>
                <menuitem id="demo2">
                    <a>Users</a>
                    <menu>
                        <menuitem><a href="/internet_shop_war_exploded/registration">Create new User</a></menuitem>
                        <menuitem><a href="/internet_shop_war_exploded/servlet/getAllUsers">All Users</a></menuitem>
                    </menu>
                </menuitem>
                <menuitem id="demo2">
                    <a>Items</a>
                    <menu>
                        <menuitem><a href="/internet_shop_war_exploded/servlet/addItem">Add Item</a></menuitem>
                        <menuitem><a href="/internet_shop_war_exploded/servlet/getAllItems">All Items</a></menuitem>
                    </menu>
                </menuitem>
                <menuitem>
                    <a href="/internet_shop_war_exploded/logout">Logout</a>
                </menuitem>

            </menu>
        </menuitem>
    </menu>
</nav>
</body>
</html>
