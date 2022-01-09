<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <title>Activités</title>
    <%@ include file="header.jsp"%>
    <link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.19.1/dist/bootstrap-table.min.css">
    <script src="https://unpkg.com/bootstrap-table@1.19.1/dist/bootstrap-table.min.js"></script>
    <script src="<c:url value="/js/activites.js"/>"></script>
</head>
<body>
<%@ include file="menu.jsp"%>

<div class="container-fluid">

    <div class="row my-3 justify-content-center">
        <div class="col text-center">
            <h1>Liste des activités</h1>
        </div>
    </div>

    <div class="table-responsive-sm">
        <table id="table"
               data-toggle="table"
               data-pagination="true"
               data-search="true"
               class="table table-striped">
            <br>
            <a type="button" class="btn btn-primary" role="button" href="creerActivite">Ajouter une activité</a>
            <button type="button" class="btn btn-primary" id="refreshListeActivite">Rafraichir la liste</button>
            <thead>
                <th scope="col">Nom</th>
                <th scope="col">Lieu</th>
                <th scope="col">Date</th>
                <th scope="col">Heure de début</th>
                <th scope="col">Heure de fin</th>
                <th scope="col">Actions</th>
            </thead>
            <!--Generation contenu de la table-->
            <tbody id="displayListeActivites">
            <c:forEach var="activite" items="${ activites }">
                <tr>
                    <td>${ activite.nom }</td>
                    <td>${ activite.lieu.nom }</td>
                    <td>${ activite.date_activite }</td>
                    <td>${ activite.heure_debut }</td>
                    <td>${ activite.heure_fin }</td>
                    <td>
                        <!-- Si l'utilisateur participe à l'activite -->
                        <c:if test="${ not fn:contains(activite.participants, sessionScope.utilisateur.id) }">
                            <a type="button" class="btn btn-primary btn-sm" role="button" href="ajouterParticipation?id=${activite.id}">Participer à l'activité</a>
                        </c:if>
                        <c:if test="${ fn:contains(activite.participants, sessionScope.utilisateur.id) }">
                            <button class="btn btn-success btn-sm" role="button">Vous participez déjà à l'activité</button>
                        </c:if>
                        <a type="button" class="btn btn-primary btn-sm" role="button" href="activite?id=${activite.id}">Details</a>
                        <c:if test="${ sessionScope.utilisateur.administrateur}">
                            <a type="button" class="btn btn-success btn-sm" role="button" href="modifierActivite?id=${activite.id}">Modifier</a>
                            <a type="button" class="btn btn-danger btn-sm" role="button" href="supprimerActivite?id=${activite.id}">Supprimer</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
