<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <!DOCTYPE html>
<html lang="it">
<head>
    <title>Dettaglio ${tipoRichiesta} - MotorsRent</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&family=Playfair+Display:wght@700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/dettaglioRichiesta.css">

</head>

<body>

<div class="no-print">
    <jsp:include page="header.jsp"/>
</div>

<div class="container">

    <div class="no-print" style="margin-top: 20px; text-align: center;">
        <a href="dashboardCliente" style="color: #545454; text-decoration: none;">&larr; Torna alla Dashboard</a>
    </div>

    <div class="detail-sheet">

        <div class="sheet-header">
            <div>
                <h1 class="doc-title">Dettaglio ${tipoRichiesta}</h1>
                <div class="doc-id">
                    Rif: #${tipoRichiesta == 'Preventivo' ? richiesta.idPreventivo : richiesta.idLeasing}
                    | Data: ${tipoRichiesta == 'Preventivo' ? richiesta.dataRichiesta : 'N/A'}
                </div>
            </div>
            <div style="text-align: right;">
                <img src="img/logo.jpg" alt="MotorsRent" style="height: 50px;"> </div>
        </div>

        <div class="info-grid">

            <div class="info-box">
                <h4>Dati Cliente</h4>
                <div class="info-row"><strong>Nome:</strong> ${richiesta.utente.nome} ${richiesta.utente.cognome}</div>
                <div class="info-row"><strong>Email:</strong> ${richiesta.utente.email}</div>
                <div class="info-row"><strong>Telefono:</strong> ${richiesta.utente.telefono}</div>
            </div>

            <div class="info-box">
                <h4>Veicolo Selezionato</h4>
                <div class="car-preview">
                    <img src="${richiesta.auto.immagine}" alt="Auto" class="car-thumb">
                    <div>
                        <div class="info-row" style="font-size: 1.2rem; font-weight: 700;">${richiesta.auto.marca} ${richiesta.auto.modello}</div>
                        <div class="info-row">Anno: ${richiesta.auto.anno}</div>
                        <div class="info-row">Prezzo Listino: € ${richiesta.auto.prezzo}</div>
                    </div>
                </div>
            </div>
        </div>

        <div class="info-box">
            <h4>Dettagli Economici & Note</h4>

            <c:choose>
                <c:when test="${tipoRichiesta == 'Preventivo'}">
                    <div class="info-row"><strong>Note Cliente:</strong></div>
                    <p style="background: #f9f9f9; padding: 15px; border-radius: 4px; color: #555;">
                            ${not empty richiesta.note ? richiesta.note : 'Nessuna nota aggiuntiva specificata.'}
                    </p>
                </c:when>

                <c:when test="${tipoRichiesta == 'Leasing'}">
                    <div class="info-row"><strong>Durata Contratto:</strong> ${richiesta.durataMesi} mesi</div>
                    <div class="info-row"><strong>Anticipo Versato:</strong> € ${richiesta.anticipo}</div>
                </c:when>
            </c:choose>
        </div>

        <div style="text-align: center; margin-top: 40px;">
            <div class="status-badge" style="color: ${richiesta.stato == 'APPROVATO' ? 'green' : (richiesta.stato == 'RIFIUTATO' ? 'red' : '#e67e22')}">
                STATO ATTUALE: ${richiesta.stato}
            </div>
        </div>

        <div class="no-print" style="margin-top: 40px; text-align: center; border-top: 1px solid #eee; padding-top: 20px;">
            <button onclick="window.print()" class="btn btn-secondary" style="padding: 10px 30px;">
                <i class="fa-solid fa-print"></i> Stampa
            </button>
        </div>

    </div>
</div>

<div class="no-print">
    <jsp:include page="footer.jsp"/>
</div>

</body>
</html>