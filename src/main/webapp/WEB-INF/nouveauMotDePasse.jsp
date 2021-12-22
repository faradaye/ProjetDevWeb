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
            $(".displayErreur").hide();

            if($(".displayErreur").html()!='')
                $(".displayErreur").show();
        });
    </script>
</head>
<body>
    <%@ include file="menu.jsp"%>
    <div class="container-fluid">
        <h1 style="text-align: center;">Recuperation mot de passe</h1>

        <c:if test="${not empty token}">
            <div class="login-form">
                <form action="${pageContext.request.contextPath}/nouveauMotDePasse" method="post" class="row g-2">
                    <h2 class="text-center">Nouveau mot de passe</h2>
                    <input type="hidden" value="${token}"/>
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="Mot de Passe" name="password" value= "${utilisateur.password}" required>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="Confirmation Mot de Passe" name="confPassword" value= "${confPassword}" required>
                    </div>
                    <div class="form-group" style="text-align: center">
                        <button type="submit" class="btn btn-primary btn-block">Confirmer</button>
                        <button class="btn btn-primary" onClick="window.history.back()">Retour</button>
                    </div>
                    <div class="alert alert-danger displayErreur" role="alert">${erreur}</div>
                </form>
            </div>
        </c:if>
        <c:if test="${empty token}">
            <div class="login-form">
                <form action="${pageContext.request.contextPath}/nouveauMotDePasse" method="post" class="row g-2">
                    <h2 class="text-center">Nouveau mot de passe</h2>
                    <div class="alert alert-danger displayErreur" role="alert">${erreur}</div>
                </form>
            </div>
        </c:if>

    </div>
</body>
</html>
