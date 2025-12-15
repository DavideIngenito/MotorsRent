<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MotorsRent - Home</title>
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
<jsp:include page="header.jsp" />
<main class="container mt-5">
    <div class="hero-card p-5 mb-4 text-center">
        <h1 class="display-5 fw-bold">Benvenuto su MotorsRent</h1>
        <p class="fs-4 mx-auto">
            La soluzione migliore per il noleggio e l'acquisto della tua prossima auto.<br>
            Esplora il catalogo, richiedi un preventivo o simula un leasing in pochi click.
        </p>
        <div class="actions mt-4">
            <a class="btn btn-primary btn-lg" href="catalogo">Vai al Catalogo</a>
            <c:if test="${empty sessionScope.utente}">
            </c:if>
        </div>
    </div>
    <hr class="my-5 divider">
    <div class="row info-section">
        <div class="col-md-6 info-box" id="chisiamo">
            <h2>Chi Siamo</h2>
            <p>
                MotorsRent nasce con l’obiettivo di digitalizzare e rendere più efficiente la gestione di una concessionaria automobilistica.
                Offriamo trasparenza, velocità e un catalogo sempre aggiornato.
            </p>
        </div>
        <div class="col-md-6 info-box" id="contatti">
            <h2>Contatti</h2>
            <ul class="list-unstyled">
                <li><strong>Email:</strong> info@motorsrent.it</li>
                <li><strong>Telefono:</strong> +39 081 1234567</li>
                <li><strong>Sede:</strong> Via Roma 10, Napoli (NA)</li>
            </ul>
        </div>
    </div>
</main>
<jsp:include page="footer.jsp" />
</body>
</html>