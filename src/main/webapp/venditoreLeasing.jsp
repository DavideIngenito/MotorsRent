<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <title>Gestione Leasing - MotorsRent</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&family=Playfair+Display:wght@700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="css/header.css">

</head>

<body>

<jsp:include page="header.jsp"/>

<div class="container" style="padding: 40px 20px;">
    <h1 class="dashboard-title">Richieste di Leasing</h1>
    <p class="dashboard-subtitle">Valuta le richieste di finanziamento.</p>

    <div class="table-container">
        <table class="premium-table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Cliente</th>
                <th>Auto</th>
                <th>Durata</th>
                <th>Stato</th>
                <th>Azioni</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="l" items="${listaLeasing}">
                <tr>
                    <td>#${l.idLeasing}</td>

                    <td>
                        <strong>${l.utente.nome} ${l.utente.cognome}</strong>
                    </td>

                    <td>${l.auto.marca} ${l.auto.modello}</td>
                    <td>${l.durataMesi} mesi</td>

                    <td>
                         <span class="status-badge
                            ${l.stato == 'APPROVATO' ? 'status-approvato' :
                             (l.stato == 'RIFIUTATO' ? 'status-rifiutato' : 'status-attesa')}">
                                 ${l.stato}
                         </span>
                    </td>

                    <td>
                        <a href="gestisciLeasing?id=${l.idLeasing}" class="btn-view">
                            <i class="fa-regular fa-folder-open"></i> Gestisci
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <c:if test="${empty listaLeasing}">
        <div class="empty-state">Non ci sono richieste di leasing al momento.</div>
    </c:if>

</div>

<jsp:include page="footer.jsp"/>

</body>
</html>