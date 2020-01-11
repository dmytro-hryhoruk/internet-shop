<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add-Item</title>
</head>
<body>
<form action="/internet_shop_war_exploded/servlet/addItem" method="post">
    <div class="container">
        <h1>Item Adding</h1>
        <label for="ItemName"><b><h2>Item Name</h2></b></label>
        <input type="text" name="ItemName" required>
        <br>
        <label for="ItemPrice"><b><h2>Item Price</h2></b></label>
        <input type="text" name="ItemPrice" required>
        <br>
        <button type="submit">Add</button>
    </div>
</form>
<br>
<button onclick="location.href='/internet_shop_war_exploded/servlet/mainMenu'" type="button">
    back to menu
</button>
</body>
</html>
