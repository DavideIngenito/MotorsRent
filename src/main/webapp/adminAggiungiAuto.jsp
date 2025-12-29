<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <title>Aggiungi Auto - MotorsRent</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&family=Playfair+Display:wght@700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

    <link rel="stylesheet" href="css/adminAggiungiAuto.css">
    <link rel="stylesheet" href="css/main.css">
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="add-container">
    <div class="form-card">

        <div class="form-header">
            <h1 class="form-title">Aggiungi Nuova Auto</h1>
            <p class="form-subtitle">Inserisci i dettagli del veicolo per pubblicarlo nel catalogo.</p>
        </div>

        <form action="AdminAutoController?action=add" method="post">
            <div class="form-grid">

                <div class="form-group">
                    <label>Marca</label>
                    <input type="text" name="marca" class="form-control" placeholder="Es. Audi" required>
                </div>

                <div class="form-group">
                    <label>Modello</label>
                    <input type="text" name="modello" class="form-control" placeholder="Es. A3 Sportback" required>
                </div>

                <div class="form-group">
                    <label>Anno Immatricolazione</label>
                    <input type="number" name="anno" class="form-control" placeholder="2023" required min="1990" max="2025">
                </div>

                <div class="form-group">
                    <label>Prezzo di Listino (€)</label>
                    <input type="number" name="prezzo" class="form-control" placeholder="25000" required step="0.01">
                </div>

                <div class="form-group">
                    <label>Chilometraggio (km)</label>
                    <input type="number" name="chilometraggio" class="form-control" placeholder="0" required>
                </div>

                <div class="form-group">
                    <label>Condizione</label>
                    <select name="stato" class="form-control">
                        <option value="Nuova">Nuova</option>
                        <option value="Usata">Usata</option>
                    </select>
                </div>

                <div class="form-group full-width">
                    <label>URL Immagine</label>
                    <input type="text" name="immagine" class="form-control" placeholder="https://..." required>
                </div>

                <div class="form-group full-width">
                    <label>Descrizione Dettagliata</label>
                    <textarea name="descrizione" class="form-control" placeholder="Inserisci caratteristiche, optional e dettagli..."></textarea>
                </div>

                <div class="form-group">
                    <label>Stato Disponibilità</label>
                    <select name="disponibilita" class="form-control">
                        <option value="1">Disponibile</option>
                        <option value="0">Non Disponibile</option>
                    </select>
                </div>

            </div>

            <div class="form-actions">
                <a href="adminGestioneAuto.jsp" class="btn btn-secondary">Annulla</a>
                <button type="submit" class="btn btn-primary">
                    <i class="fa-solid fa-plus"></i> Pubblica Auto
                </button>
            </div>
        </form>
    </div>
</div>

<jsp:include page="footer.jsp"/>

</body>
</html>