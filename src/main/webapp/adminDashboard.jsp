
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>MotorsRent - Dashboard Amministratore</title>
    <link rel="stylesheet" href="css/adminDashboard.css">
</head>
<body>

<jsp:include page="/header.jsp"/>

<div class="dashboard-container">
    <h1>Area Amministratore</h1>
    <p>Benvenuto, <strong>${sessionScope.admin.nome}</strong></p>

    <div class="admin-cards">

        <div class="card">
            <h3>Gestione Auto</h3>
            <p>Visualizza, aggiungi, modifica o rimuovi veicoli dal catalogo.</p>
            <a href="AdminAutoController?action=list" class="btn">Gestisci Auto</a>
        </div>

        <div class="card">
            <h3>Gestione Venditori</h3>
            <p>Aggiungi nuovi venditori o gestisci quelli esistenti.</p>
            <a href="AdminUtentiController?action=listVenditori" class="btn">Gestisci Venditori</a>
        </div>

        <div class="card">
            <h3>Statistiche</h3>
            <p>Visualizza analisi di utilizzo del sistema.</p>
            <a href="AdminStatisticheServlet" class="btn" style="background-color: #6f42c1;">Visualizza Report</a>
        </div>

    </div>

</div>

<jsp:include page="footer.jsp" />

</body>
</html>