<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="it">
<head>
  <title>Valutazione Leasing - MotorsRent</title>
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
        <h1 class="doc-title">Valutazione Leasing</h1>
        <div class="doc-meta">Rif: #${leasing.idLeasing} | Data: <fmt:formatDate value="${leasing.dataRichiesta}" pattern="dd/MM/yyyy"/></div>
      </div>
    </div>

    <c:if test="${not empty errore}">
      <div class="alert-error"><i class="fa-solid fa-circle-exclamation"></i> <span>${errore}</span></div>
    </c:if>

    <div class="info-grid">
      <div class="info-section">
        <h4>Richiedente</h4>
        <div class="data-row"><span class="data-label">Nome:</span> ${leasing.utente.nome} ${leasing.utente.cognome}</div>
        <div class="data-row"><span class="data-label">Email:</span> ${leasing.utente.email}</div>
        <div class="data-row"><span class="data-label">Telefono:</span> ${leasing.utente.telefono}</div>
      </div>

      <div class="info-section">
        <h4>Veicolo Scelto</h4>
        <div class="car-preview-box">
          <img src="${leasing.auto.immagine}" alt="Auto" class="car-thumb">
          <div>
            <div class="car-details-text">${leasing.auto.marca} ${leasing.auto.modello}</div>
            <div>Anno: ${leasing.auto.anno}</div>
          </div>
        </div>
      </div>
    </div>

    <div class="info-section" style="margin-top:20px;">
      <h4>Piano:</h4>
      <span>Anticipo: € ${leasing.anticipo} | Durata: ${leasing.durataMesi} mesi | Km: ${leasing.kmAnnui}</span>
    </div>

    <hr style="margin: 30px 0; border: 0; border-top: 1px solid #eee;">

    <div class="info-section offer-box">
      <h2 style="font-family:'Playfair Display'; margin-bottom: 20px;">Esito Valutazione</h2>

      <form action="gestisciLeasing" method="post">
        <input type="hidden" name="idLeasing" value="${leasing.idLeasing}">

        <div style="display:grid; grid-template-columns: 1fr 1fr; gap: 20px; margin-bottom: 20px;">
          <div>
            <label style="font-weight:bold; display:block; margin-bottom:5px;">Rata Mensile (€)</label>
            <input type="number" step="0.01" name="rata" required style="width:100%; padding:10px; border:1px solid #ccc; border-radius:4px;"
                   min="0" value="${param.rata != null ? param.rata : leasing.rataMensile}">
          </div>
          <div>
            <label style="font-weight:bold; display:block; margin-bottom:5px;">Esito Pratica</label>
            <select name="stato" style="width:100%; padding:10px; border:1px solid #ccc; border-radius:4px;">
              <option value="APPROVATO" ${leasing.stato == 'APPROVATO' ? 'selected' : ''}>Approva Richiesta</option>
              <option value="RIFIUTATO" ${leasing.stato == 'RIFIUTATO' ? 'selected' : ''}>Rifiuta Richiesta</option>
              <option value="IN_VALUTAZIONE" ${leasing.stato == 'IN VALUTAZIONE' ? 'selected' : ''}>In Valutazione</option>
            </select>
          </div>
        </div>

        <label style="font-weight:bold; display:block; margin-bottom:5px;">Note Interne / Motivazione</label>
        <textarea name="messaggio" rows="4" required style="width:100%; padding:10px; border:1px solid #ccc; border-radius:4px;"
                  placeholder="Note..."
                  oninput="this.value = this.value.replace(/[^a-zA-Z0-9\s.,!?'-]/g, '')">${param.messaggio != null ? param.messaggio : leasing.messaggioVenditore}</textarea>

        <button type="submit" class="btn" style="margin-top: 20px; width: 100%;">Registra Esito</button>
      </form>
    </div>
  </div>
</div>
<div class="no-print"><jsp:include page="footer.jsp"/></div>
</body>
</html>