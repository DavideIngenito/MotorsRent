<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Modifica Auto - MotorsRent</title>
  <link rel="stylesheet" href="css/adminModificaAuto.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="edit-container">

  <div class="form-card">
    <h1 class="form-title">Modifica Auto</h1>
    <p class="form-subtitle">Aggiorna i dettagli del veicolo ID: <strong>${auto.idAuto}</strong></p>

    <form action="AdminAutoController?action=update" method="post">
      <input type="hidden" name="idAuto" value="${auto.idAuto}">

      <div class="form-grid">

        <div class="form-group">
          <label>Marca</label>
          <input type="text" name="marca" value="${auto.marca}" required class="form-control" placeholder="Es. Audi">
        </div>

        <div class="form-group">
          <label>Modello</label>
          <input type="text" name="modello" value="${auto.modello}" required class="form-control" placeholder="Es. A3 Sportback">
        </div>

        <div class="form-group">
          <label>Anno Immatricolazione</label>
          <input type="number" name="anno" value="${auto.anno}" required class="form-control">
        </div>

        <div class="form-group">
          <label>Prezzo (€)</label>
          <input type="number" step="0.01" name="prezzo" value="${auto.prezzo}" required class="form-control">
        </div>

        <div class="form-group">
          <label>Chilometraggio (Km)</label>
          <input type="number" name="chilometraggio" value="${auto.chilometraggio}" required class="form-control">
        </div>

        <div class="form-group">
          <label>Condizione</label>
          <select name="stato" class="form-control">
            <option value="Nuova" ${auto.stato == 'Nuova' ? 'selected' : ''}>Nuova</option>
            <option value="Usata" ${auto.stato == 'Usata' ? 'selected' : ''}>Usata</option>
          </select>
        </div>

        <div class="form-group">
          <label>Stato Disponibilità</label>
          <select name="disponibilita" class="form-control">
            <option value="1" ${auto.disponibilita ? 'selected' : ''}>Disponibile in catalogo</option>
            <option value="0" ${!auto.disponibilita ? 'selected' : ''}>Non disponibile / Venduta</option>
          </select>
        </div>

        <div></div>

        <div class="form-group full-width">
          <label>URL Immagine</label>
          <input type="text" name="immagine" value="${auto.immagine}" required class="form-control" placeholder="https://...">
        </div>

        <div class="form-group full-width">
          <label>Descrizione Dettagliata</label>
          <textarea name="descrizione" class="form-control" placeholder="Inserisci dettagli, optional, ecc...">${auto.descrizione}</textarea>
        </div>

      </div>
      <div class="form-actions">
        <button type="submit" class="btn btn-primary">
          <i class="fa-solid fa-save"></i> Salva Modifiche
        </button>
        <a href="AdminAutoController?action=list" class="btn btn-secondary">Annulla</a>
      </div>

    </form>
  </div>
</div>

<jsp:include page="footer.jsp"/>

</body>
</html>