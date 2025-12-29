<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Dashboard Cliente - MotorsRent</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&family=Playfair+Display:wght@700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/dashboardCliente.css">

    <style>
        /* Stile per il testo "In attesa" */
        .waiting-text {
            color: #999;
            font-size: 0.85rem;
            font-style: italic;
            display: flex;
            align-items: center;
            gap: 5px;
        }
    </style>
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="container" style="padding: 40px 20px;">

    <h2 class="dashboard-title">Benvenuto, ${sessionScope.utente.nome}</h2>
    <p class="dashboard-subtitle">Qui puoi gestire le tue richieste.</p>

    <h3 class="section-title">I tuoi Preventivi</h3>

    <c:if test="${empty listaPreventivi}">
        <div class="empty-state">Non hai ancora richiesto preventivi.</div>
    </c:if>

    <c:if test="${not empty listaPreventivi}">
        <div class="table-container">
            <table class="premium-table">
                <tr>
                    <th>Rif.</th>
                    <th>Auto</th>
                    <th>Data</th>
                    <th>Stato</th>
                    <th>Azioni</th>
                </tr>
                <c:forEach var="p" items="${listaPreventivi}">
                    <tr>
                        <td>#${p.idPreventivo}</td>
                        <td><strong>${p.auto.marca} ${p.auto.modello}</strong></td>
                        <td>${p.dataRichiesta}</td>
                        <td>
                        <span class="status-badge
                            ${p.stato == 'APPROVATO' || p.stato == 'INVIATO' ? 'status-approvato' :
                             (p.stato == 'RIFIUTATO' ? 'status-rifiutato' : 'status-attesa')}">
                                ${p.stato}
                        </span>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${p.stato == 'IN VALUTAZIONE' || p.stato == 'NUOVA'}">
                                <span class="waiting-text">
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
            </table>
        </div>
    </c:if>

    <h3 class="section-title" style="margin-top: 50px;">Le tue Richieste Leasing</h3>

    <c:if test="${empty listaLeasing}">
        <div class="empty-state">Non hai richieste di leasing attive.</div>
    </c:if>

    <c:if test="${not empty listaLeasing}">
        <div class="table-container">
            <table class="premium-table">
                <tr>
                    <th>Rif.</th>
                    <th>Auto</th>
                    <th>Durata</th>
                    <th>Anticipo</th>
                    <th>Stato</th>
                    <th>Azioni</th>
                </tr>
                <c:forEach var="l" items="${listaLeasing}">
                    <tr>
                        <td>#${l.idLeasing}</td>
                        <td><strong>${l.auto.marca} ${l.auto.modello}</strong></td>
                        <td>${l.durataMesi} mesi</td>
                        <td>€ ${l.anticipo}</td>
                        <td>
                         <span class="status-badge
                            ${l.stato == 'APPROVATO' ? 'status-approvato' :
                             (l.stato == 'RIFIUTATO' ? 'status-rifiutato' : 'status-attesa')}">
                                 ${l.stato}
                         </span>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${l.stato == 'IN VALUTAZIONE' || l.stato == 'NUOVA'}">
                                <span class="waiting-text">
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
            </table>
        </div>
    </c:if>

</div>

<jsp:include page="footer.jsp"/>

</body>
</html>