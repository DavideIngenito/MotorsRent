<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="it">
<head>
  <title>Gestione Preventivi - MotorsRent</title>
  <link rel="stylesheet" href="css/header.css">
  <link rel="stylesheet" href="css/dashboardVenditore.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>

<body>

<jsp:include page="header.jsp"/>

<div class="container">

  <div class="dashboard-header">
    <div>
      <h1 class="dashboard-title">Gestione Preventivi</h1>
      <p class="dashboard-subtitle">Visualizza e gestisci le nuove richieste di preventivo.</p>
    </div>
  </div>

  <c:if test="${not empty listaPreventivi}">
    <div class="table-container">
      <table class="vendor-table">
        <thead>
        <tr>
          <th>Rif.</th>
          <th>Cliente</th>
          <th>Veicolo Richiesto</th>
          <th>Data Richiesta</th>
          <th>Stato Attuale</th>
          <th style="text-align: right;">Azioni</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="p" items="${listaPreventivi}">
          <tr>
            <td>
              <span class="id-cell">#${p.idPreventivo}</span>
            </td>

            <td>
              <div class="client-info">
                <span class="client-name">${p.utente.nome} ${p.utente.cognome}</span>
                <span class="client-email">${p.utente.email}</span>
              </div>
            </td>

            <td class="auto-info">
                ${p.auto.marca} ${p.auto.modello}
            </td>

            <td style="color: #666; font-size: 0.9rem;">
                ${p.dataRichiesta}
            </td>

            <td>
                            <span class="status-badge status-${p.stato.toLowerCase()}">
                                ${p.stato.replace('_', ' ')}
                            </span>
            </td>

            <td style="text-align: right;">
              <a href="gestisciPreventivo?id=${p.idPreventivo}" class="btn-manage">
                Gestisci <i class="fa-solid fa-chevron-right" style="font-size: 0.7em;"></i>
              </a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </c:if>

  <c:if test="${empty listaPreventivi}">
    <div class="empty-state">
      <i class="fa-regular fa-folder-open" style="font-size: 3rem; color: #ddd; margin-bottom: 15px;"></i>
      <h3>Nessuna richiesta da gestire</h3>
      <p>Al momento non ci sono preventivi in attesa di lavorazione.</p>
    </div>
  </c:if>

</div>

<jsp:include page="footer.jsp"/>

</body>
</html>