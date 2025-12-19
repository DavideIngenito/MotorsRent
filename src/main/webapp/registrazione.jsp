<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Registrazione - MotorsRent</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&family=Playfair+Display:wght@700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

    <link rel="stylesheet" href="css/registrazione.css">
</head>

<body class="login-page"> <jsp:include page="header.jsp" />

<div class="login-wrapper fade-in">
    <div class="login-card registration-card"> <h1 class="login-title">Crea Account</h1>
        <p class="login-subtitle">Unisciti al mondo MotorsRent in pochi click</p>

        <c:if test="${not empty errore}">
            <div class="error-box">
                <i class="fa-solid fa-circle-exclamation"></i> ${errore}
            </div>
        </c:if>

        <form action="registrazione" method="post" class="form-box">

            <div class="row-inputs">
                <div class="input-group">
                    <label>Nome</label>
                    <input type="text" name="nome" placeholder="Mario" required>
                </div>
                <div class="input-group">
                    <label>Cognome</label>
                    <input type="text" name="cognome" placeholder="Rossi" required>
                </div>
            </div>

            <div class="input-group">
                <label>Email</label>
                <input type="email" name="email" placeholder="nome@esempio.it" required>
            </div>

            <div class="input-group">
                <label>Telefono</label>
                <input type="tel" name="telefono" placeholder="+39 333 1234567" required>
            </div>

            <div class="input-group">
                <label>Password</label>
                <input type="password" name="password" placeholder="Scegli una password sicura" required>
            </div>

            <button type="submit" class="btn btn-login">Crea Account</button>

            <div class="form-footer">
                <p>Hai già un account? <a href="login.jsp">Accedi qui</a></p>
            </div>

        </form>

    </div>
</div>

<jsp:include page="footer.jsp" />

</body>
</html>