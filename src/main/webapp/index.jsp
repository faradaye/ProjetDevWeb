<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Accueil</title>
    <%@ include file="WEB-INF/header.jsp"%>
</head>
<body style="display: block">
<%@ include file="WEB-INF/menu.jsp"%>

<section id="homepage">
    <div class="container">
        <div class="row my-1">
            <div class="col">
                <h1>Covid19</h1>
                <p>Covid19 est un site aidant à identifié les personnes atteint du virus SARS-CoV-2, en permettant d'organiser des événements en toute securité</p>
            </div>
            <div class="col img-col">
                <img src="<c:url value="images/cover.jpg"/>" class="img-fluid" alt="Covid19">
            </div>
        </div>
        <div class="row cards my-2">

            <div class="col-md-4 d-flex justify-content-center">
                <div class="card" style="width: 18rem;">
                    <div class="card-body text-center">
                        <a href="amis"><img src="<c:url value="images/friends.png"/>" class="icon" alt="amis image"/></a>
                        <h5 class="card-title">Amis</h5>
                        <p class="card-text">Enregistrer vous entre amis pour vous protéger</p>
                    </div>
                </div>
            </div>

            <div class="col-md-4 d-flex justify-content-center">
                <div class="card" style="width: 18rem;">
                    <div class="card-body text-center">
                        <a href="lieux"> <img src="<c:url value="images/home.png"/>" class="icon" alt="lieux image"/></a>
                        <h5 class="card-title">Lieux</h5>
                        <p class="card-text">Ajouter des lieux pour vos activitées</p>
                    </div>
                </div>
            </div>

            <div class="col-md-4 d-flex justify-content-center">
                <div class="card" style="width: 18rem;">
                    <div class="card-body text-center">
                        <a href="activites"> <img src="<c:url value="images/party.png"/>" class="icon" alt="Activitées image"/></a>
                        <h5 class="card-title">Activités</h5>
                        <p class="card-text">Organiser des activités en toute sécurité</p>
                    </div>
                </div>
            </div>

        </div>
    </div>
</section>

</body>
</html>