<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <title>Statistiche - Admin MotorsRent</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&family=Playfair+Display:wght@700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

    <link rel="stylesheet" href="css/adminStatistiche.css"> <link rel="stylesheet" href="css/adminStatistiche.css"> </head>
<body>

<jsp:include page="header.jsp"/>

<div class="container" style="margin-top: 40px;">

    <div class="page-header">
        <h1>Statistiche Piattaforma</h1>
        <p>Panoramica dell'andamento e delle risorse di MotorsRent.</p>
    </div>

    <div class="stat-grid">

        <div class="stat-card">
            <div class="icon-wrapper">
                <i class="fa-solid fa-car-side"></i>
            </div>
            <h3>Totale Auto</h3>
            <div class="stat-number">${numAuto}</div>
            <p class="stat-desc">
                <i class="fa-solid fa-check-circle" style="color: #2e7d32; margin-right: 5px;"></i>
                ${numAutoDisponibili} disponibili
            </p>
        </div>

        <div class="stat-card">
            <div class="icon-wrapper">
                <i class="fa-solid fa-users"></i>
            </div>
            <h3>Clienti</h3>
            <div class="stat-number">${numClienti}</div>
            <p class="stat-desc">Utenti registrati</p>
        </div>

        <div class="stat-card">
            <div class="icon-wrapper">
                <i class="fa-solid fa-user-tie"></i>
            </div>
            <h3>Staff</h3>
            <div class="stat-number">${numVenditori}</div>
            <p class="stat-desc">Venditori attivi</p>
        </div>

        <div class="stat-card">
            <div class="icon-wrapper">
                <i class="fa-solid fa-chart-line"></i>
            </div>
            <h3>Attività Commerciale</h3>
            <div class="stat-number text-success">${numPreventivi + numLeasing}</div>
            <p class="stat-desc">Richieste totali</p>
        </div>

    </div>

    <div style="margin-top: 50px; text-align: center;">
        <a href="dashboardAdmin" class="btn btn-secondary">
            <i class="fa-solid fa-arrow-left"></i> Torna alla Dashboard
        </a>
    </div>
</div>

<jsp:include page="footer.jsp"/>

</body>
</html>