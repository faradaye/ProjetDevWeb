<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Inscription</title>
    <%@ include file="header.jsp"%>
    <style>
        .login-form {
            width: 340px;
            margin: 50px auto;
            font-size: 15px;
        }
        .login-form form {
            margin-bottom: 15px;
            background: #f7f7f7;
            box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
            padding: 30px;
        }
        .login-form h2 {
            margin: 0 0 15px;
        }
        .form-control, .btn {
            min-height: 38px;
            border-radius: 2px;
        }
        .btn {
            font-size: 15px;
            font-weight: bold;
        }
    </style>
    <script>
        function loginUtilisateurExiste(login) {
            $.ajax({
                type: "POST",
                url: "ajax",
                data: {
                    'categorie': "loginUtilisateurExiste",
                    'login' : login

                },
                success: function(html) {
                    if(html!=="")
                        $("#alertLogin").html(html).show();
                    else
                        $("#alertLogin").hide();
                }
            });
        }

        function emailUtilisateurExiste(email) {
            $.ajax({
                type: "POST",
                url: "ajax",
                data: {
                    'categorie': "emailUtilisateurExiste",
                    'email': email

                },
                success: function(html) {
                    if(html!=="")
                        $("#alertEmail").html(html).show();
                    else
                        $("#alertEmail").hide();
                }
            });
        }

        $(document).ready(function() {
            $("#displayErreurInscription").hide();
            $("#alertEmail").hide();
            $("#alertLogin").hide();

            if($("#displayErreurInscription").html()!='')
                $("#displayErreurInscription").show();

            $("#login").keyup(delay(function() {
                let login = $("#login").val();
                loginUtilisateurExiste(login);
            }, 1000));

            $("#email").keyup(delay(function() {
                let email = $("#email").val();
                emailUtilisateurExiste(email);
            }, 1000));

            <c:if test="${empty utilisateur.imageProfile}">
                $("#frame").hide();
            </c:if>
            <c:if test="${not empty utilisateur.imageProfile}">
                $("#frame2").hide();
            </c:if>

        });

        function preview() {

            <c:if test="${empty utilisateur.imageProfile}">
                $("#frame").show();
                frame.src = URL.createObjectURL(event.target.files[0]);
            </c:if>
            <c:if test="${not empty utilisateur.imageProfile}">
                $("#frame2").show();
                frame2.src = URL.createObjectURL(event.target.files[0]);
            </c:if>
        }

    </script>
</head>
<body>
    <%@ include file="menu.jsp"%>

    <div class="container-fluid">
        <h1 style="text-align: center;">Inscription</h1>

        <div class="login-form">
            <form action="${pageContext.request.contextPath}/inscription" method="post" class="row g-2" enctype="multipart/form-data">
                <h2 class="text-center">Inscription</h2>
                <div class="form-group">
                    <label for="imageProfile" class="form-label">Photo de profile (Photo par defaut si laissé vide)</label>
                    <input class="form-control" type="file" id="imageProfile" name="imageProfile" accept="image/*" size="16777216" onchange="preview()"><br>
                    <c:if test="${empty utilisateur.imageProfile}">
                        <div class="text-center"><img id="frame" src="" class="img-fluid col-6"  alt=""/></div>
                    </c:if>
                    <c:if test="${not empty utilisateur.imageProfile}">
                        <div class="text-center"><img id="frame2" src="${utilisateur.imageProfile}" class="img-fluid col-6"  alt=""/></div>
                    </c:if>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Identifiant" name="login" id="login" value= "${utilisateur.login}" required>
                    <div class="alert alert-danger" role="alert" id="alertLogin"></div>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="Mot de Passe" name="password" value= "${utilisateur.password}" required>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Nom" name="nom" value= "${utilisateur.nom}" required>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Prenom" name="prenom" value= "${utilisateur.prenom}" required>
                </div>
                <div class="form-group">
                    <label for="date_naissance" class="form-label">Date de naissance:</label>
                    <input type="date" class="form-control" id="date_naissance" name="date_naissance" value="${utilisateur.date_naissance}" required>
                </div>
                <div class="form-group">
                    <label for="email" class="form-label">Email (optionnel: utile pour recuperer votre mot de passe ou être notifié en plus sur votre mail)</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Ex: exemple@gmail.com" value="${utilisateur.email}">
                    <div class="alert alert-danger" role="alert" id="alertEmail">
                    </div>
                </div>
                <div class="form-group" style="text-align: center">
                    <button type="submit" class="btn btn-primary btn-block">Envoyer</button>
                    <button class="btn btn-primary" onClick="window.history.back()">Retour</button>
                </div>
                <div class="alert alert-danger" role="alert" id="displayErreurInscription">${erreur}</div>
            </form>
        </div>
    </div>
</body>
</html>
