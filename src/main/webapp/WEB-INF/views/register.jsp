<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Registration</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style>
        <%@include file="/WEB-INF/css/registrationCss.css" %>
    </style>
</head>
<body>
<form class="form-horizontal" action="${pageContext.request.contextPath}/registration" method="post">
    <div class="container register-form">
        <div class="form">
            <div class="note">
                <p>CREATE NEW USER</p>
            </div>

            <div class="form-content">
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Your Login *" name="login"/>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Password *" name="psw"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Your Name *" name="user_name"/>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Your Surname *" name="user_surname"/>
                        </div>
                    </div>
                </div>
                <button type="submit" class="btnSubmit">Submit</button>
                <p>Already have an account? <a href="/internet_shop_war_exploded/login">Sign in</a></p>
            </div>
        </div>
    </div>
</form>
</body>
</html>
