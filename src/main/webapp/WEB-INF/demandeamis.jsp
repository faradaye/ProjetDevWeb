<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Demande d'amis</title>
</head>
<body>
<h1>Envoyer une demande d'amis</h1>

<p style="color: red;">${erreur}</p>

<form method="post" action="${pageContext.request.contextPath}/demandeamis">
    <p>Adresse mail de l'ami:</p>
    <input type="text" name="loginami"/><br>

    <input type="submit"/>
</form>
</body>
</html>
