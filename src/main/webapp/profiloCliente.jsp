profiloCliente
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Profilo Cliente</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

<jsp:include page="navbarCliente.jsp"/>

<h2>Profilo Utente</h2>

<form action="ModificaProfiloController" method="post">

    Nome: <input type="text" value="${utente.nome}" name="nome"><br>
    Cognome: <input type="text" value="${utente.cognome}" name="cognome"><br>
    Email: <input type="email" value="${utente.email}" name="email"><br>
    Telefono: <input type="text" value="${utente.telefono}" name="telefono"><br>

    <button type="submit">Aggiorna Profilo</button>
</form>

</body>
</html>