<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Creer un lieu</title>
</head>
<body>
    <h1>Creer un lieu</h1>

    <p style="color: red;">${erreur}</p>

    <form method="post" action="${pageContext.request.contextPath}/creerLieu">
        <p>Nom:</p>
            <input type="text" name="nom" value= "${lieu.nom}"/><br>
        <p>Adresse:</p>
            <input type="text" name="adresse" value= "${lieu.adresse}"/><br>
        <p>Latitude:</p>
            <input type="number" step="any" name="latitude" value= "${lieu.latitude}"/><br>
        <p>Longitude:</p>
            <input type="number" step="any" name="longitude" value= "${lieu.longitude}"/><br>
        <input type="submit"/>
    </form>
</body>
</html>
