<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:forEach var="lieu" items="${ lieux }">
    <tr>
        <td>${ lieu.nom }</td>
        <td>${ lieu.adresse }</td>
        <td>${ lieu.latitude }</td>
        <td>${ lieu.longitude }</td>
        <td>
            <a type="button" class="btn btn-primary btn-sm" role="button" href="lieu?id=${lieu.id}">Details</a>
            <c:if test="${ sessionScope.utilisateur.administrateur}">
                <a type="button" class="btn btn-success btn-sm" role="button" href="modifierLieu?id=${lieu.id}">Modifier</a>
                <a type="button" class="btn btn-danger btn-sm" role="button" href="supprimerLieu?id=${lieu.id}">Supprimer</a>
            </c:if>
        </td>
    </tr>
</c:forEach>
