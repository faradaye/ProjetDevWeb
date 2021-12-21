<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Utilisateurs</title>
</head>
<body>
    <h1>Liste des utilisateurs</h1>

    <table>
        <thead>
            <tr>
                <th>Nom</th>
                <th>Prenom</th>
                <th>Date de naissance</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="utilisateur" items="${ utilisateurs }">
                <tr>
                    <td>${ utilisateur.nom }</td>
                    <td>${ utilisateur.prenom }</td>
                    <td>${ utilisateur.date_naissance }</td>
                    <td><a href="profile?id=${utilisateur.id}">Profile utilisateur</a> <c:if test="${ sessionScope.utilisateur.administrateur}"><a href="modifierUtilisateur?id=${utilisateur.id}">Modifier utilisateur</a> <a href="supprimerUtilisateur?id=${utilisateur.id}">Supprimer utilisateur</a></c:if></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
