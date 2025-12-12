
<%@ page contentType="text/html;charset=UTF-8"%>

<html>
<head>
  <title>Login - MotorsRent</title>
  <link rel="stylesheet" href="css/style.css">
</head>

<body>

<jsp:include page="header.jsp" />

<div class="container">

  <h1>Login</h1>

  <c:if test="${not empty error}">
    <div class="error-box">${error}</div>
  </c:if>

  <form action="LoginServlet" method="post" class="form-box">

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

<jsp:include page="footer.jsp" />

</body>
</html>