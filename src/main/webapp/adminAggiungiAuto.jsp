
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Aggiungi Auto - MotorsRent</title>
    <link rel="stylesheet" href="styles/admin.css">
</head>
<body>

<jsp:include page="/header.jsp"/>

<h1>Aggiungi Nuova Auto</h1>

<form action="AdminAutoController?action=add" method="post">

    <label>Marca:</label>
    <input type="text" name="marca" required>

    <label>Modello:</label>
    <input type="text" name="modello" required>

    <label>Anno:</label>
    <input type="number" name="anno" required min="1990" max="2025">

    <label>Prezzo (€):</label>
    <input type="number" name="prezzo" required>

    <label>Chilometraggio:</label>
    <input type="number" name="chilometraggio" required>

    <label>Descrizione:</label>
    <textarea name="descrizione"></textarea>

    <label>URL immagine:</label>
    <input type="text" name="immagine" required>

    <label>Disponibilità:</label>
    <select name="disponibilita">
        <option value="1">Disponibile</option>
        <option value="0">Non disponibile</option>
    </select>

    <button type="submit" class="btn">Salva Auto</button>

</form>

</body>
</html>