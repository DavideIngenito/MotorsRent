
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>MotorsRent - Dashboard Amministratore</title>
    <link rel="stylesheet" href="styles/admin.css">
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
            <a href="AdminAutoController?action=list" class="btn">Apri</a>
        </div>

        <div class="card">
            <h3>Gestione Venditori</h3>
            <p>Aggiungi nuovi venditori o gestisci quelli esistenti.</p>
            <a href="AdminUtentiController?action=listVenditori" class="btn">Apri</a>
        </div>

        <div class="card">
            <h3>Statistiche</h3>
            <p>Visualizza analisi di utilizzo del sistema.</p>
            <a href="#" class="btn disabled">Disponibile se implementato</a>
        </div>

    </div>
</div>

</body>
</html>