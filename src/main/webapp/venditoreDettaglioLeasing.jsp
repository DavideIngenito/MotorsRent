<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="it">
<head>
  <title>Dettaglio Leasing - MotorsRent</title>
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&family=Playfair+Display:wght@700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="css/header.css">
  >
</head>

<body>

<jsp:include page="header.jsp"/>

<div class="sheet-wrapper">
  <div class="detail-sheet">

    <div class="sheet-header">
      <div>
        <h1 class="doc-title">Analisi Richiesta Leasing</h1>
        <div class="doc-meta">Rif: #${leasing.idLeasing} | Data: ${leasing.dataRichiesta}</div>
      </div>
    </div>

    <div class="info-grid">
      <div class="info-section">
        <h4>Richiedente</h4>
        <div class="data-row"><span class="data-label">Nome:</span> ${leasing.utente.nome} ${leasing.utente.cognome}</div>
        <div class="data-row"><span class="data-label">Email:</span> ${leasing.utente.email}</div>
        <div class="data-row"><span class="data-label">Telefono:</span> ${leasing.utente.telefono}</div>
      </div>

      <div class="info-section">
        <h4>Veicolo</h4>
        <div class="car-preview-box">
          <img src="${leasing.auto.immagine}" alt="Auto" class="car-thumb">
          <div>
            <div class="car-details-text">${leasing.auto.marca} ${leasing.auto.modello}</div>
            <div>Listino: € ${leasing.auto.prezzo}</div>
          </div>
        </div>
      </div>
    </div>

    <div class="info-section">
      <h4>Parametri Finanziari Richiesti</h4>
      <div style="display: flex; gap: 40px; background: #fafafa; padding: 20px; border-radius: 8px;">
        <div>
          <div style="font-size: 0.8rem; color: #888; text-transform: uppercase;">Durata</div>
          <div style="font-size: 1.2rem; font-weight: bold;">${leasing.durataMesi} Mesi</div>
        </div>
        <div>
          <div style="font-size: 0.8rem; color: #888; text-transform: uppercase;">Anticipo</div>
          <div style="font-size: 1.2rem; font-weight: bold;">€ ${leasing.anticipo}</div>
        </div>
        <div>
          <div style="font-size: 0.8rem; color: #888; text-transform: uppercase;">Km Annui</div>
          <div style="font-size: 1.2rem; font-weight: bold;">${leasing.kmAnnui} km</div>
        </div>
      </div>
    </div>

    <hr style="margin: 40px 0; border: 0; border-top: 1px solid #eee;">

    <div class="info-section" style="background: #f9f9f9; padding: 30px; border-radius: 8px;">
      <h2 style="font-family:'Playfair Display'; margin-bottom: 20px;">Esito Valutazione</h2>

      <form action="gestisciLeasing" method="post">
        <input type="hidden" name="idLeasing" value="${leasing.idLeasing}">

        <div style="display:grid; grid-template-columns: 1fr 1fr; gap: 20px; margin-bottom: 20px;">
          <div>
            <label style="font-weight:bold; display:block; margin-bottom:5px;">Rata Mensile Proposta (€)</label>
            <input type="number" step="0.01" name="rata" placeholder="0.00" required style="width:100%; padding:10px; border:1px solid #ccc; border-radius:4px;">
          </div>
          <div>
            <label style="font-weight:bold; display:block; margin-bottom:5px;">Esito Pratica</label>
            <select name="stato" style="width:100%; padding:10px; border:1px solid #ccc; border-radius:4px;">
              <option value="APPROVATO">Approva Richiesta</option>
              <option value="RIFIUTATO">Rifiuta Richiesta</option>
              <option value="IN_VALUTAZIONE" selected>In Valutazione</option>
            </select>
          </div>
        </div>

        <label style="font-weight:bold; display:block; margin-bottom:5px;">Note Interne / Messaggio al Cliente</label>
        <textarea name="messaggio" rows="4" style="width:100%; padding:10px; border:1px solid #ccc; border-radius:4px;"></textarea>

        <button type="submit" class="btn" style="margin-top: 20px; width: 100%;">Registra Esito</button>
      </form>
    </div>

  </div>
</div>

<jsp:include page="footer.jsp"/>

</body>
</html>