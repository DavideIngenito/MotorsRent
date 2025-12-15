<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="header.jsp" />
    <head>
        <title>Home</title>

        <link rel="stylesheet" href="css/reset.css">
        <link rel="stylesheet" href="css/body.css">
        <link rel="stylesheet" href="css/header.css">
        <link rel="stylesheet" href="css/buttons.css">
        <link rel="stylesheet" href="css/footer.css">
    </head>

    <body>

    <header>
        <h1>Sito</h1>
        <nav>
            <a href="home.jsp">Home</a>
            <a href="login.jsp">Login</a>
        </nav>
    </header>

    <div class="container">
        <h2>Benvenuto</h2>
        <button>Scopri di più</button>
    </div>

    <footer>
        © 2024
    </footer>

    </body>
    </html>

</head>
<div class="container mt-5">

    <div class="p-5 mb-4 bg-light rounded-3 text-center">
        <h1 class="display-5 fw-bold">Benvenuto su MotorsRent</h1>
        <p class="col-md-8 fs-4 mx-auto">
            La soluzione migliore per il noleggio e l'acquisto della tua prossima auto.
            Esplora il catalogo, richiedi un preventivo o simula un leasing in pochi click.
        </p>

        <div class="actions mt-4">
            <a class="btn btn-primary btn-lg me-2" href="catalogo">Vai al Catalogo</a>

            <c:if test="${empty sessionScope.utente}">
                <a class="btn btn-outline-primary btn-lg me-2" href="login.jsp">Login</a>
                <a class="btn btn-warning btn-lg" href="registrazione.jsp">Registrati</a>
            </c:if>
        </div>
    </div>

    <hr class="my-5">

    <div class="row">
        <div class="col-md-6" id="chisiamo">
            <h2>Chi Siamo</h2>
            <p>
                MotorsRent nasce con l’obiettivo di digitalizzare e rendere più efficiente la gestione di una concessionaria automobilistica.
                Offriamo trasparenza, velocità e un catalogo sempre aggiornato.
            </p>
        </div>

        <div class="col-md-6" id="contatti">
            <h2>Contatti</h2>
            <ul class="list-unstyled">
                <li><strong>Email:</strong> info@motorsrent.it</li>
                <li><strong>Telefono:</strong> +39 081 1234567</li>
                <li><strong>Sede:</strong> Via Roma 10, Napoli (NA)</li>
            </ul>
        </div>
    </div>

</div>

<jsp:include page="footer.jsp" />