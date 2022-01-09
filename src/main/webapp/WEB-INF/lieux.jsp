<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lieux</title>
    <%@ include file="header.jsp"%>
    <link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.19.1/dist/bootstrap-table.min.css">
    <script src="https://unpkg.com/bootstrap-table@1.19.1/dist/bootstrap-table.min.js"></script>
    <script src="<c:url value="/js/lieux.js"/>"></script>
</head>
<body>
    <%@ include file="menu.jsp"%>

    <div class="container-fluid">

        <div class="row my-3 justify-content-center">
            <div class="col text-center">
                <h1>Liste des lieux</h1>
            </div>
        </div>

        <div class="table-responsive-sm">
            <table id="table"
                   data-toggle="table"
                   data-pagination="true"
                   data-search="true"
                   class="table table-striped">

                <br>
                <a type="button" class="btn btn-primary" role="button" href="creerLieu">Ajouter un lieu</a>
                <button type="button" class="btn btn-primary" id="refreshListeLieux">Raffraichir la liste</button>
                <thead>
                    <th scope="col">Nom</th>
                    <th scope="col">adresse</th>
                    <th scope="col">latitude</th>
                    <th scope="col">longitude</th>
                    <th scope="col">Actions</th>
                </thead>
                <!--Generation contenu de la table-->
                <tbody id="displayListeLieux">
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
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
