<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <title>Dashboard Cliente - MotorsRent</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600&family=Playfair+Display:wght@700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">


    <link rel="stylesheet" href="css/dashboardCliente.css">
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="container">

    <div class="dashboard-header-card">
        <div class="header-text">
            <h1 class="welcome-title">Bentornato, ${sessionScope.utente.nome}</h1>
            <p class="welcome-subtitle">Ecco il riepilogo delle tue attività e richieste recenti.</p>
        </div>

        <div class="header-actions">
            <a href="profiloCliente.jsp" class="btn-action">
                <i class="fa-solid fa-user-pen"></i> Modifica Profilo
            </a>
        </div>
    </div>

    <h3 class="section-title">I tuoi Preventivi</h3>

    <c:if test="${empty listaPreventivi}">
        <div class="empty-state">
            <i class="fa-regular fa-folder-open" style="font-size: 2rem; margin-bottom: 10px; display:block;"></i>
            Non hai ancora richiesto preventivi.
        </div>
    </c:if>

    <c:if test="${not empty listaPreventivi}">
        <div class="table-container">
            <table class="premium-table">
                <thead>
                <tr>
                    <th>Rif.</th>
                    <th>Veicolo Richiesto</th>
                    <th>Data</th>
                    <th>Stato</th>
                    <th>Azioni</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="p" items="${listaPreventivi}">
                    <tr>
                        <td>#${p.idPreventivo}</td>
                        <td><strong>${p.auto.marca} ${p.auto.modello}</strong></td>
                        <td style="color:#666;">${p.dataRichiesta}</td>
                        <td>
                                <span class="status-badge status-${p.stato.toLowerCase()}">
                                        ${p.stato.replace('_', ' ')}
                                </span>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${p.stato == 'IN VALUTAZIONE' || p.stato == 'NUOVA' || p.stato == 'IN_LAVORAZIONE'}">
                                        <span class="waiting-status">
                                            <i class="fa-regular fa-clock"></i> In lavorazione...
                                        </span>
                                </c:when>
                                <c:otherwise>
                                    <a href="dettaglioRichiesta?tipo=preventivo&id=${p.idPreventivo}" class="btn-view">
                                        <i class="fa-regular fa-eye"></i> Visualizza Offerta
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>

    <h3 class="section-title" style="margin-top: 50px;">Le tue Richieste Leasing</h3>

    <c:if test="${empty listaLeasing}">
        <div class="empty-state">
            <i class="fa-regular fa-folder-open" style="font-size: 2rem; margin-bottom: 10px; display:block;"></i>
            Non hai richieste di leasing attive.
        </div>
    </c:if>

    <c:if test="${not empty listaLeasing}">
        <div class="table-container">
            <table class="premium-table">
                <thead>
                <tr>
                    <th>Rif.</th>
                    <th>Veicolo</th>
                    <th>Durata</th>
                    <th>Anticipo</th>
                    <th>Stato</th>
                    <th>Azioni</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="l" items="${listaLeasing}">
                    <tr>
                        <td>#${l.idLeasing}</td>
                        <td><strong>${l.auto.marca} ${l.auto.modello}</strong></td>
                        <td>${l.durataMesi} mesi</td>
                        <td>€ ${l.anticipo}</td>
                        <td>
                                 <span class="status-badge status-${l.stato.toLowerCase()}">
                                         ${l.stato.replace('_', ' ')}
                                 </span>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${l.stato == 'IN VALUTAZIONE' || l.stato == 'NUOVA' || l.stato == 'IN_VALUTAZIONE'}">
                                        <span class="waiting-status">
                                            <i class="fa-regular fa-clock"></i> In lavorazione...
                                        </span>
                                </c:when>
                                <c:otherwise>
                                    <a href="dettaglioRichiesta?tipo=leasing&id=${l.idLeasing}" class="btn-view">
                                        <i class="fa-regular fa-eye"></i> Visualizza Esito
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>

</div>

<jsp:include page="footer.jsp"/>

</body>
</html>