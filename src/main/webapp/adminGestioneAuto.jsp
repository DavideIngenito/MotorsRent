
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Gestione Auto - MotorsRent</title>
    <link rel="stylesheet" href="styles/admin.css">
</head>
<body>

<jsp:include page="/header.jsp"/>

<h1>Gestione Catalogo Auto</h1>

<a href="AdminAutoController?action=addForm" class="btn add-btn">+ Aggiungi Nuova Auto</a>

<table class="table">
    <thead>
    <tr>
        <th>Immagine</th>
        <th>Marca</th>
        <th>Modello</th>
        <th>Anno</th>
        <th>Prezzo (€)</th>
        <th>Azioni</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="auto" items="${listaAuto}">
        <tr>
            <td><img src="${auto.immagine}" class="preview"></td>
            <td>${auto.marca}</td>
            <td>${auto.modello}</td>
            <td>${auto.anno}</td>
            <td>${auto.prezzo}</td>
            <td>
                <a href="AdminAutoController?action=editForm&id=${auto.idAuto}" class="btn-small">Modifica</a>
                <a href="AdminAutoController?action=delete&id=${auto.idAuto}"
                   class="btn-small delete"
                   onclick="return confirm('Sei sicuro di voler eliminare questa auto?');">
                    Elimina
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>