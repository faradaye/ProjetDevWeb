<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:forEach var="ami" items="${ amis }">
    <tr>
        <td>
            <c:if test="${empty ami.imageProfile}">
                <img class="rounded-circle" style="height: 50px; width: 50px" alt="image utilisateur" src="<c:url value="images/profileDefaut.png"/>" />
            </c:if>
            <c:if test="${not empty ami.imageProfile}">
                <img class="rounded-circle" style="height: 50px; width: 50px" alt="image utilisateur" src="voirImageProfile?id=${ami.id}" />
            </c:if>
        </td>
        <td>${ ami.nom }</td>
        <td>${ ami.prenom }</td>
        <td>
            <a type="button" class="btn btn-primary btn-sm" role="button" href="profile?id=${ami.id}">Profile</a>
            <a type="button" class="btn btn-danger btn-sm" role="button" href="supprimerAmi?id=${ami.id}">Supprimer</a>
        </td>
    </tr>
</c:forEach>
