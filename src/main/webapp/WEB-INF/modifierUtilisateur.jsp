<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <c:if test="${ sessionScope.utilisateur.id == idUtilisateur}">
        <title>Modifier son compte</title>
    </c:if>
    <c:if test="${ sessionScope.utilisateur.id != idUtilisateur}">
        <title>Modifier un compte</title>
    </c:if>
</head>
<body>
    <c:if test="${ sessionScope.utilisateur.id == idUtilisateur}">
        <h1>Modifier son compte</h1>
    </c:if>
    <c:if test="${ sessionScope.utilisateur.id != idUtilisateur}">
        <h1>Modifier un compte</h1>
    </c:if>

    <p style="color: red;">${erreur}</p>

    <form method="post" action="${pageContext.request.contextPath}/modifierUtilisateur">
        <input type="hidden" name="idUtilisateur" value="${idUtilisateur}" />
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
        <c:if test="${sessionScope.utilisateur.administrateur}">
            <input type="checkbox" value="1" id="administrateur" name="administrateur"
                   <c:if test="${utilisateur.administrateur eq true}">checked</c:if>>
            <label for="administrateur">Administrateur</label><br>
        </c:if>
        <input type="submit"/>
    </form>
</body>
</html>
