<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestione Auto - MotorsRent</title>

    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600&family=Playfair+Display:wght@700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="styles/adminGestioneAuto.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>

<jsp:include page="/header.jsp"/>

<div class="admin-container">

    <h1>Gestione Catalogo Auto</h1>

    <a href="AdminAutoController?action=addForm" class="btn add-btn">
        <i class="fa-solid fa-plus" style="margin-right: 8px;"></i> Aggiungi Nuova Auto
    </a>

    <div class="table-wrapper">
        <table class="table">
            <thead>
            <tr>
                <th width="15%">Anteprima</th> <th>Marca</th>
                <th>Modello</th>
                <th>Anno</th>
                <th>Prezzo (€)</th>
                <th width="20%">Azioni</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="auto" items="${listaAuto}">
                <tr>
                    <td>
                        <img src="${not empty auto.immagine ? auto.immagine : 'img/placeholder-car.png'}" class="preview" alt="Auto">
                    </td>
                    <td><strong>${auto.marca}</strong></td>
                    <td>${auto.modello}</td>
                    <td>${auto.anno}</td>
                    <td style="font-family: monospace; font-size: 1rem;">€ ${auto.prezzo}</td>
                    <td>
                        <a href="AdminAutoController?action=editForm&id=${auto.idAuto}" class="btn-small">
                            <i class="fa-solid fa-pen"></i> Modifica
                        </a>
                        <a href="AdminAutoController?action=delete&id=${auto.idAuto}"
                           class="btn-small delete"
                           onclick="return confirm('Sei sicuro di voler eliminare la ${auto.marca} ${auto.modello}?');">
                            <i class="fa-solid fa-trash"></i> Elimina
                        </a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty listaAuto}">
                <tr>
                    <td colspan="6" style="text-align: center; padding: 40px; color: #999;">
                        Nessuna auto presente nel catalogo.
                    </td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>

    <div style="margin-top: 30px;">
        <a href="dashboardAdmin" class="btn btn-secondary">&larr; Torna alla Dashboard</a>
    </div>

</div>

<jsp:include page="footer.jsp"/>
</body>
</html>