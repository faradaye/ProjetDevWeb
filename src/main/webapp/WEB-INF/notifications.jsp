<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Notifications</title>
    <%@ include file="header.jsp"%>
</head>
<body>
    <%@ include file="menu.jsp"%>

    <div class="container-fluid">

        <div class="row my-3 justify-content-center">
            <div class="col text-center">
                <h1>Liste des notifications de l'utilisateur connecté</h1>
            </div>
        </div>

        <table class="table table-striped">

            <br>
            <button type="button" class="btn btn-primary" id="refreshListeNotificationsPerso">Rafraichir la liste</button>
            <thead>
                <th scope="col">Type de notif</th>
                <th scope="col">Contenu</th>
            </thead>
            <!--Generation contenu de la table-->
            <tbody id="displayListeNotificationsPerso">
            <c:forEach var="notification" items="${ notificationsUtilisateur }">
                <tr>
                    <td>${ notification.type_notif }</td>
                    <td>${ notification.contenu }</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="container-fluid">

        <div class="row my-3 justify-content-center">
            <div class="col text-center">
                <h1>Liste des notifications de tous les utilisateurs</h1>
            </div>
        </div>

        <table class="table table-striped">

            <br>
            <button type="button" class="btn btn-primary" id="refreshListeNotificationsTous">Rafraichir la liste</button>
            <thead>
            <th scope="col">Type de notif</th>
            <th scope="col">Contenu</th>
            </thead>
            <!--Generation contenu de la table-->
            <tbody id="displayListeNotificationsTous">
            <c:forEach var="notification" items="${ notifications }">
                <tr>
                    <td>${ notification.type_notif }</td>
                    <td>${ notification.contenu }</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
