

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
  <title>Catalogo Auto - MotorsRent</title>
  <link rel="stylesheet" href="css/style.css">
</head>

<body>

<jsp:include page="navbarCliente.jsp"/>

<h2>Catalogo Auto</h2>

<form action="CatalogoClienteController" method="get">
  Marca: <input type="text" name="marca">
  Prezzo max: <input type="number" name="prezzoMax">
  <button type="submit">Filtra</button>
</form>

<div class="catalogo-container">
  <c:forEach var="auto" items="${listaAuto}">
    <div class="card">
      <img src="${auto.immagine}" alt="Auto">
      <h3>${auto.marca} ${auto.modello}</h3>
      <p>Prezzo: € ${auto.prezzo}</p>
      <a href="DettaglioAutoClienteController?id=${auto.idAuto}">Dettagli</a>
    </div>
  </c:forEach>
</div>

</body>
</html>