
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <title>Dashboard Venditore - MotorsRent</title>
  <link rel="stylesheet" href="css/style.css">
</head>

<body>

<jsp:include page="navbarVenditore.jsp"/>

<div class="container">
  <h1>Benvenuto, ${venditore.nome} ${venditore.cognome}</h1>

  <section class="dashboard-section">
    <h2>Riepilogo richieste</h2>

    <div class="dashboard-cards">

      <div class="card">
        <h3>Preventivi in attesa</h3>
        <p class="num">${numPreventiviInAttesa}</p>
        <a href="venditorePreventivi" class="btn">Gestisci Preventivi</a>
      </div>

      <div class="card">
        <h3>Richieste di Leasing in attesa</h3>
        <p class="num">${numLeasingInAttesa}</p>
        <a href="venditoreLeasing" class="btn">Gestisci Leasing</a>
      </div>

    </div>

  </section>
</div>

</body>
</html>