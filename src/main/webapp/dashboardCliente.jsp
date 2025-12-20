<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Dashboard Cliente - MotorsRent</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&family=Playfair+Display:wght@700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/dashboardCliente.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

</head>
<jsp:include page="header.jsp"/>

<div class="container" style="padding: 40px 20px;">

    <h2 style="font-family: 'Playfair Display', serif; font-size: 2rem;">Benvenuto, ${sessionScope.utente.nome}</h2>
    <p style="color: #919191; margin-bottom: 30px;">Qui puoi gestire le tue richieste.</p>

    <h3 style="margin-bottom: 15px;">I tuoi Preventivi</h3>

    <c:if test="${empty listaPreventivi}">
        <div style="padding: 20px; background: #f9f9f9; border-radius: 8px;">Non hai ancora richiesto preventivi.</div>
    </c:if>

    <c:if test="${not empty listaPreventivi}">
        <table class="table">
            <tr>
                <th>Rif.</th>
                <th>Auto</th>
                <th>Data</th>
                <th>Stato</th>
                <th>Azioni</th> </tr>
            <c:forEach var="p" items="${listaPreventivi}">
                <tr>
                    <td>#${p.idPreventivo}</td>
                    <td><strong>${p.auto.marca} ${p.auto.modello}</strong></td>
                    <td>${p.dataRichiesta}</td>
                    <td>
                        <span style="color: ${p.stato == 'APPROVATO' ? 'green' : (p.stato == 'RIFIUTATO' ? 'red' : 'orange')}">
                                ${p.stato}
                        </span>
                    </td>
                    <td>
                        <a href="dettaglioRichiesta?tipo=preventivo&id=${p.idPreventivo}" class="btn-view">
                            <i class="fa-regular fa-eye"></i> Visualizza
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <h3 style="margin-top: 50px; margin-bottom: 15px;">Le tue Richieste Leasing</h3>

    <c:if test="${empty listaLeasing}">
        <div style="padding: 20px; background: #f9f9f9; border-radius: 8px;">Non hai richieste di leasing attive.</div>
    </c:if>

    <c:if test="${not empty listaLeasing}">
        <table class="table">
            <tr>
                <th>Rif.</th>
                <th>Auto</th>
                <th>Durata</th>
                <th>Anticipo</th>
                <th>Stato</th>
                <th>Azioni</th> </tr>
            <c:forEach var="l" items="${listaLeasing}">
                <tr>
                    <td>#${l.idLeasing}</td>
                    <td><strong>${l.auto.marca} ${l.auto.modello}</strong></td>
                    <td>${l.durataMesi} mesi</td>
                    <td>€ ${l.anticipo}</td>
                    <td>
                         <span style="color: ${l.stato == 'APPROVATO' ? 'green' : (l.stato == 'RIFIUTATO' ? 'red' : 'orange')}">
                                 ${l.stato}
                         </span>
                    </td>
                    <td>
                        <a href="dettaglioRichiesta?tipo=leasing&id=${l.idLeasing}" class="btn-view">
                            <i class="fa-regular fa-eye"></i> Visualizza
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

</div>

<jsp:include page="footer.jsp"/>

</body>
</html>