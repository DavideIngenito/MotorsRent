<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <title>Modifica Auto - MotorsRent</title>
  <link rel="stylesheet" href="css/style.css"> </head>
<body>

<jsp:include page="header.jsp"/>

<div class="container" style="margin-top: 30px;">
  <h1>Modifica Auto</h1>

  <form action="AdminAutoController?action=update" method="post">

    <input type="hidden" name="idAuto" value="${auto.idAuto}">

    <div class="form-group">
      <label>Marca:</label>
      <input type="text" name="marca" value="${auto.marca}" required class="form-control">
    </div>

    <div class="form-group">
      <label>Modello:</label>
      <input type="text" name="modello" value="${auto.modello}" required class="form-control">
    </div>

    <div class="form-group">
      <label>Anno:</label>
      <input type="number" name="anno" value="${auto.anno}" required class="form-control">
    </div>

    <div class="form-group">
      <label>Prezzo (€):</label>
      <input type="number" step="0.01" name="prezzo" value="${auto.prezzo}" required class="form-control">
    </div>

    <div class="form-group">
      <label>Chilometraggio:</label>
      <input type="number" name="chilometraggio" value="${auto.chilometraggio}" required class="form-control">
    </div>

    <div class="form-group">
      <label>Descrizione:</label>
      <textarea name="descrizione" class="form-control">${auto.descrizione}</textarea>
    </div>

    <div class="form-group">
      <label>URL immagine:</label>
      <input type="text" name="immagine" value="${auto.immagine}" required class="form-control">
    </div>

    <div class="form-group">
      <label>Condizione:</label>
      <select name="stato" class="form-control">
        <option value="Nuova" ${auto.stato == 'Nuova' ? 'selected' : ''}>Nuova</option>
        <option value="Usata" ${auto.stato == 'Usata' ? 'selected' : ''}>Usata</option>
      </select>
    </div>

    <div class="form-group">
      <label>Disponibilità:</label>
      <select name="disponibilita" class="form-control">
        <option value="1" ${auto.disponibilita ? 'selected' : ''}>Disponibile al noleggio</option>

        <option value="0" ${!auto.disponibilita ? 'selected' : ''}>Attualmente non disponibile</option>
      </select>
    </div>

    <br>
    <button type="submit" class="btn btn-primary">Aggiorna Auto</button>
    <a href="AdminAutoController?action=list" class="btn btn-secondary">Annulla</a>

  </form>
</div>

<jsp:include page="footer.jsp"/>

</body>
</html>