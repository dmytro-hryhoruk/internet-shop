<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Menu</title>
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
    </style>
</head>
<body>
<h1>Main Menu</h1>
<button onclick="location.href='/internet_shop_war_exploded/registration'" type="button">
    Register
</button>
<br>
<button onclick="location.href='/internet_shop_war_exploded/servlet/addItem'" type="button">
    Add Item
</button>
<br>
<button onclick="location.href='/internet_shop_war_exploded/servlet/getAllItems'" type="button">
    Show all Items
</button>
<br>
<button onclick="location.href='/internet_shop_war_exploded/servlet/getAllUsers'" type="button">
    Show all Users
</button>
<br>
<button onclick="location.href='/internet_shop_war_exploded/servlet/showBucket'" type="button">
    Show bucket
</button>
<br>
<button onclick="location.href='/internet_shop_war_exploded/servlet/allOrders'" type="button">
    Show all orders
</button>
</body>
</html>
