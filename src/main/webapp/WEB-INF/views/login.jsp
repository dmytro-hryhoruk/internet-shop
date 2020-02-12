<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style>
        <%@include file="/WEB-INF/css/registrationCss.css" %>
    </style>
</head>
<body>
<div class="row m-4">
    <div class="col-4">
        <div class="login-blue p-3 shadow-lg rounded">
            <div class="pt-3">
                <h2 class="text-white ">Sign In </h2>
            </div>

            <form class="mt-5" action="${pageContext.request.contextPath}/login" method="post">
                <div style="color: red">${errorMsg}</div>
                <div class="form-group">
                    <input type="text" name="login"
                           class="form-control form-control-sm bg-light"
                           placeholder="Login">
                </div>

                <div class="form-group">
                    <input type="password" name="psw"
                           class="form-control form-control-sm bg-light"
                           placeholder="Password">
                </div>

                <div class="mt-5">
                    <button type="submit" class="btn btn-sm btn-light col">
                        Login
                    </button>
                </div>

                <div class="mt-5">
                    <p class="text-white text-center">
                        Don't have an account?
                        <a href="${pageContext.request.contextPath}/registration">Sign up</a>
                    </p>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
