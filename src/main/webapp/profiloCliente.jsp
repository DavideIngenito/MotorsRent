<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <title>Il mio Profilo - MotorsRent</title>
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

    <style>
        /* CSS Specifico per questa pagina, integrato con main.css */
        .profile-container {
            max-width: 800px;
            margin: 40px auto;
            background: white;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 5px 20px rgba(0,0,0,0.05);
            border: 1px solid #eee;
        }

        .profile-header {
            text-align: center;
            margin-bottom: 40px;
            padding-bottom: 20px;
            border-bottom: 1px solid #f0f0f0;
        }

        .avatar-circle {
            width: 80px;
            height: 80px;
            background-color: #121212;
            color: white;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 2rem;
            margin: 0 auto 15px auto;
        }

        /* Grid per il form */
        .form-grid {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 20px;
        }

        .full-width {
            grid-column: span 2;
        }

        /* Stile Input (uguale a dashboardVenditore/Richieste) */
        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-weight: 600;
            font-size: 0.9rem;
            color: #333;
        }

        .form-group input {
            width: 100%;
            padding: 12px;
            border: 1px solid #ddd;
            border-radius: 6px;
            font-size: 1rem;
            font-family: 'Inter', sans-serif;
            transition: border-color 0.2s;
        }

        .form-group input:focus {
            border-color: #121212;
            outline: none;
        }

        .btn-save {
            background-color: #121212;
            color: white;
            padding: 15px;
            border: none;
            border-radius: 6px;
            width: 100%;
            font-size: 1rem;
            font-weight: 700;
            text-transform: uppercase;
            cursor: pointer;
            margin-top: 20px;
        }

        .btn-save:hover {
            background-color: #333;
        }

        .success-msg {
            background-color: #d1e7dd;
            color: #0f5132;
            padding: 15px;
            border-radius: 6px;
            margin-bottom: 20px;
            text-align: center;
            border: 1px solid #badbcc;
        }

        .error-msg {
            background-color: #f8d7da;
            color: #842029;
            padding: 15px;
            border-radius: 6px;
            margin-bottom: 20px;
            text-align: center;
            border: 1px solid #f5c2c7;
        }
    </style>
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="container">

    <div class="profile-container">

        <div class="profile-header">
            <div class="avatar-circle">
                <i class="fa-solid fa-user"></i>
            </div>
            <h1 style="margin:0; font-size:2rem;">${sessionScope.utente.nome} ${sessionScope.utente.cognome}</h1>
            <p style="color:#777;">Gestisci le informazioni del tuo account</p>
        </div>

        <c:if test="${not empty param.msg}">
            <div class="success-msg">
                <i class="fa-solid fa-check-circle"></i> ${param.msg}
            </div>
        </c:if>
        <c:if test="${not empty param.error}">
            <div class="error-msg">
                <i class="fa-solid fa-exclamation-circle"></i> ${param.error}
            </div>
        </c:if>

        <form action="modificaProfilo" method="post">
            <div class="form-grid">

                <div class="form-group">
                    <label>Nome</label>
                    <input type="text" name="nome" value="${sessionScope.utente.nome}" required>
                </div>

                <div class="form-group">
                    <label>Cognome</label>
                    <input type="text" name="cognome" value="${sessionScope.utente.cognome}" required>
                </div>

                <div class="form-group full-width">
                    <label>Email (Username)</label>
                    <input type="email" name="email" value="${sessionScope.utente.email}" required>
                </div>

                <div class="form-group full-width">
                    <label>Telefono</label>
                    <input type="text" name="telefono" value="${sessionScope.utente.telefono}" required>
                </div>

                <div class="form-group full-width">
                    <label>Password</label>
                    <input type="text" name="password" value="${sessionScope.utente.password}" required>
                    <small style="color:#888; display:block; margin-top:5px;">
                        <i class="fa-solid fa-lock"></i> Modifica questo campo per cambiare la password.
                    </small>
                </div>

            </div>

            <button type="submit" class="btn-save">Salva Modifiche</button>
        </form>

        <div style="text-align:center; margin-top:20px;">
            <a href="dashboardCliente" style="color:#666; text-decoration:underline;">Torna alla Dashboard</a>
        </div>

    </div>

</div>

<jsp:include page="footer.jsp"/>

</body>
</html>