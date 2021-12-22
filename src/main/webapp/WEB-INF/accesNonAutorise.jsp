<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Acces Non Autorisé</title>
    <%@ include file="header.jsp"%>
</head>
<body>
    <%@ include file="menu.jsp"%>
    <div class="container-fluid">
        <div class="row justify-content-center h-10">
            <div class="col text-center">
                <h1 class="">Acces Non Autorisé</h1>
            </div>
        </div>
        <div class="row justify-content-center h-50">
            <div class="col img-col text-center h-100">
                <img src="<c:url value="../images/accesNonAutorise.png"/>" alt="image acces non autorisé" class="img-fluid h-100">
            </div>
        </div>
        <div class="row justify-content-center h-5">
            <div class="col text-center h-100">
                <a class="btn btn-primary h-100" role="button" href="javascript:history.back()">Retour</a>
            </div>
        </div>
    </div>

</body>
</html>
