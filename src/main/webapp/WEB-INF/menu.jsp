<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary bg-gradient">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1" href="#">Covid19</span>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="<c:url value="/" />">Accueil</a>
                    </li>
                <c:if test="${not empty sessionScope.utilisateur}">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="utilisateurs">Utilisateurs</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownLieux" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Lieux
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="creerLieu">Créer un lieu</a></li>
                            <li><a class="dropdown-item" href="lieux">Liste des lieux</a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownActivitees" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Activitées
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="creerActivitee">Créer une activitée</a></li>
                            <li><a class="dropdown-item" href="activitees">Liste des activitées</a></li>
                        </ul>
                    </li>
                </c:if>
                </ul>
                    <ul class="navbar-nav ms-auto">
                    <c:if test="${empty sessionScope.utilisateur}">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="authentification">Se connecter</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="inscription">S'inscrire</a>
                        </li>
                    </c:if>
                    <c:if test="${not empty sessionScope.utilisateur}">
                        <li class="nav-item">
                            <span class="navbar-brand mb-0 h2" href="#">${sessionScope.utilisateur.login}</span>
                        </li>

                        <li class="nav-item">
                            <button  type="button" class=" btn btn-primary icon position-relative" id="bell">
                                <img src="https://i.imgur.com/AC7dgLA.png" alt="">
                                <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                                <c:out value = "${ fn:length(sessionScope.notifications) }"/>
                                <span class="visually-hidden">notifications non lu</span>
                            </span>
                            </button>
                            <div class="notifications" id="box">
                                <h2>Notifications - <span> <c:out value = "${ fn:length(sessionScope.notifications) }"/> </span></h2>
                                <c:forEach var="notif" items="${ sessionScope.notifications }">
                                <div class="notifications-item"> <img src="https://i.imgur.com/uIgDDDd.jpg" alt="img">
                                    <div class="text">
                                        <h4>${ notif.prenom_nom_source }</h4>
                                        <p>${ notif.contenu }</p>
                                        <c:if test="${notif.type_notif == 1}">
                                            <a type="button" onclick="window.location.reload()" href="accepterDemande?id=${notif.id}">Accepter</a>
                                            <a type="button" onclick="window.location.reload()" href="refuserDemande?id=${notif.id}">Refuser</a>
                                        </c:if>
                                    </div>
                                </div>
                                </c:forEach>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="profile">Mon compte</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="#" onclick="Deconnexion()">Se deconnecter</a>
                        </li>
                    </c:if>
                    </ul>
        </div>
    </div>
</nav>
