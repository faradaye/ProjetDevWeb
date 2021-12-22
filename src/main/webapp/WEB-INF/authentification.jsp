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
            $("#displayErreurConnection").hide();

            if($("#displayErreurConnection").html()!='')
                $("#displayErreurConnection").show();

        });
    </script>
</head>
<body>
    <%@ include file="menu.jsp"%>
    <div class="container-fluid">
        <h1 style="text-align: center;">Authentification</h1>

        <p style="text-align: center;">Merci de vous connecter afin de continuer.</p>

        <div class="login-form">
            <form action="${pageContext.request.contextPath}/authentification" method="post" class="row g-2">
                <input type="hidden" name="redirectId" value="${param.redirectId}" />
                <h2 class="text-center">Connection</h2>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Identifiant" name="login" required>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="Mot de Passe" name="password" required>
                </div>
                <div class="form-group" style="text-align: center">
                    <button type="submit" class="btn btn-primary btn-block">Envoyer</button>
                    <button class="btn btn-primary" onClick="window.history.back()">Retour</button>
                </div>
                <div class="clearfix">
                    <a href="recupeMotDePasse" class="float-right">Vous avez oubliez votre mot de passe ?</a>
                </div>
                <div class="alert alert-danger" role="alert" id="displayErreurConnection">${erreur}</div>
            </form>
        </div>
    </div>
</body>
</html>
