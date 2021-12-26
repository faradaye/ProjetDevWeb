<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Créer une activité</title>
    <%@ include file="header.jsp"%>
    <script>
        $(document).ready(function() {
            $("#displayErreurCreerActivite").hide();

            if($("#displayErreurCreerActivite").html()!='')
                $("#displayErreurCreerActivite").show();

        });

        function verifyTimes(){
            var debut = document.forms["form"].elements["heuredebut"].value;
            var fin = document.forms["form"].elements["heurefin"].value;
            var date = document.forms["form"].elements["date"].value;
            var dateDebut = new Date(date + " " + debut);
            var dateFin = new Date(date + " " + fin);

            if(dateDebut < dateFin){
                return true;
            }else{
                alert("Les horaires ne sont pas bons.");
                return false;
            }
        }
    </script>
</head>
<body>
<%@ include file="menu.jsp"%>

<div class="container-fluid">
    <div class="row my-3">
        <div class="col-2">
        </div>
        <div class="col-9">
            <h1>Créer une activité</h1>
        </div>
    </div>

    <button type="button" class="btn btn-primary" onClick="window.history.back()">Retour</button>
    <a type="button" class="btn btn-primary" role="button" href="activites">Liste des activités</a>
    <!--Nouveau formulaire-->
    <form method="post" class="my-3" id="form" onsubmit="return verifyTimes()" action="${pageContext.request.contextPath}/creerActivite">
        <div class="alert alert-danger" role="alert" id="displayErreurCreerActivite">${erreur}</div>
        <div class="row my-3">
            <div class="col-12">
                <label for="nom" class="form-label">Nom</label>
                <input type="text" class="form-control" id="nom" name="nom" placeholder="Ex: Apéritif" value="${activite.nom}" required>
            </div>
        </div>
        <div class="row my-3">
            <div class="col-12">
                <label for="lieu" class="form-label">Lieu</label>
                <select class="form-control" id="lieu" name="lieu" required>
                    <c:forEach var="lieu" items="${ lieux }">
                        <option value="${lieu.id}">${lieu.nom}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="row my-3">
            <div class="col-6">
                <label for="date" class="form-label">Date</label>
                <input type="date" class="form-control" id="date" name="date" placeholder="Ex: 50.5" value="${activite.date_activite}" required>
            </div>
            <div class="col-6">
                <label for="heuredebut" class="form-label">Heure de début</label>
                <input type="time" step="any" class="form-control" id="heuredebut" name="heuredebut" placeholder="Ex: 80.1" value="${activite.heure_debut}" required>
            </div>
            <div class="col-6">
                <label for="heurefin" class="form-label">Heure de fin</label>
                <input type="time" step="any" class="form-control" id="heurefin" name="heurefin" placeholder="Ex: 80.1" value="${activite.heure_fin}" required>
            </div>
        </div>
        <div class="row my-3">
            <div class="col-12">
                <button type="submit" class="btn btn-primary" name="submit">Créer</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
