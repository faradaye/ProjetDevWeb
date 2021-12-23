<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Authentification</title>
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
        $(document).ready(function() {
            $("#displayErreur").hide();
            $("#displaySucces").hide();

            if($("#displayErreur").html()!='')
                $("#displayErreur").show();

            if($("#displaySucces").html()!='')
                $("#displaySucces").show();
        });
    </script>
</head>
<body>
    <%@ include file="menu.jsp"%>
    <div class="container-fluid">
        <h1 style="text-align: center;">Recuperation mot de passe</h1>

        <p style="text-align: center;">Merci de vous renseigner l'adresse email de votre compte.<br>Si vous n'avez pas renseignez d'adresse email, veuillez contacter un administrateur</p>

        <div class="login-form">
            <form action="${pageContext.request.contextPath}/recupeMotDePasse" method="post" class="row g-2">
                <h2 class="text-center">Recuperation mot de passe</h2>
                <div class="row my-3">
                    <div class="col-12">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="Ex: exemple@gmail.com" value="${email}">
                    </div>
                </div>
                <div class="form-group" style="text-align: center">
                    <button type="submit" class="btn btn-primary btn-block">Reinitialiser mot de passe</button>
                    <button class="btn btn-primary" onClick="window.history.back()">Retour</button>
                </div>
                <div class="alert alert-danger" role="alert" id="displayErreur">${erreur}</div>
                <div class="alert alert-success" role="alert" id="displaySucces">${messageDemandeAccepte}</div>
            </form>
        </div>
    </div>
</body>
</html>
