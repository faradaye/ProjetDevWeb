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
        <h1 style="text-align: center;">Inscription</h1>

        <div class="login-form">
            <form action="${pageContext.request.contextPath}/inscription" method="post" class="row g-2">
                <h2 class="text-center">Inscription</h2>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Identifiant" name="login" value= "${utilisateur.login}" required>
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
                <div class="form-group" style="text-align: center">
                    <button type="submit" class="btn btn-primary btn-block">Envoyer</button>
                    <button class="btn btn-primary" onClick="window.history.back()">Retour</button>
                </div>
                <div class="alert alert-danger" role="alert" id="displayErreurConnection">${erreur}</div>
            </form>
        </div>
    </div>
</body>
</html>
