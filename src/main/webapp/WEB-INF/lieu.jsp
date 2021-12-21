<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lieu</title>
</head>
<body>
    <h1>Lieu</h1>
    <c:if test="${sessionScope.utilisateur.administrateur eq true}">
        <a href="modifierLieu?id=${lieu.id}">modifier le lieu</a><br>
        <a href="supprimerLieu?id=${lieu.id}">supprimer le lieu</a>
    </c:if>
    <p>Nom: ${lieu.nom}</p>
    <p>Adresse: ${lieu.adresse}</p>
    <p>Latitude: ${lieu.latitude}</p>
    <p>Longitude: ${lieu.longitude}</p>


</body>
</html>
