<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="eng">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<link rel="stylesheet"
		href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
		integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
		crossorigin="anonymous">
	<link rel="stylesheet" href="css/login.css">

<title>Online  magazine-shop</title>
</head>

<body>
    <jsp:include page="header.jsp"></jsp:include>

    <div class="alert alert-success alert-dismissible fade show" role="alert">
			<b>Great!</b> You successfully registered in magazine-shop!
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
	</div>

    <div class="login-page">
        <div class="form">
            <form class="register-form">
                <input class="firstName" type="text" placeholder="First name" />
                <input class="lastName" type="text" placeholder="Last name" />
                <input class="email" type="text" placeholder="Login" />
                <input class="password" type="password" placeholder="Password" />
                <input class="confirmPassword" type="password" placeholder="Confirm password " />
                <label><input id="user" name="accessLevel" type="radio" value="user" checked>User</label>
        		<label><input id="admin" name="accessLevel" type="radio" value="admin">Administrator</label>
                <button class="register" type="button">Create account</button>
                <p class="message">Already registered? <a href="#">Sign in</a></p>
            </form>
            <form class="login-form">
                <input class="email" type="text" placeholder="Login" />
                <input class="password" type="password" placeholder="Password" />
                <button class="login" type="button">Sign in</button>
                <p class="message">Not registered? <a href="#">Create an account</a></p>
            </form>
        </div>
    </div>

    <jsp:include page="footer.jsp"></jsp:include>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="js/login.js"></script>
</body>

</html>