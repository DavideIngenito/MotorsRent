<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Gestione Leasing - MotorsRent</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

<jsp:include page="header.jsp"/>

<div class="container">
    <h1>Richieste di Leasing</h1>

    <table class="styled-table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Cliente</th>
            <th>Auto</th>
            <th>Durata</th>
            <th>Stato</th>
            <th>Azione</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="l" items="${listaLeasing}">
            <tr>
                <td>${l.idLeasing}</td>

                <td>${l.cliente.nome} ${l.cliente.cognome}</td>
                <td>${l.auto.marca} ${l.auto.modello}</td>

                <td>${l.durataMesi} mesi</td>

                <td>
                    <span class="status ${l.stato}">${l.stato}</span>
                </td>

                <td>
                    <a href="gestisciLeasing?id=${l.idLeasing}" class="btn-small">Gestisci</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:if test="${empty listaLeasing}">
        <p>Non ci sono richieste di leasing al momento.</p>
    </c:if>

</div>

<jsp:include page="footer.jsp"/>

</body>
</html>