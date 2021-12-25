<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
            <h1>Description de l'activité ${activite.nom}</h1>
        </div>
    </div>

    <div class="row my-3">
        <div class="col">
            <button class="btn btn-primary" onClick="window.history.back()">Retour</button>
            <a type="button" class="btn btn-primary" role="button" href="activites">Liste des activités</a>
            <c:if test="${ not fn:contains(activite.participants, sessionScope.utilisateur.id) }">
                <a type="button" class="btn btn-primary" role="button" href="ajouterParticipation?id=${activite.id}">Participer à l'activité</a>
            </c:if>
            <c:if test="${ fn:contains(activite.participants, sessionScope.utilisateur.id) }">
                <button class="btn btn-success" role="button">Vous participez déjà à l'activité</button>
            </c:if>
            <c:if test="${sessionScope.utilisateur.administrateur}">
                <a class="btn btn-success" role="button" href="modifierActivite?id=${activite.id}">Modifier l'activité</a><br>
                <a class="btn btn-danger" id="btn-supLieu" value="${activite.id}" role="button" href="supprimerActivite?id=${activite.id}">Supprimer l'activité</a>
            </c:if>
        </div>

    </div>


    <div class="row my-3">
        <div class="col">Activité: ${activite.nom}</div>
    </div>
    <div class="row my-3">
        <div class="col-2"><u>Adresse:</u></div>
        <div class="col">${activite.lieu.adresse}</div>
    </div>
    <div class="row my-3">
        <div class="col-2"><u>Date:</u></div>
        <div class="col">${activite.date_activite}</div>
    </div>
    <div class="row my-3">
        <div class="col-2"><u>Heure de début:</u></div>
        <div class="col">${activite.heure_debut}</div>
    </div>
    <div class="row my-3">
        <div class="col-2"><u>Heure de fin:</u></div>
        <div class="col">${activite.heure_fin}</div>
    </div>
</div>
</body>
</html>
