<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style>
        body {
            font-size: 13px;
        }

        .login-blue {
            background: linear-gradient(145deg, #2196F3, #3F51B5);
        }
    </style>
</head>
<body>
<div>${errorMsg}</div>
<div class="row m-4">
    <div class="col-4">
        <div class="login-blue p-3 shadow-lg rounded">
            <div class="pt-3">
                <h2 class="text-white ">Sign In </h2>
            </div>

            <form class="mt-5" action="/internet_shop_war_exploded/login" method="post">
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
                        <a href="/internet_shop_war_exploded/registration">Sign up</a>
                    </p>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
