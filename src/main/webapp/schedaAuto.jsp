<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>${auto.marca} ${auto.modello} - MotorsRent</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

<jsp:include page="header.jsp" />

<div class="container">

    <div class="scheda-auto" style="display: flex; gap: 20px; margin-top: 20px;">

        <div class="auto-img-box" style="flex: 1;">
            <img class="auto-img" src="${auto.immagine}" alt="${auto.modello}" style="width: 100%; border-radius: 8px;">
        </div>

        <div class="auto-info" style="flex: 1;">
            <h1>${auto.marca} ${auto.modello}</h1>
            <h3 style="color: #2c3e50;">€ ${auto.prezzo}</h3>

            <ul style="list-style: none; padding: 0; margin-top: 20px;">
                <li><b>Anno:</b> ${auto.anno}</li>
                <li><b>Chilometraggio:</b> ${auto.chilometraggio} km</li>
                <li><b>Stato:</b> ${auto.stato}</li>
                <li><b>Disponibilità:</b>
                    <c:choose>
                        <c:when test="${auto.disponibilita}">
                            <span style="color: green; font-weight: bold;">Disponibile</span>
                        </c:when>
                        <c:otherwise>
                            <span style="color: red; font-weight: bold;">Non Disponibile</span>
                        </c:otherwise>
                    </c:choose>
                </li>
            </ul>

            <p style="margin-top: 20px;"><b>Descrizione:</b><br> ${auto.descrizione}</p>
        </div>
    </div>

    <hr style="margin: 40px 0;">

    <div class="actions" style="text-align: center;">

        <c:if test="${not empty sessionScope.utente && sessionScope.utente.ruolo == 'CLIENTE'}">
            <h2 style="margin-bottom: 20px;">Sei interessato?</h2>

            <a class="btn" href="richiestaPreventivo?idAuto=${auto.idAuto}">Richiedi Preventivo</a>
            <a class="btn btn-secondary" href="richiestaLeasing?idAuto=${auto.idAuto}" style="background-color: #555;">Richiedi Leasing</a>
        </c:if>

        <c:if test="${empty sessionScope.utente}">
            <div class="login-prompt" style="background-color: #f8f9fa; padding: 20px; border-radius: 8px;">
                <h3>Vuoi richiedere un preventivo?</h3>
                <p>Accedi o registrati per contattare la concessionaria.</p>
                <a class="btn" href="login.jsp">Accedi per continuare</a>
            </div>
        </c:if>

        <c:if test="${sessionScope.utente.ruolo == 'AMMINISTRATORE'}">
            <a class="btn" href="adminModificaAuto.jsp?id=${auto.idAuto}" style="background-color: orange;">Modifica Auto</a>
        </c:if>

    </div>

</div>

<jsp:include page="footer.jsp" />

</body>
</html>