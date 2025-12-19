<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
  <title>Login - MotorsRent</title>
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="css/login.css">
</head>

<body class="login-page">

<div class="login-container">

  <img src="img/image_4.png" alt="MotorsRent Logo" class="logo-img">

  <h1 class="login-title">Login</h1>

  <c:if test="${not empty errore}">
    <div style="color: red; margin-bottom: 20px;">${errore}</div>
  </c:if>

  <form action="login" method="post" class="form-box">
    <div class="input-group">
      <label for="username">Username</label>
      <input type="text" id="username" name="username" required>
    </div>

    <div class="input-group">
      <label for="password">Password</label>
      <input type="password" id="password" name="password" required>
    </div>

    <a href="#" class="forgot-password-link">Ho dimenticato la password</a>

    <button type="submit" class="btn-login">LOGIN</button>

    <a href="registrazione.jsp" class="register-link">Non hai un account? Registrat!!</a>
  </form>

</div>

</body>
</html>