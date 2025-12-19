<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<html>
<head>
    <title>Statistiche - Admin</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        .stat-grid { display: flex; gap: 20px; flex-wrap: wrap; margin-top: 20px;}
        .stat-card { flex: 1; min-width: 200px; background: #f8f9fa; padding: 20px; border-radius: 8px; text-align: center; border: 1px solid #ddd;}
        .stat-number { font-size: 2.5em; font-weight: bold; color: #007bff; }
    </style>
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="container">
    <h1>Statistiche Piattaforma</h1>
    <p>Panoramica dell'andamento di MotorsRent.</p>

    <div class="stat-grid">
        <div class="stat-card">
            <h3>Totale Auto</h3>
            <div class="stat-number">${numAuto}</div>
            <p>Di cui ${numAutoDisponibili} disponibili</p>
        </div>

        <div class="stat-card">
            <h3>Utenti</h3>
            <div class="stat-number">${numClienti}</div>
            <p>Clienti registrati</p>
        </div>

        <div class="stat-card">
            <h3>Staff</h3>
            <div class="stat-number">${numVenditori}</div>
            <p>Venditori attivi</p>
        </div>

        <div class="stat-card">
            <h3>Attività Commerciale</h3>
            <div class="stat-number" style="color: green;">${numPreventivi + numLeasing}</div>
            <p>Richieste totali (Preventivi + Leasing)</p>
        </div>
    </div>

    <div style="margin-top: 30px;">
        <a href="dashboardAdmin" class="btn btn-secondary">Vai alla Dashboard</a>
    </div>
</div>

<jsp:include page="footer.jsp"/>

</body>
</html>