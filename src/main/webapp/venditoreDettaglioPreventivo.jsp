<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <title>Valutazione Preventivo - MotorsRent</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dettaglio.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        .alert-error {
            background-color: #f8d7da; color: #721c24; padding: 15px; border-radius: 4px;
            margin-bottom: 20px; border: 1px solid #f5c6cb; display: flex; align-items: center; gap: 10px; font-weight: bold;
        }
    </style>
</head>
<body>

<div class="no-print"><jsp:include page="header.jsp"/></div>

<div class="sheet-wrapper">
    <div class="detail-sheet">
        <div class="sheet-header">
            <div>
                <h1 class="doc-title">Valutazione Preventivo</h1>
                <div class="doc-meta">Rif: #${preventivo.idPreventivo} | Data: <fmt:formatDate value="${preventivo.dataRichiesta}" pattern="dd/MM/yyyy"/></div>
            </div>
        </div>

        <c:if test="${not empty errore}">
            <div class="alert-error"><i class="fa-solid fa-circle-exclamation"></i> <span>${errore}</span></div>
        </c:if>

        <div class="info-grid">
            <div class="info-section">
                <h4>Richiedente</h4>
                <div class="data-row"><span class="data-label">Nome:</span> ${preventivo.utente.nome} ${preventivo.utente.cognome}</div>
                <div class="data-row"><span class="data-label">Email:</span> ${preventivo.utente.email}</div>
                <div class="data-row"><span class="data-label">Telefono:</span> ${preventivo.utente.telefono}</div>
            </div>
            <div class="info-section">
                <h4>Veicolo Oggetto della Richiesta</h4>
                <div class="car-preview-box">
                    <img src="${preventivo.auto.immagine}" alt="Auto" class="car-thumb">
                    <div>
                        <div class="car-details-text">${preventivo.auto.marca} ${preventivo.auto.modello}</div>
                        <div>Anno: ${preventivo.auto.anno}</div>
                        <div style="font-weight:bold; margin-top:5px;">Listino: € <fmt:formatNumber value="${preventivo.auto.prezzo}" type="number"/></div>
                    </div>
                </div>
            </div>
        </div>

        <c:if test="${not empty preventivo.note}">
            <div class="info-section">
                <h4>Note dal Cliente</h4>
                <div class="notes-box">"${preventivo.note}"</div>
            </div>
        </c:if>

        <hr style="margin: 40px 0; border: 0; border-top: 1px solid #eee;">

        <div class="info-section offer-box">
            <h2 style="font-family:'Playfair Display'; margin-bottom: 20px;">Invia Risposta Commerciale</h2>

            <form action="gestisciPreventivo" method="post">
                <input type="hidden" name="idPreventivo" value="${preventivo.idPreventivo}">

                <div style="display:grid; grid-template-columns: 1fr 1fr; gap: 20px; margin-bottom: 20px;">
                    <div>
                        <label style="font-weight:bold; display:block; margin-bottom:5px;">Prezzo Finale Offerto (€)</label>
                        <input type="number" step="0.01" name="prezzo" required style="width:100%; padding:10px; border:1px solid #ccc; border-radius:4px;"
                               placeholder="Es. 14500" min="0"
                               value="${param.prezzo != null ? param.prezzo : preventivo.prezzoProposto}">
                    </div>
                    <div>
                        <label style="font-weight:bold; display:block; margin-bottom:5px;">Stato Pratica</label>
                        <select name="stato" style="width:100%; padding:10px; border:1px solid #ccc; border-radius:4px;">
                            <option value="INVIATO" ${preventivo.stato == 'OFFERTA INVIATA' ? 'selected' : ''}>Inviato (Offerta Pronta)</option>
                            <option value="RIFIUTATO" ${preventivo.stato == 'RIFIUTATO' ? 'selected' : ''}>Rifiuta Richiesta</option>
                            <option value="IN_LAVORAZIONE" ${preventivo.stato == 'IN VALUTAZIONE' ? 'selected' : ''}>In Lavorazione</option>
                        </select>
                    </div>
                </div>

                <label style="font-weight:bold; display:block; margin-bottom:5px;">Messaggio per il Cliente</label>
                <textarea name="messaggio" rows="4" required style="width:100%; padding:10px; border:1px solid #ccc; border-radius:4px;"
                          placeholder="Gentile cliente..."
                          oninput="this.value = this.value.replace(/[^a-zA-Z0-9\s.,!?'-]/g, '')">${param.messaggio != null ? param.messaggio : preventivo.messaggioVenditore}</textarea>

                <button type="submit" class="btn" style="margin-top: 20px; width: 100%;">Conferma e Invia</button>
            </form>
        </div>
    </div>
</div>
<div class="no-print"><jsp:include page="footer.jsp"/></div>
</body>
</html>