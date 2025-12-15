
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <title>Gestione Preventivi - MotorsRent</title>
  <link rel="stylesheet" href="css/style.css">
</head>

<body>

<jsp:include page="header.jsp"/>

<div class="container">
  <h1>Richieste di Preventivo</h1>

  <table class="styled-table">
    <thead>
    <tr>
      <th>ID</th>
      <th>Cliente</th>
      <th>Auto</th>
      <th>Data</th>
      <th>Stato</th>
      <th>Apri</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="p" items="${listaPreventivi}">
      <tr>
        <td>${p.idPreventivo}</td>

        <td>${p.cliente.nome} ${p.cliente.cognome}</td>

        <td>${p.auto.marca} ${p.auto.modello}</td>

        <td>${p.dataRichiesta}</td>
        <td>${p.stato}</td>
        <td>
          <a href="gestisciPreventivo?id=${p.idPreventivo}" class="btn-small">Apri</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

</body>
</html>