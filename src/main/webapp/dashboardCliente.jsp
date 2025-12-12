<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Dashboard Cliente - MotorsRent</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<jsp:include page="navbarCliente.jsp"/>

<h2>Benvenuto, ${sessionScope.utente.nome}</h2>

<hr>

<h3>I tuoi Preventivi</h3>

<table class="table">
    <tr>
        <th>ID</th>
        <th>Auto</th>
        <th>Data</th>
        <th>Stato</th>
    </tr>
    <c:forEach var="p" items="${preventivi}">
        <tr>
            <td>${p.idPreventivo}</td>
            <td>${p.auto.marca} ${p.auto.modello}</td>
            <td>${p.dataPreventivo}</td>
            <td>${p.stato}</td>
        </tr>
    </c:forEach>
</table>

<h3>Le tue Richieste Leasing</h3>

<table class="table">
    <tr>
        <th>ID</th>
        <th>Auto</th>
        <th>Durata</th>
        <th>Stato</th>
    </tr>
    <c:forEach var="l" items="${leasing}">
        <tr>
            <td>${l.idLeasing}</td>
            <td>${l.auto.marca} ${l.auto.modello}</td>
            <td>${l.durataMesi} mesi</td>
            <td>${l.stato}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>