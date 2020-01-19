<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style>
        .note {
            text-align: center;
            height: 80px;
            background: -webkit-linear-gradient(left, #0072ff, #8811c5);
            color: #fff;
            font-weight: bold;
            line-height: 80px;
        }

        .form-content {
            padding: 5%;
            border: 1px solid #ced4da;
            margin-bottom: 2%;
        }

        .form-control {
            border-radius: 1.5rem;
        }

        .btnSubmit {
            border: none;
            border-radius: 1.5rem;
            padding: 1%;
            width: 20%;
            cursor: pointer;
            background: #0062cc;
            color: #fff;
        }
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
