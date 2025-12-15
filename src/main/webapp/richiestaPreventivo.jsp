<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<html>
<head>
    <title>Richiesta Preventivo - MotorsRent</title>
    <link rel="stylesheet" href="css/style.css">

    <style>
        /* Stile specifico per replicare il Mockup */
        .preventivo-container {
            max-width: 800px;
            margin: 40px auto;
            padding: 20px;
            font-family: 'Arial', sans-serif;
        }

        .preventivo-title {
            font-size: 2.5rem;
            color: #333;
            margin-bottom: 10px;
            font-weight: normal; /* Il mockup ha un font sottile */
        }

        .preventivo-subtitle {
            font-size: 1.1rem;
            color: #666;
            margin-bottom: 30px;
            line-height: 1.5;
        }

        .input-group {
            margin-bottom: 20px;
        }

        /* Stile delle label come da mockup (dentro o sopra il box) */
        .custom-label {
            display: block;
            font-weight: bold;
            margin-bottom: 8px;
            color: #333;
            font-size: 0.9rem;
        }

        /* Input box grigio chiaro/bianco con bordo sottile */
        .custom-input {
            width: 100%;
            padding: 15px;
            border: 1px solid #ccc;
            background-color: #f9f9f9; /* Leggermente grigio come mockup */
            font-size: 1rem;
            border-radius: 4px;
            box-sizing: border-box; /* Assicura che il padding non sballi la larghezza */
        }

        .custom-input:focus {
            outline: none;
            border-color: #555;
            background-color: #fff;
        }

        /* Sezione Note (necessaria per la tua Servlet, anche se non nel mockup) */
        .note-section {
            margin-top: 30px;
            padding-top: 20px;
            border-top: 1px solid #eee;
        }
    </style>
</head>

<body>

<jsp:include page="header.jsp"/>

<div class="preventivo-container">

    <h1 class="preventivo-title">Dettagli Contatto</h1>

    <p class="preventivo-subtitle">
        Inserisci i tuoi dati in modo che il Concessionario possa inviarti un'offerta personalizzata
    </p>

    <c:if test="${not empty errore}">
        <div style="background-color: #f8d7da; color: #721c24; padding: 10px; border-radius: 5px; margin-bottom: 20px;">
                ${errore}
        </div>
    </c:if>

    <form action="richiestaPreventivo" method="post">

        <input type="hidden" name="idAuto" value="${param.idAuto != null ? param.idAuto : idAuto}">

        <div class="input-group">
            <label class="custom-label">Nome *</label>
            <input type="text" class="custom-input" value="${sessionScope.utente.nome}" readonly>
        </div>

        <div class="input-group">
            <label class="custom-label">Cognome *</label>
            <input type="text" class="custom-input" value="${sessionScope.utente.cognome}" readonly>
        </div>

        <div class="input-group">
            <label class="custom-label">Telefono cellulare *</label>
            <input type="text" class="custom-input" value="${sessionScope.utente.telefono}" readonly>
        </div>

        <div class="input-group">
            <label class="custom-label">E-mail *</label>
            <input type="email" class="custom-input" value="${sessionScope.utente.email}" readonly>
        </div>

        <div class="note-section">
            <label class="custom-label">Messaggio per il concessionario (Opzionale)</label>
            <textarea name="note" class="custom-input" rows="4" placeholder="Scrivi qui eventuali richieste specifiche..."></textarea>
        </div>

        <div style="margin-top: 30px; text-align: right;">
            <button type="submit" class="btn btn-primary btn-lg" style="padding: 10px 30px;">Invia Richiesta</button>
        </div>

    </form>
</div>

<jsp:include page="footer.jsp"/>

</body>
</html>