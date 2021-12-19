<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Authentification</title>
</head>
<body>
    <h1>Authentification</h1>

    <p style="color: red;">${erreur}</p>

    <form method="post" action="${pageContext.request.contextPath}/authentification">
        <p>Login:</p>
            <input type="text" name="login" value= "${utilisateur.login}"/><br>
        <p>Password:</p>
            <input type="password" name="password" value= "${utilisateur.password}"/><br>

        <input type="submit"/>
    </form>
</body>
</html>
