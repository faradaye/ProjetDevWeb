<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Modifier un lieu</title>
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
                <h1>Modifier le lieu ${lieu.nom}</h1>
            </div>
        </div>

        <button type="button" class="btn btn-primary" onClick="window.history.back()">Retour</button>
        <a type="button" class="btn btn-primary" role="button" href="lieux">Liste des lieux</a>
        <!--Nouveau formulaire-->
        <form method="post" class="my-3" action="${pageContext.request.contextPath}/modifierLieu">
            <div class="alert alert-danger" role="alert" id="displayErreurModifierLieu">${erreur}</div>
            <input type="hidden" name="idLieu" value="${idLieu}" />
            <div class="row my-3">
                <div class="col-12">
                    <label for="nom" class="form-label">Nom</label>
                    <input type="text" class="form-control" id="nom" name="nom" placeholder="Ex: DuPont" value="${lieu.nom}" required>
                </div>
            </div>
            <div class="row my-3">
                <div class="col-12">
                    <label for="adresse" class="form-label">Adresse</label>
                    <input type="text" class="form-control" id="adresse" name="adresse" placeholder="Ex: Campus, Bd des Aiguillettes, 54506 Vandœuvre-lès-Nancy" value="${lieu.adresse}" required>
                </div>
            </div>
            <div class="row my-3">
                <div class="col-6">
                    <label for="latitude" class="form-label">Latitude</label>
                    <input type="number" step="any" class="form-control" id="latitude" name="latitude" placeholder="Ex: 50.5" value="${lieu.latitude}" required>
                </div>
                <div class="col-6">
                    <label for="longitude" class="form-label">Longitude</label>
                    <input type="number" step="any" class="form-control" id="longitude" name="longitude" placeholder="Ex: 80.1" value="${lieu.longitude}" required>
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
