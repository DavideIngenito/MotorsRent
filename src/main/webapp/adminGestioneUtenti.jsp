
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <title>Gestione Venditori - MotorsRent</title>
  <link rel="stylesheet" href="styles/admin.css">
</head>
<body>

<jsp:include page="/header.jsp"/>

<h1>Gestione Venditori</h1>

<h2>Aggiungi Nuovo Venditore</h2>

<form action="AdminUtentiController?action=addVenditore" method="post">

  <label>Nome:</label>
  <input type="text" name="nome" required>

  <label>Cognome:</label>
  <input type="text" name="cognome" required>

  <label>Email:</label>
  <input type="email" name="email" required>

  <label>Password:</label>
  <input type="password" name="password" required>

  <button type="submit" class="btn">Crea Venditore</button>
</form>

<h2>Venditori Attivi</h2>

<table class="table">
  <thead>
  <tr>
    <th>Nome</th>
    <th>Email</th>
    <th>Azioni</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="vend" items="${listaVenditori}">
    <tr>
      <td>${vend.nome} ${vend.cognome}</td>
      <td>${vend.email}</td>
      <td>
        <a class="btn-small delete"
           href="AdminUtentiController?action=deleteVenditore&id=${vend.idUtente}"
           onclick="return confirm('Eliminare questo venditore?');">
          Rimuovi
        </a>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>

</body>
</html>