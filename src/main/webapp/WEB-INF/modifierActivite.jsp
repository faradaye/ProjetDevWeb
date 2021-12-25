<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Modifier une activité</title>
    <%@ include file="header.jsp"%>
    <script>
        $(document).ready(function() {
            $("#displayErreurModifierLieu").hide();

            if($("#displayErreurModifierLieu").html()!='')
                $("#displayErreurModifierLieu").show();

        });
    </script>
</head>
<body>
<%@ include file="menu.jsp"%>

<div class="container-fluid">
    <div class="row my-3">
        <div class="col-2">
        </div>
        <div class="col-9">
            <h1>Modifier l'activité ${activite.nom}</h1>
        </div>
    </div>

    <button type="button" class="btn btn-primary" onClick="window.history.back()">Retour</button>
    <a type="button" class="btn btn-primary" role="button" href="lieux">Liste des lieux</a>
    <!--Nouveau formulaire-->
    <form method="post" class="my-3" action="${pageContext.request.contextPath}/modifierActivite">
        <div class="alert alert-danger" role="alert" id="displayErreurModifierLieu">${erreur}</div>
        <input type="hidden" name="idActivite" value="${idActivite}" />
        <div class="row my-3">
            <div class="col-12">
                <label for="nom" class="form-label">Nom</label>
                <input type="text" class="form-control" id="nom" name="nom" placeholder="Ex: Apéritif" value="${activite.nom}" required>
            </div>
        </div>
        <div class="row my-3">
            <div class="col-12">
                <label for="id_lieu" class="form-label">Lieu</label>
                <select class="form-control" id="id_lieu" name="id_lieu" required>
                    <c:forEach var="lieu" items="${ lieux }">
                        <c:if test="${lieu.id == activite.lieu.id}">
                            <option value="${lieu.id}" selected>${lieu.nom}</option>
                        </c:if>
                        <c:if test="${lieu.id != activite.lieu.id}">
                            <option value="${lieu.id}">${lieu.nom}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="row my-3">
            <div class="col-6">
                <label for="date" class="form-label">Date</label>
                <input type="date" class="form-control" id="date" name="date" value="${activite.date_activite}" required>
            </div>
            <div class="col-6">
                <label for="heure_debut" class="form-label">Heure de début</label>
                <input type="time" step="any" class="form-control" id="heure_debut" name="heure_debut" value="${activite.heure_debut}" required>
            </div>
            <div class="col-6">
                <label for="heure_fin" class="form-label">Heure de fin</label>
                <input type="time" step="any" class="form-control" id="heure_fin" name="heure_fin" value="${activite.heure_fin}" required>
            </div>
        </div>
        <div class="row my-3">
            <div class="col-12">
                <button type="submit" class="btn btn-primary" name="submit">Mettre à jour</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
