<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <title>Gestione Leasing - MotorsRent</title>
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/dashboardVenditore.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>

<body>

<jsp:include page="header.jsp"/>

<div class="container">

    <div class="dashboard-header">
        <div>
            <h1 class="dashboard-title">Gestione Leasing</h1>
            <p class="dashboard-subtitle">Valuta le pratiche di finanziamento aperte.</p>
        </div>
    </div>

    <c:if test="${not empty listaLeasing}">
        <div class="table-container">
            <table class="vendor-table">
                <thead>
                <tr>
                    <th>Rif.</th>
                    <th>Richiedente</th>
                    <th>Veicolo</th>
                    <th>Durata</th>
                    <th>Anticipo</th>
                    <th>Stato</th>
                    <th style="text-align: right;">Azioni</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="l" items="${listaLeasing}">
                    <tr>
                        <td>
                            <span class="id-cell">#${l.idLeasing}</span>
                        </td>

                        <td>
                            <div class="client-info">
                                <span class="client-name">${l.utente.nome} ${l.utente.cognome}</span>
                                <span class="client-email">${l.utente.email}</span>
                            </div>
                        </td>

                        <td class="auto-info">
                                ${l.auto.marca} ${l.auto.modello}
                        </td>

                        <td style="color: #666; font-weight: 500;">
                                ${l.durataMesi} Mesi
                        </td>
                        <td style="color: #666;">
                            € ${l.anticipo}
                        </td>

                        <td>
                            <span class="status-badge status-${l.stato.toLowerCase()}">
                                    ${l.stato.replace('_', ' ')}
                            </span>
                        </td>

                        <td style="text-align: right;">
                            <a href="gestisciLeasing?id=${l.idLeasing}" class="btn-manage">
                                Gestisci <i class="fa-solid fa-chevron-right" style="font-size: 0.7em;"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>

    <c:if test="${empty listaLeasing}">
        <div class="empty-state">
            <i class="fa-regular fa-folder-open" style="font-size: 3rem; color: #ddd; margin-bottom: 15px;"></i>
            <h3>Nessuna pratica leasing</h3>
            <p>Non ci sono richieste di finanziamento in attesa.</p>
        </div>
    </c:if>

</div>

<jsp:include page="footer.jsp"/>

</body>
</html>