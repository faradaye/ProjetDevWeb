<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Amis</title>
    <%@ include file="header.jsp"%>
    <link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.19.1/dist/bootstrap-table.min.css">
    <script src="https://unpkg.com/bootstrap-table@1.19.1/dist/bootstrap-table.min.js"></script>
    <script src="<c:url value="/js/amis.js"/>"></script>
</head>
<body>
<%@ include file="menu.jsp"%>

<div class="container-fluid">

    <div class="row my-3 justify-content-center">
        <div class="col text-center">
            <h1>Liste de vos amis</h1>
        </div>
    </div>

    <div class="table-responsive-sm">
        <table id="table"
               data-toggle="table"
               data-pagination="true"
               data-search="true"
               class="table table-striped">

            <br>
            <button type="button" class="btn btn-primary" id="refreshListeAmis">Rafraichir la liste</button>
            <thead>
            <th scope="col"></th>
            <th data-sortable="true" data-field="nom" scope="col">Nom</th>
            <th data-sortable="true" data-field="prenom" scope="col">Prenom</th>
            <th scope="col">Actions</th>
            </thead>
            <!--Generation contenu de la table-->
            <tbody id="displayListeAmis">
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
            </tbody>
        </table>
    </div>

    <div class="row my-3 justify-content-center">
        <div class="col text-center">
            <h1>Envoyer une demande d'amis</h1>
        </div>
    </div>

    <form method="post" action="${pageContext.request.contextPath}/amis">
        <h3>Nom d'utilisateur de l'ami:</h3>
        <input type="text" name="loginami"/><br>

        <input class="btn btn-primary" type="submit"/>
    </form>
</div>
</body>
</html>
