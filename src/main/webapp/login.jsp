<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
  <title>Login - MotorsRent</title>
  <link rel="stylesheet" href="css/login.css">
</head>

<body>

<jsp:include page="header.jsp" />

<div class="container">

  <h1>Login</h1>

  <c:if test="${not empty errore}">
    <div class="error-box" style="color: red; border: 1px solid red; padding: 10px; margin-bottom: 10px;">
        ${errore}
    </div>
  </c:if>

  <form action="login" method="post" class="form-box">

    <label>Email</label>
    <input type="email" name="email" required>

    <label>Password</label>
    <input type="password" name="password" required>

    <button type="submit" class="btn">Accedi</button>

    <p>Non hai un account?
      <a href="registrazione.jsp">Registrati</a>
    </p>
  </form>

</div>

<div class="login-page">
  <div class="login-container">

  </div>
  <div class="login-page">
    <div class="login-card">
      <h1 class="login-title">Login</h1>

    </div>
  </div>


</div>


<jsp:include page="footer.jsp" />

</body>
</html>