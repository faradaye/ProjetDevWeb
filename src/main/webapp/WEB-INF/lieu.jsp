<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lieu</title>
    <%@ include file="header.jsp"%>
</head>
<body>
    <%@ include file="menu.jsp"%>

    <div class="container-fluid">
        <div class="row my-3">
            <div class="col-2">
            </div>
            <div class="col-9">
                <h1>Description du lieu ${lieu.nom}</h1>
            </div>
        </div>

        <div class="row my-3">
            <div class="col">
                <button class="btn btn-primary" onClick="window.history.back()">Retour</button>
                <a type="button" class="btn btn-primary" role="button" href="lieux">Liste des lieux</a>
                <c:if test="${sessionScope.utilisateur.administrateur eq true}">
                    <a class="btn btn-success" role="button" href="modifierLieu?id=${lieu.id}">Modifier le lieu</a><br>
                    <a class="btn btn-danger" id="btn-supLieu" value="${lieu.id}" role="button" href="supprimerLieu?id=${lieu.id}">Supprimer le lieu</a>
                </c:if>
            </div>

        </div>


        <div class="row my-3">
            <div class="col">Lieu : ${lieu.nom}</div>
        </div>
        <div class="row my-3">
            <div class="col-2"><u>Adresse :</u></div>
            <div class="col">${lieu.adresse}</div>
        </div>
        <div class="row my-3">
            <div class="col-2"><u>Latitude :</u></div>
            <div class="col">${lieu.latitude}</div>
        </div>
        <div class="row my-3">
            <div class="col-2"><u>Longitude :</u></div>
            <div class="col">${lieu.longitude}</div>
        </div>

    </div>


</body>
</html>
