
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Richiesta Leasing</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

<jsp:include page="navbarCliente.jsp"/>

<h2>Richiesta Leasing</h2>

<form action="RichiestaLeasingController" method="post">

    <input type="hidden" name="idAuto" value="${param.idAuto}">

    <label>Anticipo (€):</label>
    <input type="number" name="anticipo" required>

    <label>Durata (mesi):</label>
    <input type="number" name="durata" required>

    <label>Km annui:</label>
    <input type="number" name="kmAnnui" required>

    <button type="submit">Invia Richiesta Leasing</button>
</form>

</body>
</html>