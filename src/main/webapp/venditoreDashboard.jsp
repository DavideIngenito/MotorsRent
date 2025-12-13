<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <title>Dashboard Venditore - MotorsRent</title>
  <link rel="stylesheet" href="css/style.css">
</head>

<body>

<jsp:include page="header.jsp"/>

<div class="container">
  <h1>Benvenuto, ${sessionScope.utente.nome} ${sessionScope.utente.cognome}</h1>
  <p class="role-badge">Pannello Venditore</p>

  <section class="dashboard-section" style="margin-top: 30px;">
    <h2>Riepilogo richieste</h2>

    <div class="dashboard-cards" style="display: flex; gap: 20px;">

      <div class="card" style="border: 1px solid #ddd; padding: 20px; border-radius: 8px; flex: 1;">
        <h3>Preventivi in attesa</h3>

        <p class="num" style="font-size: 2em; font-weight: bold; color: #e67e22;">
          ${numPreventiviInAttesa}
        </p>

        <a href="venditorePreventivi" class="btn">Visualizza Lista</a>
      </div>

      <div class="card" style="border: 1px solid #ddd; padding: 20px; border-radius: 8px; flex: 1;">
        <h3>Richieste di Leasing</h3>

        <p class="num" style="font-size: 2em; font-weight: bold; color: #3498db;">
          ${numLeasingInAttesa}
        </p>

        <a href="venditoreLeasing" class="btn">Gestisci Leasing</a>
      </div>

    </div>

  </section>
</div>

<jsp:include page="footer.jsp"/>

</body>
</html>