<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MotorsRent - Premium Mobility</title>

    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&family=Playfair+Display:wght@700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

    <link rel="stylesheet" href="css/index.css">
</head>

<body>

<jsp:include page="header.jsp" />

<header class="hero-section fade-in">
    <div class="container text-center">
        <span class="eyebrow">Benvenuto in MotorsRent</span>
        <h1 class="hero-title">Guidare non è mai stato così semplice.</h1>
        <p class="hero-subtitle">
            Vendita e Noleggio premium. Scegli la tua prossima auto con la sicurezza di un servizio trasparente e digitale.
        </p>

        <div class="actions">
            <a class="btn btn-primary" href="catalogo">
                <i class="fa-solid fa-car-side"></i> Vai al Catalogo
            </a>

            <c:if test="${empty sessionScope.utente}">
                <a class="btn btn-outline" href="login.jsp">Area Clienti</a>
                <a class="btn btn-text" href="registrazione.jsp">Non hai un account? Registrati &rarr;</a>
            </c:if>
        </div>
    </div>
</header>

<section class="features-section container fade-in delay-1">
    <div class="feature-card">
        <div class="icon-box"><i class="fa-solid fa-check-to-slot"></i></div>
        <h3>Prenotazione Online</h3>
        <p>Gestisci tutto dal divano di casa tua. Scegli, prenota e ritira in sede.</p>
    </div>
    <div class="feature-card">
        <div class="icon-box"><i class="fa-solid fa-file-contract"></i></div>
        <h3>Leasing Flessibile</h3>
        <p>Soluzioni finanziarie su misura per privati e aziende.</p>
    </div>
    <div class="feature-card">
        <div class="icon-box"><i class="fa-solid fa-shield-halved"></i></div>
        <h3>Veicoli Certificati</h3>
        <p>Ogni auto viene controllata dai nostri esperti meccanici.</p>
    </div>
</section>

<main class="container content-grid fade-in delay-2">
    <div class="info-text">
        <h2>Chi Siamo</h2>
        <p>
            MotorsRent nasce con l’obiettivo di digitalizzare il concetto di concessionaria.
            Abbandona la burocrazia cartacea: offriamo trasparenza, velocità e un catalogo aggiornato in tempo reale.
        </p>
        <a href="#" class="read-more">Scopri la nostra storia</a>
    </div>

    <div class="info-contact">
        <h2>Contattaci</h2>
        <ul class="contact-list">
            <li><i class="fa-solid fa-envelope"></i> info@motorsrent.it</li>
            <li><i class="fa-solid fa-phone"></i> +39 081 1234567</li>
            <li><i class="fa-solid fa-location-dot"></i> Via Roma 10, Napoli (NA)</li>
        </ul>
    </div>
</main>

<jsp:include page="footer.jsp" />

</body>
</html>