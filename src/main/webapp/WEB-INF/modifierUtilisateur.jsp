<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <c:if test="${ sessionScope.utilisateur.id == idUtilisateur}">
        <title>Modifier son compte</title>
    </c:if>
    <c:if test="${ sessionScope.utilisateur.id != idUtilisateur}">
        <title>Modifier un compte</title>
    </c:if>
    <%@ include file="header.jsp"%>
    <script>
        $(document).ready(function() {
            $("#displayErreurModificationUtilisateur").hide();

            if($("#displayErreurModificationUtilisateur").html()!='')
                $("#displayErreurModificationUtilisateur").show();


            $("#btn-MdpReset").click(function (){
                $("#password").show();
                $("#labelInputMdpReset").show()
            });

        });

        function preview() {
            frame.src = URL.createObjectURL(event.target.files[0]);
        }
    </script>

</head>
<body>
    <%@ include file="menu.jsp"%>

    <div class="container-fluid">
        <div class="row my-3">
            <div class="col-2">
                <img src="" class="img-fluid" alt="image profile">
            </div>
            <div class="col-9">
                <c:if test="${ sessionScope.utilisateur.id == idUtilisateur}">
                    <h1>Modification de son compte (${utilisateur.login}) <c:if test="${utilisateur.administrateur eq true}"><span style="color: #ff0000">(ADMIN)</span></c:if></h1>
                </c:if>
                <c:if test="${ sessionScope.utilisateur.id != idUtilisateur}">
                    <h1>Modification de ${utilisateur.login} <c:if test="${utilisateur.administrateur eq true}"><span style="color: #ff0000">(ADMIN)</span></c:if></h1>
                </c:if>
            </div>
        </div>

        <button type="button" class="btn btn-primary" onClick="window.history.back()">Retour</button>
        <a type="button" class="btn btn-primary" role="button" href="utilisateurs">Liste des utilisateurs</a>
        <!--Nouveau formulaire-->
        <form method="post" class="my-3" action="${pageContext.request.contextPath}/modifierUtilisateur" enctype="multipart/form-data">
            <div class="alert alert-danger" role="alert" id="displayErreurModificationUtilisateur">${erreur}</div>
            <input type="hidden" name="idUtilisateur" value="${idUtilisateur}" />

            <div class="row my-3">
                <div class="col-4">
                    <label for="imageProfile" class="form-label">Photo de profile (Photo par defaut si laissé vide)</label>
                    <input class="form-control" type="file" id="imageProfile" name="imageProfile" accept="image/*" size="16777216" onchange="preview()"><br>
                    <c:if test="${empty utilisateur.imageProfile}">
                        <img id="frame" src="<c:url value="images/profileDefaut.png"/>" class="img-fluid col-6" />
                    </c:if>
                    <c:if test="${not empty utilisateur.imageProfile}">
                        <img id="frame" src="${utilisateur.imageProfile}" class="img-fluid col-6" />
                    </c:if>
                </div>
            </div>

            <div class="row my-3">
                <div class="col-12">
                    <label for="login" class="form-label">Identifiant</label>
                    <input type="text" class="form-control" id="login" name="login" placeholder="Ex: L.DuPont" value="${utilisateur.login}" required>
                </div>
            </div>

            <div class="col-4">
                <button type="button" id="btn-MdpReset" class="btn btn-primary">Reset mot de passe</button>
            </div>
            <div class="col-4">
                <label id="labelInputMdpReset" for="password" class="form-label" style="display: none">Nouveau Mot de passe: </label>
                <input type="password" class="form-control" style="display: none" id="password" name="password" placeholder="Ex: ChevalRizBatterie">
            </div>

            <div class="row my-3">
                <div class="col-6">
                    <label for="nom" class="form-label">Nom</label>
                    <input type="text" class="form-control" id="nom" name="nom" placeholder="Ex: DuPont" value="${utilisateur.nom}" required>
                </div>
                <div class="col-6">
                    <label for="prenom" class="form-label">Prenom</label>
                    <input type="text" class="form-control" id="prenom" name="prenom" placeholder="Ex: Louis" value="${utilisateur.prenom}" required>
                </div>
            </div>
            <div class="row my-3">
                <div class="col-6">
                    <label for="date_naissance" class="form-label">Date de naissance</label>
                    <input type="date" class="form-control" id="date_naissance" name="date_naissance" value="${utilisateur.date_naissance}" required>
                </div>
            </div>
            <div class="row my-3">
                <div class="col-12">
                    <label for="email" class="form-label">Email (optionnel: utile pour recuperer votre mot de passe ou être notifié en plus sur votre mail)</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Ex: exemple@gmail.com" value="${utilisateur.email}">
                </div>
            </div>
            <c:if test="${sessionScope.utilisateur.administrateur}">
                <div class="row my-3">
                    <div class="col-12">
                        <input type="checkbox" value="1" class="form-check-input" id="administrateur" name="administrateur" <c:if test="${utilisateur.administrateur eq true}">checked</c:if>>
                        <label for="administrateur" class="form-check-label">Administrateur</label>
                    </div>
                </div>
            </c:if>
            <div class="row my-3">
                <div class="col-12">
                    <button type="submit" class="btn btn-primary" name="submit">Mettre à jour</button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
