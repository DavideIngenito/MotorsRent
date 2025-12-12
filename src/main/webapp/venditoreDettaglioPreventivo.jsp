
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Dettaglio Preventivo - MotorsRent</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

<jsp:include page="navbarVenditore.jsp"/>

<div class="container">
    <h1>Dettaglio Preventivo #${preventivo.idPreventivo}</h1>

    <section class="detail-section">
        <h2>Dati Cliente</h2>
        <p><b>Nome:</b> ${preventivo.cliente.nome} ${preventivo.cliente.cognome}</p>
        <p><b>Email:</b> ${preventivo.cliente.email}</p>
    </section>

    <section class="detail-section">
        <h2>Veicolo Richiesto</h2>
        <p><b>Marca:</b> ${preventivo.auto.marca}</p>
        <p><b>Modello:</b> ${preventivo.auto.modello}</p>
        <p><b>Prezzo:</b> € ${preventivo.auto.prezzo}</p>
    </section>

    <section class="detail-section">
        <h2>Note Cliente</h2>
        <p>${preventivo.note}</p>
    </section>

    <section class="form-section">
        <h2>Risposta Preventivo</h2>

        <form action="inviaPreventivo" method="post">
            <input type="hidden" name="idPreventivo" value="${preventivo.idPreventivo}">

            <label>Importo Offerto (€):</label>
            <input type="number" name="importo" required>

            <label>Messaggio al Cliente:</label>
            <textarea name="messaggio" required></textarea>

            <label>Stato:</label>
            <select name="stato">
                <option value="Inviato">Inviato</option>
                <option value="Rifiutato">Rifiutato</option>
            </select>

            <button type="submit" class="btn">Invia Preventivo</button>
        </form>
    </section>
</div>

</body>
</html>