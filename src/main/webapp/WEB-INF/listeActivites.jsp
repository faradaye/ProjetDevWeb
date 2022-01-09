<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
