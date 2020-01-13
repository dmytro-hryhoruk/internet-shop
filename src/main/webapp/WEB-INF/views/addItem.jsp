<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add-Item</title>
    <style>
        button {
            width: 100px;
        }

    </style>
</head>
<body>
<form action="${pageContext.request.contextPath}/servlet/addItem" method="post">
    <div class="container">
        <h1>Item Adding</h1>
        <label for="ItemName"><b>Item Name</b></label>
        <input type="text" name="ItemName" required>
        <br><br>
        <label for="ItemPrice"><b>Item Price</b></label>
        <input type="text" name="ItemPrice" required>
        <br><br>
        <button type="submit">Add</button>
    </div>
</form>
<button onclick="location.href='${pageContext.request.contextPath}/servlet/getAllItems'" type="button">
    All Items
</button>
<br><br>
<button onclick="location.href='${pageContext.request.contextPath}/servlet/mainMenu'" type="button">
    back to menu
</button>
</body>
</html>
