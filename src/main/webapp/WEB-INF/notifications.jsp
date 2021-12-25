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
                <h1>Liste de toutes vos notifications</h1>
            </div>
        </div>

        <table class="table table-striped">

            <br>
            <thead>
                <th scope="col">Contenu</th>
            </thead>
            <!--Generation contenu de la table-->
            <tbody id="displayListeNotificationsPerso">
            <c:forEach var="notification" items="${ notificationsUtilisateur }">
                <tr>
                    <td>${ notification.contenu }</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
