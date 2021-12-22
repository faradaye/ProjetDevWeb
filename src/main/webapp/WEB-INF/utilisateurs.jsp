<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Utilisateurs</title>
    <%@ include file="header.jsp"%>
</head>
<body>
    <%@ include file="menu.jsp"%>

    <div class="container-fluid">

        <div class="row my-3 justify-content-center">
            <div class="col text-center">
                <h1>Liste des utilisateurs</h1>
            </div>
        </div>

        <table class="table table-striped">

            <br>
            <button type="button" class="btn btn-primary" id="refreshListeUtilisateurs">Raffraichir la liste</button>
            <thead>
                <th scope="col">Nom</th>
                <th scope="col">Prenom</th>
                <th scope="col">Date de naissance</th>
                <th scope="col">Actions</th>
            </thead>
            <!--Generation contenu de la table-->
            <tbody id="displayListeUtilisateurs">
            <c:forEach var="utilisateur" items="${ utilisateurs }">
                <tr>
                    <td>${ utilisateur.nom }</td>
                    <td>${ utilisateur.prenom }</td>
                    <td>${ utilisateur.date_naissance }</td>
                    <td>
                        <a type="button" class="btn btn-primary btn-sm" role="button" href="profile?id=${utilisateur.id}">Profile</a>
                        <c:if test="${ sessionScope.utilisateur.administrateur}">
                            <a type="button" class="btn btn-success btn-sm" role="button" href="modifierUtilisateur?id=${utilisateur.id}">Modifier</a>
                            <a type="button" class="btn btn-danger btn-sm" role="button" href="supprimerUtilisateur?id=${utilisateur.id}">Supprimer</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
