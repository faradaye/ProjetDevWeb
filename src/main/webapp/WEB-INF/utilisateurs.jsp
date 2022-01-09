<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Utilisateurs</title>
    <%@ include file="header.jsp"%>
    <link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.19.1/dist/bootstrap-table.min.css">
    <script src="https://unpkg.com/bootstrap-table@1.19.1/dist/bootstrap-table.min.js"></script>
    <script src="<c:url value="/js/utilisateurs.js"/>"></script>
</head>
<body>
    <%@ include file="menu.jsp"%>

    <div class="container-fluid">

        <div class="row my-3 justify-content-center">
            <div class="col text-center">
                <h1>Liste des utilisateurs</h1>
            </div>
        </div>
        <div class="table-responsive-sm">
            <table id="table"
                   data-toggle="table"
                   data-pagination="true"
                   data-search="true"
                   class="table table-striped">
                <br>
                <button type="button" class="btn btn-primary" id="refreshListeUtilisateurs">Rafraichir la liste</button>
                <thead>
                    <th scope="col"></th>
                    <th data-sortable="true" data-field="nom" scope="col">Nom</th>
                    <th data-sortable="true" data-field="prenom" scope="col">Prenom</th>
                    <th data-sortable="true" data-field="date_naissance" scope="col">Date de naissance</th>
                    <th scope="col">Actions</th>
                </thead>
                <!--Generation contenu de la table-->
                <tbody id="displayListeUtilisateurs">
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
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
