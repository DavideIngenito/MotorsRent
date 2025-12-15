<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>${auto.marca} ${auto.modello} - MotorsRent</title>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>

<jsp:include page="navbarCliente.jsp"/>

<div class="container">
  <h1>${auto.marca} ${auto.modello}</h1>

  <div style="display: flex; gap: 20px;">
    <img src="${auto.immagine}" style="max-width: 500px; border-radius: 10px;">

    <div>
      <p><strong>Anno:</strong> ${auto.anno}</p>
      <p><strong>Km:</strong> ${auto.chilometraggio}</p>
      <p><strong>Condizione:</strong> ${auto.stato}</p>
      <p><strong>Descrizione:</strong> ${auto.descrizione}</p>
      <h3 style="color: #2ecc71;">Prezzo: € ${auto.prezzo}</h3>

      <hr>

      <c:if test="${sessionScope.utente != null}">
        <form action="RichiediPreventivoController" method="post" style="margin-bottom: 10px;">
          <input type="hidden" name="idAuto" value="${auto.idAuto}">
          <textarea name="note" placeholder="Scrivi una nota per il venditore..." required></textarea><br>
          <button type="submit" class="btn">Richiedi Preventivo</button>
        </form>

        <form action="RichiediLeasingController" method="post">
          <input type="hidden" name="idAuto" value="${auto.idAuto}">
          <label>Durata (Mesi):</label>
          <select name="durata">
            <option value="12">12 Mesi</option>
            <option value="24">24 Mesi</option>
            <option value="36">36 Mesi</option>
          </select>
          <button type="submit" class="btn" style="background-color: #3498db;">Richiedi Leasing</button>
        </form>
      </c:if>

      <c:if test="${sessionScope.utente == null}">
        <p><a href="login.jsp">Accedi</a> per richiedere un preventivo.</p>
      </c:if>
    </div>
  </div>
</div>

<jsp:include page="footer.jsp"/>

</body>
</html>