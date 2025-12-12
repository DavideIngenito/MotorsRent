
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Gestione Leasing - MotorsRent</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

<jsp:include page="navbarVenditore.jsp"/>

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
            <th>Apri</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="l" items="${listaLeasing}">
            <tr>
                <td>${l.idLeasing}</td>
                <td>${l.clienteNome} ${l.clienteCognome}</td>
                <td>${l.autoMarca} ${l.autoModello}</td>
                <td>${l.durataMesi} mesi</td>
                <td>${l.stato}</td>
                <td>
                    <a href="venditoreDettaglioLeasing?id=${l.idLeasing}" class="btn-small">Apri</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>

</body>
</html>