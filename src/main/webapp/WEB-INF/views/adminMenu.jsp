<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Menu</title>
    <link href="button.css" rel="stylesheet" type="text/css">
    <style>
        button {
            border: 1px solid #3261C3;
            border-radius: 5px;
            background: #3775DD;
            background: -moz-linear-gradient(top, #3775DD 0, #3455C1 100%);
            background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #3775DD), color-stop(100%, #3455C1));
            background: -webkit-linear-gradient(top, #3775DD 0, #3455C1 100%);
            background: -o-linear-gradient(top, #3775DD 0, #3455C1 100%);
            background: linear-gradient(to bottom, #3775DD 0, #3455C1 100%);
            -webkit-box-shadow: 0 2px 6px -2px rgba(0, 0, 0, .7), inset 0 1px 2px 0 rgba(255, 255, 255, 0.5);
            box-shadow: 0 2px 6px -2px rgba(0, 0, 0, .7), inset 0 1px 2px 0 rgba(255, 255, 255, 0.5);
            margin-bottom: 5px;
            height: 32px;
            width: 150px;
            color: white;
            font-size: 1em;
            text-align-all: match-parent;
            transition: all 0.3s;
        }

        button:hover {
            box-shadow: 0 -5px 10px #3455C1 inset;
            color: #96bcfc;
        }

        html, body {
            padding: 0px;
            margin: 0px;
            background: #191A1D;
            font-family: 'Karla', sans-serif;
            width: 100vw;
        }

        body * {
            margin: 0;
            padding: 0;
        }

        /* HTML Nav Styles */
        /* HTML Nav Styles */
        /* HTML Nav Styles */
        nav menuitem {
            position: relative;
            display: block;
            opacity: 0;
            cursor: pointer;
        }

        nav menuitem > menu {
            position: absolute;
            pointer-events: none;
        }

        nav > menu {
            display: flex;
        }

        nav > menu > menuitem {
            pointer-events: all;
            opacity: 1;
        }

        menu menuitem a {
            white-space: nowrap;
            display: block;
        }

        menuitem:hover > menu {
            pointer-events: initial;
        }

        menuitem:hover > menu > menuitem,
        menu:hover > menuitem {
            opacity: 1;
        }

        nav > menu > menuitem menuitem menu {
            transform: translateX(100%);
            top: 0;
            right: 0;
        }

        /* User Styles Below Not Required */
        /* User Styles Below Not Required */
        /* User Styles Below Not Required */

        nav {
            margin-top: 40px;
            margin-left: 40px;
        }

        nav a {
            background: #75F;
            color: #FFF;
            min-width: 190px;
            transition: background 0.5s, color 0.5s, transform 0.5s;
            margin: 0px 6px 6px 0px;
            padding: 20px 40px;
            box-sizing: border-box;
            border-radius: 3px;
            box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.5);
            position: relative;
        }

        nav a:hover:before {
            content: '';
            top: 0;
            left: 0;
            position: absolute;
            background: rgba(0, 0, 0, 0.2);
            width: 100%;
            height: 100%;
        }

        nav > menu > menuitem > a + menu:after {
            content: '';
            position: absolute;
            border: 10px solid transparent;
            border-top: 10px solid white;
            left: 12px;
            top: -40px;
        }

        nav menuitem > menu > menuitem > a + menu:after {
            content: '';
            position: absolute;
            border: 10px solid transparent;
            border-left: 10px solid white;
            top: 20px;
            left: -180px;
            transition: opacity 0.6, transform 0s;
        }

        nav > menu > menuitem > menu > menuitem {
            transition: transform 0.6s, opacity 0.6s;
            transform: translateY(150%);
            opacity: 0;
        }

        nav > menu > menuitem:hover > menu > menuitem,
        nav > menu > menuitem.hover > menu > menuitem {
            transform: translateY(0%);
            opacity: 1;
        }

        menuitem > menu > menuitem > menu > menuitem {
            transition: transform 0.6s, opacity 0.6s;
            transform: translateX(195px) translateY(0%);
            opacity: 0;
        }

        menuitem > menu > menuitem:hover > menu > menuitem,
        menuitem > menu > menuitem.hover > menu > menuitem {
            transform: translateX(0) translateY(0%);
            opacity: 1;
        }
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
