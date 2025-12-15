<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Dashboard Cliente - MotorsRent</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        .table { width: 100%; border-collapse: collapse; margin-bottom: 30px; }
        .table th, .table td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        .table th { background-color: #f2f2f2; }
    </style>
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="container" style="padding: 20px;">

    <h2>Benvenuto, ${sessionScope.utente.nome}</h2>
    <hr>

    <h3>I tuoi Preventivi</h3>

    <c:if test="${empty listaPreventivi}">
        <p>Non hai ancora richiesto preventivi.</p>
    </c:if>

    <c:if test="${not empty listaPreventivi}">
        <table class="table">
            <tr>
                <th>ID</th>
                <th>Auto</th>
                <th>Data</th>
                <th>Note</th>
                <th>Stato</th>
            </tr>
            <c:forEach var="p" items="${listaPreventivi}">
                <tr>
                    <td>${p.idPreventivo}</td>
                    <td>${p.auto.marca} ${p.auto.modello}</td>
                    <td>${p.dataRichiesta}</td>
                    <td>${p.note}</td>
                    <td><b>${p.stato}</b></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <h3>Le tue Richieste Leasing</h3>

    <c:if test="${empty listaLeasing}">
        <p>Non hai richieste di leasing attive.</p>
    </c:if>

    <c:if test="${not empty listaLeasing}">
        <table class="table">
            <tr>
                <th>ID</th>
                <th>Auto</th>
                <th>Durata</th>
                <th>Anticipo</th>
                <th>Stato</th>
            </tr>
            <c:forEach var="l" items="${listaLeasing}">
                <tr>
                    <td>${l.idLeasing}</td>
                    <td>${l.auto.marca} ${l.auto.modello}</td>
                    <td>${l.durataMesi} mesi</td>
                    <td>€ ${l.anticipo}</td>
                    <td><b>${l.stato}</b></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

</div>

<jsp:include page="footer.jsp"/>

</body>
</html>