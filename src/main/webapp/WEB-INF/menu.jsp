<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary bg-gradient">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1" href="#">Covid19</span>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 flex-fill">
                <c:if test="${not empty sessionScope.utilisateur}">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="<c:url value="/" />">Accueil</a>
                    </li>
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
                <div class="d-flex ms-auto">
                    <c:if test="${empty sessionScope.utilisateur}">
                        <li class="d-flex nav-item">
                            <a class="nav-link active" aria-current="page" href="authentification">Se connecter</a>
                        </li>
                        <li class="d-flex nav-item">
                            <a class="nav-link active" aria-current="page" href="inscription">S'inscrire</a>
                        </li>
                    </c:if>
                    <c:if test="${not empty sessionScope.utilisateur}">
                        <span class="navbar-brand mb-0 h2" href="#">${sessionScope.utilisateur.login}</span>
                        <div class="icon" id="bell"> <img src="https://i.imgur.com/AC7dgLA.png" alt=""> </div>
                        <div class="notifications" id="box">
                            <h2>Notifications - <span>2</span></h2>
                            <div class="notifications-item"> <img src="https://i.imgur.com/uIgDDDd.jpg" alt="img">
                                <div class="text">
                                    <h4>Samso aliao</h4>
                                    <p>Samso Nagaro a été testé positif</p>
                                </div>
                            </div>
                            <div class="notifications-item"> <img src="https://img.icons8.com/flat_round/64/000000/vote-badge.png" alt="img">
                                <div class="text">
                                    <h4>John Silvester</h4>
                                    <p>John Silvester lance une nouvelle activitée</p>
                                </div>
                            </div>
                        </div>
                        <li class="d-flex nav-item">
                            <a class="nav-link active" aria-current="page" href="profile">Mon compte</a>
                        </li>
                        <li class="d-flex nav-item">
                            <a class="nav-link active" aria-current="page" href="#" onclick="Deconnexion()">Se deconnecter</a>
                        </li>
                    </c:if>
                </div>
            </ul>
        </div>
    </div>
</nav>
