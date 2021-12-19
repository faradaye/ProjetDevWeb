<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Accueil</title>
</head>
<body>

<c:if test="${empty sessionScope.utilisateur}">
    <a href="authentification">Authentification</a>
    <a href="inscription">Inscription</a>
</c:if>
<c:if test="${not empty sessionScope.utilisateur}">
    <a href="deconnexion">Deconnexion</a><br><br>

    <p>Bonjour ${sessionScope.utilisateur.login}</p><br>
    <a href="utilisateurs">Utilisateurs</a>
</c:if>

</body>
</html>
