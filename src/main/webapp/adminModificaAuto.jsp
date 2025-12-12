
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <title>Modifica Auto - MotorsRent</title>
  <link rel="stylesheet" href="styles/admin.css">
</head>
<body>

<jsp:include page="/header.jsp"/>

<h1>Modifica Auto</h1>

<form action="AdminAutoController?action=update" method="post">

  <input type="hidden" name="idAuto" value="${auto.idAuto}">

  <label>Marca:</label>
  <input type="text" name="marca" value="${auto.marca}" required>

  <label>Modello:</label>
  <input type="text" name="modello" value="${auto.modello}" required>

  <label>Anno:</label>
  <input type="number" name="anno" value="${auto.anno}" required>

  <label>Prezzo (€):</label>
  <input type="number" name="prezzo" value="${auto.prezzo}" required>

  <label>Chilometraggio:</label>
  <input type="number" name="chilometraggio" value="${auto.chilometraggio}" required>

  <label>Descrizione:</label>
  <textarea name="descrizione">${auto.descrizione}</textarea>

  <label>URL immagine:</label>
  <input type="text" name="immagine" value="${auto.immagine}" required>

  <label>Disponibilità:</label>
  <select name="disponibilita">
    <option value="1" ${auto.disponibilita==1?'selected':''}>Disponibile</option>
    <option value="0" ${auto.disponibilita==0?'selected':''}>Non disponibile</option>
  </select>

  <button type="submit" class="btn">Aggiorna Auto</button>

</form>

</body>
</html>