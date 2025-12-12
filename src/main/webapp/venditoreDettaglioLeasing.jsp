

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <title>Dettaglio Richiesta Leasing</title>
  <link rel="stylesheet" href="css/style.css">
</head>

<body>

<jsp:include page="navbarVenditore.jsp"/>

<div class="container">

  <h1>Richiesta Leasing #${leasing.idLeasing}</h1>

  <section class="detail-section">
    <h2>Cliente</h2>
    <p><b>Nome:</b> ${leasing.cliente.nome} ${leasing.cliente.cognome}</p>
    <p><b>Email:</b> ${leasing.cliente.email}</p>
  </section>

  <section class="detail-section">
    <h2>Veicolo</h2>
    <p><b>Marca:</b> ${leasing.auto.marca}</p>
    <p><b>Modello:</b> ${leasing.auto.modello}</p>
    <p><b>Prezzo:</b> € ${leasing.auto.prezzo}</p>
  </section>

  <section class="detail-section">
    <h2>Parametri Leasing</h2>
    <p><b>Durata:</b> ${leasing.durataMesi} mesi</p>
    <p><b>Anticipo:</b> € ${leasing.anticipo}</p>
    <p><b>Km annui:</b> ${leasing.kmAnnui}</p>
  </section>

  <section class="form-section">
    <h2>Valutazione Richiesta</h2>

    <form action="inviaLeasing" method="post">
      <input type="hidden" name="idLeasing" value="${leasing.idLeasing}">

      <label>Rata mensile definitiva (€):</label>
      <input type="number" step="0.01" name="rata" required />

      <label>Stato:</label>
      <select name="stato">
        <option value="Approvato">Approvato</option>
        <option value="Rifiutato">Rifiutato</option>
      </select>

      <label>Messaggio al Cliente:</label>
      <textarea name="messaggio" required></textarea>

      <button type="submit" class="btn">Invia Risposta</button>
    </form>
  </section>

</div>

</body>

</html>