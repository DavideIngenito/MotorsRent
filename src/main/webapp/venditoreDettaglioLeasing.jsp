<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <title>Dettaglio Richiesta Leasing</title>
  <link rel="stylesheet" href="css/style.css">
</head>

<body>

<jsp:include page="header.jsp"/>

<div class="container">

  <h1>Richiesta Leasing #${leasing.idLeasing}</h1>

  <div class="details-grid" style="display: flex; gap: 20px;">

    <section class="detail-section" style="flex: 1;">
      <h2>Cliente</h2>
      <p><b>Nome:</b> ${leasing.cliente.nome} ${leasing.cliente.cognome}</p>
      <p><b>Email:</b> ${leasing.cliente.email}</p>
    </section>

    <section class="detail-section" style="flex: 1;">
      <h2>Veicolo</h2>
      <p><b>Marca:</b> ${leasing.auto.marca}</p>
      <p><b>Modello:</b> ${leasing.auto.modello}</p>
      <p><b>Prezzo Auto:</b> € ${leasing.auto.prezzo}</p>
    </section>

    <section class="detail-section" style="flex: 1;">
      <h2>Parametri Leasing</h2>
      <p><b>Durata:</b> ${leasing.durataMesi} mesi</p>
      <p><b>Anticipo:</b> € ${leasing.anticipo}</p>
      <p><b>Km annui:</b> ${leasing.kmAnnui}</p>
    </section>
  </div>

  <hr>

  <section class="form-section">
    <h2>Valutazione Richiesta</h2>

    <form action="gestisciLeasing" method="post" class="form-box">

      <input type="hidden" name="idLeasing" value="${leasing.idLeasing}">

      <label>Rata mensile proposta (€):</label>
      <input type="number" step="0.01" name="rata" placeholder="Es. 350.00" required />

      <label>Stato:</label>
      <select name="stato">
        <option value="APPROVATO">Approvato</option>
        <option value="RIFIUTATO">Rifiutato</option>
        <option value="IN_VALUTAZIONE" selected>In Valutazione</option>
      </select>

      <label>Messaggio al Cliente (opzionale):</label>
      <textarea name="messaggio" rows="4"></textarea>

      <button type="submit" class="btn">Invia Risposta</button>
    </form>
  </section>

</div>

<jsp:include page="footer.jsp"/>

</body>
</html>