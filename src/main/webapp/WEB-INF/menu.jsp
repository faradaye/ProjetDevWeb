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
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownUtilisateurs" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Utilisateurs
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="amis">Amis</a></li>
                            <li><a class="dropdown-item" href="utilisateurs">Liste des utilisateurs</a></li>
                        </ul>
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
                            Activités
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="creerActivite">Créer une activité</a></li>
                            <li><a class="dropdown-item" href="activites">Liste des activités</a></li>
                        </ul>
                    </li>
                </c:if>
                </ul>
                    <ul class="navbar-nav ms-auto">
                    <c:if test="${empty sessionScope.utilisateur}">
                        <li class="nav-item" style="margin: auto;">
                            <div class="form-check form-switch" >
                                <input class="form-check-input" type="checkbox" id="darkMode">
                                <label class="form-check-label" for="darkMode">Dark Mode</label>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="authentification">Se connecter</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="inscription">S'inscrire</a>
                        </li>
                    </c:if>
                    <c:if test="${not empty sessionScope.utilisateur}">
                        <li class="nav-item" style="margin: auto;">
                            <div class="form-check form-switch" style="margin-right: 20px">
                                <input class="form-check-input" type="checkbox" id="darkMode">
                                <label class="form-check-label" for="darkMode">Dark Mode</label>
                            </div>
                        </li>
                        <li class="nav-item">
                            <div class="row">
                                <div class="col-4">
                                    <div class="profile-header-container">
                                        <div class="profile-header-img">
                                            <c:if test="${empty sessionScope.utilisateur.imageProfile}">
                                                <img class="rounded-circle" style="height: 50px; width: 50px" alt="image profile" src="<c:url value="images/profileDefaut.png"/>" />
                                            </c:if>
                                            <c:if test="${not empty sessionScope.utilisateur.imageProfile}">
                                                <img class="rounded-circle" style="height: 50px; width: 50px" alt="image profile" src="voirImageProfile?id=${sessionScope.utilisateur.id}" />
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-8">
                                    <span class="navbar-brand mb-0 h2" href="#">${sessionScope.utilisateur.login}</span>
                                </div>
                            </div>
                        </li>

                        <li class="nav-item">
                            <button  type="button" class=" btn btn-primary icon position-relative" id="bell">
                                <img src="https://i.imgur.com/AC7dgLA.png" alt="">
                                <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                                <c:out value = "${ fn:length(sessionScope.notificationsNonLues) }"/>
                                <span class="visually-hidden">notifications non lu</span>
                            </span>
                            </button>
                            <div class="notifications" id="box">
                                <h2>Notifications - <span> <c:out value = "${ fn:length(sessionScope.notificationsNonLues) }"/> </span> <a type="button" href="notifications">Tout voir</a> </h2>
                                <c:forEach var="notif" items="${ sessionScope.notifications }">
                                    <c:if test="${not notif.lue}">
                                        <div class="notifications-item">
                                            <c:if test="${empty notif.image_source}">
                                                <img alt="image utilisateur" src="<c:url value="images/profileDefaut.png"/>" />
                                            </c:if>
                                            <c:if test="${not empty notif.image_source}">
                                                <img alt="image utilisateur" src="voirImageProfile?id=${notif.id_source}" />
                                            </c:if>
                                            <div class="text">
                                                <h4>${ notif.prenom_nom_source }</h4>
                                                <p>${ notif.contenu }</p>
                                                <c:if test="${notif.type_notif == 1}">
                                                    <a type="button" onclick="window.location.reload()" href="accepterDemande?id=${notif.id}">Accepter</a>
                                                    <a type="button" onclick="window.location.reload()" href="refuserDemande?id=${notif.id}">Refuser</a>
                                                </c:if>
                                                <c:if test="${notif.type_notif != 1}">
                                                    <a type="button" onclick="window.location.reload()" href="notificationLue?id=${notif.id}">Marquer comme lue</a>
                                                </c:if>
                                            </div>
                                        </div>
                                    </c:if>
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

<script>
    let darkModeOn = false;

    const createCookie = (name, value, days) => {
        let expires;
        if (days) {
            let date = new Date();
            date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
            expires = "expires=" + date.toGMTString();
        } else {
            expires = "";
        }
        document.cookie = name + "=" + value + "; SameSite=Lax;"+ expires + "; path=/";
    }

    const readCookie = name => {
        let nameEQ = name + "=";
        let ca = document.cookie.split(";");
        for(let i=0; i < ca.length; i++) {
            let c = ca[i];
            while (c.charAt(0) === " ") {
                c = c.substring(1, c.length);
            }
            if (c.indexOf(nameEQ) === 0) {
                return c.substring(nameEQ.length,c.length);
            }
        }
        return null;
    }

    const deleteCookie = name => {
        createCookie(name, "", -1);
    }

    const toggleDarkMode = (e) => {
        if (document.body.classList.contains("dark-mode")) {
            document.body.classList.remove("dark-mode");
            darkModeOn = false;
            createCookie("my_preferredMode", "light-mode", 365);
        } else {
            document.body.classList.add("dark-mode");
            darkModeOn = true;
            createCookie("my_preferredMode", "dark-mode", 365);
        }
    }

    const getPreferredMode = () => {
        if (readCookie("my_preferredMode")) {
            return readCookie("my_preferredMode");
        } else {
            return "not-set";
        }
    }

    document.getElementById("darkMode").addEventListener("click", toggleDarkMode)

    document.addEventListener("DOMContentLoaded", () => {
        if (readCookie("my_preferredMode")) {
            if (readCookie("my_preferredMode") == "dark-mode") {
                darkModeOn = true;
            } else {
                darkModeOn = false;
            }
        } else {
            if (window.matchMedia && window.matchMedia("(prefers-color-scheme: dark)").matches) {
                darkModeOn = true;
            } else {
                if (document.body.classList.contains("dark-mode")) {
                    darkModeOn = true;
                } else {
                    darkModeOn = false;
                }
            }
        }

        if (darkModeOn) {
            if (!document.body.classList.contains("dark-mode")) {
                document.body.classList.add("dark-mode");
            }
            document.getElementById("darkMode").checked = true
        } else {
            if (document.body.classList.contains("dark-mode")) {
                document.body.classList.remove("dark-mode");
            }
        }
    })
</script>
