
<html>

<head>
    <meta charset="UTF-8">
    <title>Форма регистрации</title>
</head>

<body>
    <jsp:include page="header.jsp"></jsp:include>

    <h1>Register form</h1>
    <form action="registering" method="post">
        <input name="firstName" type="text" placeholder="Name"><br><br>
        <input name="lastName" type="text" placeholder="Last name"><br><br>
        <input name="email" type="text" placeholder="Email"><br><br>
        <input name="password" type="password" placeholder="Password"><br><br>
        <input name="accessLevel" type="radio" id="user" value="user" checked>
        <label for="user">user</label><br>
        <input name="accessLevel" type="radio" id="admin" value="admin">
        <label for="admin">admin</label><br><br>
        <input type="submit" value="submit">
    </form>

    <jsp:include page="footer.jsp"></jsp:include>
</body>

</html>