<%--
  Created by IntelliJ IDEA.
  User: Thomas
  Date: 19/12/2021
  Time: 00:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Notifications</title>
</head>
<body>
<h1>Liste des notifications de l'utilisateur connecté:</h1>

<table>
    <thead>
    <tr>
        <th>Type de notif</th>
        <th>Contenu</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="notification" items="${ notificationsUtilisateur }">
        <tr>
            <td>${ notification.type_notif }</td>
            <td>${ notification.contenu }</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<h1>Liste des notifications de tous les utilisateurs:</h1>
<table>
    <thead>
    <tr>
        <th>Type de notif</th>
        <th>Contenu</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="notification" items="${ notifications }">
        <tr>
            <td>${ notification.type_notif }</td>
            <td>${ notification.contenu }</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
