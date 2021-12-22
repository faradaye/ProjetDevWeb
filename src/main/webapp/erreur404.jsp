<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Page introuvable</title>
    <%@ include file="WEB-INF/header.jsp"%>
</head>
<body>
<%@ include file="WEB-INF/menu.jsp"%>

<div class="container-fluid">
    <div class="row justify-content-center h-10">
        <div class="col text-center">
            <h1 class="">Page introuvable (erreur 404)</h1>
        </div>
    </div>
    <div class="row justify-content-center h-50">
        <div class="col img-col text-center h-100">
            <img src="<c:url value="images/erreur404.png"/>" alt="image erreur 404" class="img-fluid h-100">
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
