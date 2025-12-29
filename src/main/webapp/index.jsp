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

<div class="hero-section">
    <div class="hero-overlay"></div>
    <div class="hero-content">
        <h1 class="main-title">MotorsRent</h1>
        <p class="main-subtitle">
            Il tuo viaggio inizia qui. Scegli l'auto perfetta per le tue esigenze <br>
            e parti alla scoperta di nuove emozioni.
        </p>
        <a href="catalogo" class="btn-hero">Vai al Catalogo</a>
    </div>
</div>

<section class="features-section container fade-in delay-1" style="margin-top: 10px; margin-bottom: 60px;">
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

<section class="section-novita fade-in delay-2" style="background-color: #f9f9f9;">
    <div class="container">
        <div class="section-header">
            <h2 class="section-title">Le Nostre Novità</h2>
            <p class="section-desc">Scopri gli ultimi arrivi nel nostro parco auto.</p>
        </div>

        <div class="cars-grid">
            <c:forEach var="auto" items="${listaNovita}">
                <div class="car-card">
                    <div class="car-img-wrapper">
                        <img src="${auto.immagine}" alt="${auto.modello}">

                        <span class="badge-status status-${auto.stato.toLowerCase()}">
                                ${auto.stato}
                        </span>
                    </div>
                    <div class="car-info">
                        <span class="car-brand">${auto.marca}</span>
                        <h3 class="car-model">${auto.modello}</h3>
                        <div class="car-footer">
                            <span class="car-price">€ ${auto.prezzo}</span>
                            <a href="schedaAuto?idAuto=${auto.idAuto}" class="btn-details">
                                <i class="fa-solid fa-arrow-right"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <c:if test="${empty listaNovita}">
            <div style="text-align:center; padding: 20px; color: #666;">
                Nessuna novità disponibile al momento.
            </div>
        </c:if>

        <div style="text-align: center; margin-top: 30px;">
            <a href="catalogo" class="btn-view-all">Visualizza tutto il catalogo</a>
        </div>
    </div>
</section>

<main class="container content-grid fade-in delay-2" style="margin-top: 60px; margin-bottom: 60px;">
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