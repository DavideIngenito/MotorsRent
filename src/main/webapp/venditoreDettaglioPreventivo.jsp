<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <title>Dettaglio Preventivo - MotorsRent</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&family=Playfair+Display:wght@700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/header.css">
     </head>

<body>

<jsp:include page="header.jsp"/>

<div class="sheet-wrapper">
    <div class="detail-sheet">

        <div class="sheet-header">
            <div>
                <h1 class="doc-title">Valutazione Preventivo</h1>
                <div class="doc-meta">Rif: #${preventivo.idPreventivo} | Data: ${preventivo.dataRichiesta}</div>
            </div>
        </div>

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
                        <div style="font-weight:bold; margin-top:5px;">Listino: € ${preventivo.auto.prezzo}</div>
                    </div>
                </div>
            </div>
        </div>

        <div class="info-section">
            <h4>Note dal Cliente</h4>
            <div class="notes-box">
                "${preventivo.note}"
            </div>
        </div>

        <hr style="margin: 40px 0; border: 0; border-top: 1px solid #eee;">

        <div class="info-section" style="background: #f9f9f9; padding: 30px; border-radius: 8px;">
            <h2 style="font-family:'Playfair Display'; margin-bottom: 20px;">Invia Risposta Commerciale</h2>

            <form action="gestisciPreventivo" method="post">
                <input type="hidden" name="idPreventivo" value="${preventivo.idPreventivo}">

                <div style="display:grid; grid-template-columns: 1fr 1fr; gap: 20px; margin-bottom: 20px;">
                    <div>
                        <label style="font-weight:bold; display:block; margin-bottom:5px;">Prezzo Finale Offerto (€)</label>
                        <input type="number" name="importo" required style="width:100%; padding:10px; border:1px solid #ccc; border-radius:4px;">
                    </div>
                    <div>
                        <label style="font-weight:bold; display:block; margin-bottom:5px;">Stato Pratica</label>
                        <select name="stato" style="width:100%; padding:10px; border:1px solid #ccc; border-radius:4px;">
                            <option value="INVIATO">Inviato (Offerta Pronta)</option>
                            <option value="RIFIUTATO">Rifiuta Richiesta</option>
                            <option value="IN_LAVORAZIONE" selected>In Lavorazione</option>
                        </select>
                    </div>
                </div>

                <label style="font-weight:bold; display:block; margin-bottom:5px;">Messaggio per il Cliente</label>
                <textarea name="messaggio" rows="4" required style="width:100%; padding:10px; border:1px solid #ccc; border-radius:4px;" placeholder="Gentile cliente, ecco la nostra migliore offerta..."></textarea>

                <button type="submit" class="btn" style="margin-top: 20px; width: 100%;">Conferma e Invia</button>
            </form>
        </div>

    </div>
</div>

<jsp:include page="footer.jsp"/>

</body>
</html>