<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lieux</title>
    <%@ include file="header.jsp"%>
</head>
<body>
    <%@ include file="menu.jsp"%>
    <h1>Liste des lieux</h1>

    <a href="creerLieu">Ajouter un lieu</a>

    <table>
        <thead>
            <tr>
                <th>Nom</th>
                <th>adresse</th>
                <th>latitude</th>
                <th>longitude</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="lieu" items="${ lieux }">
                <tr>
                    <td>${ lieu.nom }</td>
                    <td>${ lieu.adresse }</td>
                    <td>${ lieu.latitude }</td>
                    <td>${ lieu.longitude }</td>
                    <td><a href="lieu?id=${lieu.id}">Voir lieu</a> <c:if test="${ sessionScope.utilisateur.administrateur}"><a href="modifierLieu?id=${lieu.id}">Modifier lieu</a> <a href="supprimerLieu?id=${lieu.id}">Supprimer lieu</a></c:if></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
