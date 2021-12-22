<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="date" class="java.util.Date"/>
<html>
<head>
    <title>Profile</title>
    <%@ include file="header.jsp"%>
</head>
<body>
    <%@ include file="menu.jsp"%>

    <div class="container-fluid">
        <div class="row my-3">
            <div class="col-2">
                <img src="" class="img-fluid" alt="image profile">
            </div>
            <div class="col-9">
                <h1>Profile de ${utilisateur.login} <c:if test="${utilisateur.administrateur eq true}"><span style="color: #ff0000">(ADMIN)</span></c:if></h1>
            </div>
        </div>

        <div class="row my-3">
            <div class="col">
                <button class="btn btn-primary" onClick="window.history.back()">Retour</button>
                <a type="button" class="btn btn-primary" role="button" href="utilisateurs">Liste des utilisateurs</a>
                <c:if test="${utilisateur.id == sessionScope.utilisateur.id}">
                    <a class="btn btn-success" role="button" href="modifierUtilisateur">Modifier son compte</a>
                </c:if>
                <c:if test="${utilisateur.id != sessionScope.utilisateur.id && sessionScope.utilisateur.administrateur eq true}">
                    <a class="btn btn-success" role="button" href="modifierUtilisateur?id=${utilisateur.id}">Modifier le compte</a><br>
                    <a class="btn btn-danger" id="btn-supUtilisateur" value="${utilisateur.id}" role="button" href="supprimerUtilisateur?id=${utilisateur.id}">Supprimer le compte</a>
                </c:if>
            </div>

        </div>


        <div class="row my-3">
            <div class="col">Profile de ${utilisateur.login} <c:if test="${utilisateur.administrateur eq true}"><span style="color: #ff0000">(ADMIN)</span></c:if></div>
        </div>
        <div class="row my-3">
            <div class="col-2"><u>Prenom :</u></div>
            <div class="col">${utilisateur.prenom}</div>
        </div>
        <div class="row my-3">
            <div class="col-2"><u>Nom :</u></div>
            <div class="col">${utilisateur.nom}</div>
        </div>
        <div class="row my-3">
            <div class="col-2"><u>Date de naissance :</u></div>
            <div class="col"><fmt:formatDate value="${utilisateur.date_naissance}" type="date" pattern="dd/M/yyyy"/></div>
        </div>
        <div class="row my-3">
            <div class="col-2"><u>Email :</u></div>
            <div class="col">${utilisateur.email}</div>
        </div>

    </div>


</body>
</html>
