<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <title>Dettaglio ${tipoRichiesta} - MotorsRent</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&family=Playfair+Display:wght@700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/dettaglio.css">
</head>
<body>

<div class="no-print">
    <jsp:include page="header.jsp"/>
</div>

<div class="sheet-wrapper">
    <div class="detail-sheet">

        <div class="sheet-header">
            <div>
                <h1 class="doc-title">Dettaglio ${tipoRichiesta}</h1>
                <div class="doc-meta">
                    Rif: #<c:choose>
                    <c:when test="${tipoRichiesta == 'Preventivo'}">${richiesta.idPreventivo}</c:when>
                    <c:when test="${tipoRichiesta == 'Leasing'}">${richiesta.idLeasing}</c:when>
                </c:choose>
                    | Data: ${richiesta.dataRichiesta}
                </div>
            </div>
            <div style="text-align: right;">
                <img src="img/logo.png" alt="MotorsRent" style="height: 50px;">
            </div>
        </div>

        <div class="info-grid">
            <div class="info-section">
                <h4>Dati Cliente</h4>
                <div class="data-row"><span class="data-label">Nome:</span> ${richiesta.utente.nome} ${richiesta.utente.cognome}</div>
                <div class="data-row"><span class="data-label">Email:</span> ${richiesta.utente.email}</div>
                <div class="data-row"><span class="data-label">Telefono:</span> ${richiesta.utente.telefono}</div>
            </div>

            <div class="info-section">
                <h4>Veicolo Selezionato</h4>
                <div class="car-preview-box">
                    <img src="${richiesta.auto.immagine}" alt="Auto" class="car-thumb">
                    <div>
                        <div class="car-details-text">${richiesta.auto.marca} ${richiesta.auto.modello}</div>
                        <div>Anno: ${richiesta.auto.anno}</div>
                        <div>Listino: € ${richiesta.auto.prezzo}</div>
                    </div>
                </div>
            </div>
        </div>

        <div class="info-section">
            <h4>Dettagli Richiesta</h4>

            <c:choose>
                <c:when test="${tipoRichiesta == 'Preventivo'}">
                    <div class="notes-box">
                        <strong>Nota Iniziale Cliente:</strong><br>
                            ${not empty richiesta.note ? richiesta.note : 'Nessuna nota aggiuntiva.'}
                    </div>
                </c:when>

                <c:when test="${tipoRichiesta == 'Leasing'}">
                    <div style="display:flex; gap:30px;">
                        <div><strong>Durata:</strong> ${richiesta.durataMesi} mesi</div>
                        <div><strong>Anticipo:</strong> € ${richiesta.anticipo}</div>
                        <div><strong>Km/Anno:</strong> ${richiesta.kmAnnui}</div>
                    </div>
                </c:when>
            </c:choose>
        </div>

        <c:if test="${not empty richiesta.messaggioVenditore
                      or (tipoRichiesta eq 'Preventivo' and richiesta.prezzoProposto > 0)
                      or (tipoRichiesta eq 'Leasing' and richiesta.rataMensile > 0)}">

            <hr style="margin: 40px 0; border: 0; border-top: 1px solid #eee;">

            <div class="info-section" style="background: #f4faff; padding: 20px; border-radius: 8px; border: 1px solid #d0e3ff;">
                <h4 style="color: #0056b3; border-color: #0056b3;">Offerta Concessionaria</h4>

                <div style="font-size: 1.2rem; font-weight: bold; margin-bottom: 15px; color: #121212;">
                    <c:choose>
                        <c:when test="${tipoRichiesta == 'Preventivo'}">
                            Prezzo Finale Proposto: € ${richiesta.prezzoProposto}
                        </c:when>
                        <c:when test="${tipoRichiesta == 'Leasing'}">
                            Rata Mensile Proposta: € ${richiesta.rataMensile}
                        </c:when>
                    </c:choose>
                </div>

                <c:if test="${not empty richiesta.messaggioVenditore}">
                    <div style="color: #555;">
                        <strong>Messaggio:</strong><br>
                            ${richiesta.messaggioVenditore}
                    </div>
                </c:if>
            </div>
        </c:if>

        <div class="stamp-box">
            <span class="stamp ${richiesta.stato}">${richiesta.stato}</span>
        </div>

        <div class="print-actions">
            <button onclick="window.print()" class="btn-print">
                <i class="fa-solid fa-print"></i> Stampa Documento
            </button>
        </div>

    </div>
</div>

<div class="no-print">
    <jsp:include page="footer.jsp"/>
</div>

</body>
</html>