<jsp:useBean id="errMsg" scope="request" type="java.lang.String"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Data error</title>
</head>
<body>
<h1>An error has occurred while trying to process your data'(</h1>
<h3>${errMsg}</h3><br>
<h3><a href="${pageContext.request.contextPath}/servlet/Menu">Return to Main Page</a></h3>
</body>
</html>
