<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="it">
<head>
    <title>${auto.marca} ${auto.modello} - MotorsRent</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&family=Playfair+Display:wght@700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/schedaAuto.css">
</head>

<body>

<jsp:include page="header.jsp" />

<div class="container detail-container">

    <a href="catalogo" class="back-link">&larr; Torna al catalogo</a>

    <div class="product-layout">

        <div class="product-image">
            <img src="${auto.immagine}" alt="${auto.modello}">
        </div>

        <div class="product-info">
            <div class="product-header">
                <span class="brand-tag">${auto.marca}</span>
                <h1>${auto.modello}</h1>
                <div class="product-price">€ ${auto.prezzo}</div>
            </div>

            <div class="specs-grid">
                <div class="spec-item">
                    <span class="label">Anno</span>
                    <span class="value">${auto.anno}</span>
                </div>
                <div class="spec-item">
                    <span class="label">Chilometraggio</span>
                    <span class="value">${auto.chilometraggio} km</span>
                </div>
                <div class="spec-item">
                    <span class="label">Stato</span>
                    <span class="value">${auto.stato}</span>
                </div>
                <div class="spec-item">
                    <span class="label">Disponibilità</span>
                    <span class="value ${auto.disponibilita ? 'text-green' : 'text-red'}">
                        ${auto.disponibilita ? 'Immediata' : 'Non disponibile'}
                    </span>
                </div>
            </div>

            <div class="description-box">
                <h3>Descrizione del veicolo</h3>
                <p>${auto.descrizione}</p>
            </div>

            <div class="actions-box">
                <c:choose>
                    <c:when test="${not empty sessionScope.utente && sessionScope.utente.ruolo == 'CLIENTE'}">
                        <a class="btn btn-primary btn-full" href="richiestaPreventivo?idAuto=${auto.idAuto}">Richiedi Preventivo</a>
                        <a class="btn btn-outline btn-full" href="richiestaLeasing?idAuto=${auto.idAuto}">Simula Leasing</a>
                    </c:when>

                    <c:when test="${sessionScope.utente.ruolo == 'AMMINISTRATORE'}">
                        <a class="btn btn-warning btn-full" href="AdminAutoController?action=editForm&id=${auto.idAuto}">Modifica Auto</a>
                    </c:when>

                    <c:otherwise>
                        <div class="login-alert">
                            <p>Accedi per richiedere un preventivo.</p>
                            <a href="login.jsp" class="btn btn-small">Accedi</a>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />

</body>
</html>