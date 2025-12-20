<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="it">
<head>
  <title>Gestione Preventivi - MotorsRent</title>
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&family=Playfair+Display:wght@700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
  <link rel="stylesheet" href="css/header.css">
  </head>

<body>

<jsp:include page="header.jsp"/>

<div class="container" style="padding: 40px 20px;">
  <h1 class="dashboard-title">Richieste di Preventivo</h1>
  <p class="dashboard-subtitle">Gestisci le offerte per i clienti.</p>

  <div class="table-container">
    <table class="premium-table">
      <thead>
      <tr>
        <th>ID</th>
        <th>Cliente</th>
        <th>Auto</th>
        <th>Data</th>
        <th>Stato</th>
        <th>Azioni</th>
      </tr>
      </thead>

      <tbody>
      <c:forEach var="p" items="${listaPreventivi}">
        <tr>
          <td>#${p.idPreventivo}</td>

          <td>
            <strong>${p.utente.nome} ${p.utente.cognome}</strong><br>
            <span style="font-size:0.8rem; color:#888;">${p.utente.email}</span>
          </td>

          <td>${p.auto.marca} ${p.auto.modello}</td>
          <td>${p.dataRichiesta}</td>

          <td>
                        <span class="status-badge
                            ${p.stato == 'APPROVATO' ? 'status-approvato' :
                             (p.stato == 'RIFIUTATO' ? 'status-rifiutato' : 'status-attesa')}">
                            ${p.stato}
                        </span>
          </td>

          <td>
            <a href="gestisciPreventivo?id=${p.idPreventivo}" class="btn-view">
              <i class="fa-regular fa-folder-open"></i> Gestisci
            </a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>

  <c:if test="${empty listaPreventivi}">
    <div class="empty-state">Non ci sono richieste di preventivo al momento.</div>
  </c:if>
</div>

<jsp:include page="footer.jsp"/>

</body>
</html>