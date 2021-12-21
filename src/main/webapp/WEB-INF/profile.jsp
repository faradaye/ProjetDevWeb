<%--
  Created by IntelliJ IDEA.
  User: Thomas
  Date: 21/12/2021
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
    <h1>Profile</h1>
    <c:if test="${utilisateur.id == sessionScope.utilisateur.id}">
        <a href="modifierUtilisateur">modifier son compte</a>
    </c:if>
    <c:if test="${utilisateur.id != sessionScope.utilisateur.id && sessionScope.utilisateur.administrateur eq true}">
        <a href="modifierUtilisateur?id=${utilisateur.id}">modifier le compte</a><br>
        <a href="supprimerUtilisateur?id=${utilisateur.id}">supprimer le compte</a>
    </c:if>
    <p>Prenom: ${utilisateur.prenom}</p>
    <p>Nom: ${utilisateur.nom}</p>
    <p>Date de naissance: ${utilisateur.date_naissance}</p>
    <c:if test="${utilisateur.administrateur eq true}"><p style="color: #ff0000">ADMIN</p></c:if>


</body>
</html>
