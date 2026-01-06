<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <title>Gestione Venditori - MotorsRent</title>
  <link rel="stylesheet" href="css/adminGestioneUtenti.css">
</head>

<body>

<jsp:include page="/header.jsp"/>

<div class="admin-users-page">
  <div class="admin-users-container">

    <!-- ===== HEADER ===== -->
    <div class="admin-users-header">
      <h1>Gestione Venditori</h1>
      <p>Gestisci i venditori attivi del sistema</p>
    </div>

    <section class="admin-users-section">
      <h2>Aggiungi Nuovo Venditore</h2>

      <form
              action="AdminUtentiController?action=addVenditore"
              method="post"
              class="admin-users-form"
      >

        <div class="admin-users-field">
          <label>Nome</label>
          <input type="text" name="nome" required>
        </div>

        <div class="admin-users-field">
          <label>Cognome</label>
          <input type="text" name="cognome" required>
        </div>

        <div class="admin-users-field">
          <label>Email</label>
          <input type="email" name="email" required>
        </div>

        <div class="admin-users-field">
          <label>Password</label>
          <input type="password" name="password" required>
        </div>

        <button type="submit" class="btn btn-primary">
          Crea Venditore
        </button>

      </form>
    </section>

    <section class="admin-users-section">
      <h2>Venditori Attivi</h2>

      <div class="admin-users-table-wrapper">
        <table class="admin-users-table">
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
                <div class="admin-users-actions">
                  <a
                          href="AdminUtentiController?action=deleteVenditore&id=${vend.idUtente}"
                          class="admin-users-btn-delete"
                          onclick="return confirm('Eliminare questo venditore?');"
                  >
                    Rimuovi
                  </a>
                </div>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </section>

    <div class="admin-users-footer">
      <a href="dashboardAdmin" class="btn btn-secondary">
        Vai alla Dashboard
      </a>
    </div>

  </div>
</div>

<jsp:include page="footer.jsp"/>

</body>
</html>
