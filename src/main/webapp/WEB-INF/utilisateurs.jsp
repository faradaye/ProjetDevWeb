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
    <title>Utilisateurs</title>
</head>
<body>
    <h1>Liste des utilisateurs</h1>

    <table>
        <thead>
            <tr>
                <th>Nom</th>
                <th>Prenom</th>
                <th>Date de naissance</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="utilisateur" items="${ utilisateurs }">
                <tr>
                    <td>${ utilisateur.nom }</td>
                    <td>${ utilisateur.prenom }</td>
                    <td>${ utilisateur.date_naissance }</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
