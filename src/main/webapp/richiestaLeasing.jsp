<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <title>Richiesta Leasing - MotorsRent</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&family=Playfair+Display:wght@700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">

    <style>
        .form-container { max-width: 600px; margin: 50px auto; padding: 30px; background: #fff; border-radius: 8px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); }
        .form-group { margin-bottom: 20px; }
        .form-group label { display: block; margin-bottom: 8px; font-weight: 600; }
        .form-group input, .form-group select, .form-group textarea { width: 100%; padding: 12px; border: 1px solid #ccc; border-radius: 4px; font-family: 'Inter', sans-serif; }
        .btn-submit { width: 100%; padding: 15px; background: #121212; color: #fff; border: none; font-weight: bold; cursor: pointer; border-radius: 4px; }
        .btn-submit:hover { background: #333; }
        .error-box { color: red; margin-bottom: 15px; text-align: center; }
    </style>
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="container">
    <div class="form-container">
        <h2 style="text-align:center; font-family:'Playfair Display'; margin-bottom:30px;">Richiesta Leasing</h2>

        <c:if test="${not empty errore}">
            <div class="error-box">${errore}</div>
        </c:if>

        <form action="richiestaLeasing" method="post">
            <input type="hidden" name="idAuto" value="${param.idAuto != null ? param.idAuto : idAuto}">

            <div class="form-group">
                <label for="durata">Durata Contratto (Mesi)</label>
                <select id="durata" name="durata" required>
                    <option value="24">24 Mesi</option>
                    <option value="36">36 Mesi</option>
                    <option value="48">48 Mesi</option>
                    <option value="60">60 Mesi</option>
                </select>
            </div>

            <div class="form-group">
                <label for="anticipo">Anticipo (€)</label>
                <input type="number" id="anticipo" name="anticipo" min="0" step="100" required
                       title="Inserisci un valore positivo">
            </div>

            <div class="form-group">
                <label for="kmAnnui">Km annui previsti</label>
                <input type="number" id="kmAnnui" name="kmAnnui" min="1" step="1000" required>
            </div>

            <div class="form-group">
                <label for="note">Note aggiuntive (Opzionale)</label>
                <textarea id="note" name="note" rows="4" placeholder="Es. Esigenze particolari..."
                          oninput="this.value = this.value.replace(/[^a-zA-Z0-9\s.,!?'-]/g, '')"></textarea>
            </div>

            <button type="submit" class="btn-submit">Invia Richiesta Leasing</button>
        </form>
    </div>
</div>

<jsp:include page="footer.jsp"/>

</body>
</html>