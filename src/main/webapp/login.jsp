<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
  <title>Login - MotorsRent</title>
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&family=Playfair+Display:wght@700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="css/login.css">
</head>

<body class="login-page">

<jsp:include page="header.jsp" />

<div class="login-wrapper fade-in">
  <div class="login-card">

    <h1 class="login-title">Bentornato</h1>
    <p class="login-subtitle">Accedi alla tua area riservata MotorsRent</p>

    <c:if test="${not empty errore}">
      <div class="error-box">
        <i class="fa-solid fa-circle-exclamation"></i> ${errore}
      </div>
    </c:if>

    <form action="login" method="post" class="form-box">
      <div class="input-group">
        <label>Email</label>
        <input type="email" name="email" placeholder="esempio@email.it" required>
      </div>

      <div class="input-group">
        <label>Password</label>
        <input type="password" name="password" placeholder="••••••••" required>
      </div>

      <button type="submit" class="btn btn-login">Accedi</button>

      <div class="form-footer">
        <p>Non hai un account? <a href="registrazione.jsp">Registrati ora</a></p>
      </div>
    </form>

  </div>
</div>

<jsp:include page="footer.jsp" />

</body>
</html>