<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:forEach var="utilisateur" items="${ utilisateurs }">
    <tr>
        <td>
            <c:if test="${empty utilisateur.imageProfile}">
                <img class="rounded-circle" style="height: 50px; width: 50px" alt="image utilisateur" src="<c:url value="images/profileDefaut.png"/>" />
            </c:if>
            <c:if test="${not empty utilisateur.imageProfile}">
                <img class="rounded-circle" style="height: 50px; width: 50px" alt="image utilisateur" src="voirImageProfile?id=${utilisateur.id}" />
            </c:if>
        </td>
        <td>${ utilisateur.nom }</td>
        <td>${ utilisateur.prenom }</td>
        <td>${ utilisateur.date_naissance }</td>
        <td>
            <a type="button" class="btn btn-primary btn-sm" role="button" href="profile?id=${utilisateur.id}">Profile</a>
            <c:if test="${ sessionScope.utilisateur.administrateur }">
                <a type="button" class="btn btn-success btn-sm" role="button" href="modifierUtilisateur?id=${utilisateur.id}">Modifier</a>
                <a type="button" class="btn btn-danger btn-sm" role="button" href="supprimerUtilisateur?id=${utilisateur.id}">Supprimer</a>
            </c:if>
        </td>
    </tr>
</c:forEach>
