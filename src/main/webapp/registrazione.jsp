<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Registrazione - MotorsRent</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

<jsp:include page="header.jsp" />

<div class="container">

    <h1>Registrazione</h1>

    <c:if test="${not empty errore}">
        <div class="error-box" style="color: red; border: 1px solid red; padding: 10px;">
                ${errore}
        </div>
    </c:if>

    <form action="registrazione" method="post" class="form-box">

        <label>Nome</label>
        <input type="text" name="nome" required>

        <label>Cognome</label>
        <input type="text" name="cognome" required>

        <label>Email</label>
        <input type="email" name="email" required>

        <label>Telefono</label>
        <input type="text" name="telefono" required>

        <label>Password</label>
        <input type="password" name="password" required>

        <button type="submit" class="btn">Crea Account</button>

    </form>

    <p>Hai già un account? <a href="login.jsp">Accedi qui</a></p>

</div>

<jsp:include page="footer.jsp" />

</body>
</html>