<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Inscription</title>
    <%@ include file="header.jsp"%>
</head>
<body>
    <%@ include file="menu.jsp"%>
    <h1>Inscription</h1>

    <p style="color: red;">${erreur}</p>

    <form method="post" action="${pageContext.request.contextPath}/inscription">
        <p>Login:</p>
            <input type="text" name="login" value= "${utilisateur.login}"/><br>
        <p>Password:</p>
            <input type="password" name="password" value= "${utilisateur.password}"/><br>
        <p>Nom:</p>
            <input type="text" name="nom" value= "${utilisateur.nom}"/><br>
        <p>Prénom:</p>
            <input type="text" name="prenom" value= "${utilisateur.prenom}"/><br>
        <p>Date de naissance:</p>
            <input type="date" name="date_naissance" value= "${utilisateur.date_naissance}"/><br>
        <input type="submit"/>
    </form>
</body>
</html>
