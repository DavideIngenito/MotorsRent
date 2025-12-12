
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Richiesta Preventivo</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

<jsp:include page="navbarCliente.jsp"/>

<h2>Richiedi un Preventivo</h2>

<form action="RichiestaPreventivoController" method="post">

    <input type="hidden" name="idAuto" value="${param.idAuto}">

    <label>Note aggiuntive:</label><br>
    <textarea name="note" rows="5"></textarea>

    <br><br>
    <button type="submit">Invia Richiesta</button>
</form>

</body>
</html>