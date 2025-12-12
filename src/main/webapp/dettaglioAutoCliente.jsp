
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${auto.marca} ${auto.modello}</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

<jsp:include page="navbarCliente.jsp"/>

<h2>${auto.marca} ${auto.modello}</h2>

<div class="auto-details">
    <img src="${auto.immagine}">
    <ul>
        <li>Anno: ${auto.anno}</li>
        <li>Prezzo: € ${auto.prezzo}</li>
        <li>Chilometraggio: ${auto.chilometraggio} km</li>
        <li>Stato: ${auto.stato}</li>
    </ul>
</div>

<a href="richiestaPreventivo.jsp?idAuto=${auto.idAuto}" class="btn">Richiedi Preventivo</a>
<a href="richiestaLeasing.jsp?idAuto=${auto.idAuto}" class="btn2">Richiedi Leasing</a>

</body>
</html>