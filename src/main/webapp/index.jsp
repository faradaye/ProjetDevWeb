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
            <div class="row cards">

                <div class="col-md-4 d-flex justify-content-center">
                    <div class="card" style="width: 18rem;">
                        <div class="card-body text-center">
                            <img src="<c:url value="images/friends.png"/>" class="icon" alt="amis image"/>
                            <h5 class="card-title">Amis</h5>
                            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                        </div>
                    </div>
                </div>

                <div class="col-md-4 d-flex justify-content-center">
                    <div class="card" style="width: 18rem;">
                        <div class="card-body text-center">
                            <img src="<c:url value="images/home.png"/>" class="icon" alt="lieux image"/>
                            <h5 class="card-title">Lieux</h5>
                            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                        </div>
                    </div>
                </div>

                <div class="col-md-4 d-flex justify-content-center">
                    <div class="card" style="width: 18rem;">
                        <div class="card-body text-center">
                            <img src="<c:url value="images/party.png"/>" class="icon" alt="Activitées image"/>
                            <h5 class="card-title">Activitées</h5>
                            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </section>

</body>
</html>
