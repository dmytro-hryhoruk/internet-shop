<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Menu</title>
    <style>
        <%@include file="/WEB-INF/css/menuCss.css" %>
    </style>
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
                        <menuitem><a href="${pageContext.request.contextPath}/registration">Create new User</a>
                        </menuitem>
                        <menuitem><a href="${pageContext.request.contextPath}/servlet/getAllUsers">All Users</a>
                        </menuitem>
                    </menu>
                </menuitem>
                <menuitem id="demo2">
                    <a>Items</a>
                    <menu>
                        <menuitem><a href="${pageContext.request.contextPath}/servlet/addItem">Add Item</a></menuitem>
                        <menuitem><a href="${pageContext.request.contextPath}/servlet/getAllItems">All Items</a>
                        </menuitem>
                    </menu>
                </menuitem>
                <menuitem>
                    <a href="${pageContext.request.contextPath}/logout">Logout</a>
                </menuitem>

            </menu>
        </menuitem>
    </menu>
</nav>
</body>
</html>
