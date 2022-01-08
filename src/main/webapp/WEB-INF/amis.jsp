<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Amis</title>
    <%@ include file="header.jsp"%>
</head>
<body>
<%@ include file="menu.jsp"%>

<div class="container-fluid">

    <div class="row my-3 justify-content-center">
        <div class="col text-center">
            <h1>Liste de vos amis</h1>
        </div>
    </div>

    <table class="table table-striped">

        <br>
        <button type="button" class="btn btn-primary" id="refreshListeUtilisateurs">Rafraichir la liste</button>
        <thead>
        <th scope="col">Nom</th>
        <th scope="col">Prenom</th>
        <th scope="col">Actions</th>
        </thead>
        <!--Generation contenu de la table-->
        <tbody id="displayListeUtilisateurs">
        <c:forEach var="ami" items="${ amis }">
            <tr>
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
