<%@ page contentType="text/html;charset=UTF-8" %>
<%-- Se usi Tomcat 10/11 usa jakarta, altrimenti java.sun.com --%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<jsp:include page="header.jsp" />

<div class="container mt-5">

    <div class="p-5 mb-4 bg-light rounded-3 text-center">

        <%-- TITOLO DINAMICO --%>
        <c:choose>
            <c:when test="${empty sessionScope.utente}">
                <h1 class="display-5 fw-bold">Benvenuto su MotorsRent</h1>
                <p class="col-md-8 fs-4 mx-auto">
                    La soluzione migliore per il noleggio e l'acquisto della tua prossima auto.
                    Esplora il catalogo, richiedi un preventivo o simula un leasing in pochi click.
                </p>
            </c:when>
            <c:when test="${sessionScope.utente.ruolo == 'CLIENTE'}">
                <h1 class="display-5 fw-bold">Bentornato, ${sessionScope.utente.nome}!</h1>
                <p class="col-md-8 fs-4 mx-auto">
                    Pronto a trovare la tua prossima auto? Controlla le tue richieste o sfoglia il catalogo.
                </p>
            </c:when>
            <c:when test="${sessionScope.utente.ruolo == 'VENDITORE'}">
                <h1 class="display-5 fw-bold">Pannello Venditore</h1>
                <p class="col-md-8 fs-4 mx-auto">
                    Gestisci le richieste di preventivo e leasing dei clienti.
                </p>
            </c:when>
            <c:when test="${sessionScope.utente.ruolo == 'ADMIN'}">
                <h1 class="display-5 fw-bold">Amministrazione Sistema</h1>
                <p class="col-md-8 fs-4 mx-auto">
                    Gestione completa del parco auto e degli utenti registrati.
                </p>
            </c:when>
        </c:choose>

        <%-- AZIONI DINAMICHE (PULSANTI) --%>
        <div class="actions mt-4">

            <c:choose>
                <%-- CASO 1: OSPITE (Non loggato) --%>
                <c:when test="${empty sessionScope.utente}">
                    <a class="btn btn-primary btn-lg me-2" href="catalogo">Vai al Catalogo</a>
                    <a class="btn btn-outline-primary btn-lg me-2" href="login.jsp">Login</a>
                    <a class="btn btn-warning btn-lg" href="registrazione.jsp">Registrati</a>
                </c:when>

                <%-- CASO 2: CLIENTE --%>
                <c:when test="${sessionScope.utente.ruolo == 'CLIENTE'}">
                    <a class="btn btn-primary btn-lg me-2" href="catalogo">Sfoglia Catalogo</a>
                    <a class="btn btn-success btn-lg" href="dashboardCliente">La mia Area Personale</a>
                </c:when>

                <%-- CASO 3: VENDITORE --%>
                <c:when test="${sessionScope.utente.ruolo == 'VENDITORE'}">
                    <a class="btn btn-primary btn-lg me-2" href="dashboardVenditore">Dashboard Principale</a>
                    <a class="btn btn-outline-dark btn-lg me-2" href="venditorePreventivi">Richieste Preventivi</a>
                    <a class="btn btn-outline-dark btn-lg" href="venditoreLeasing">Richieste Leasing</a>
                </c:when>

                <%-- CASO 4: ADMIN --%>
                <c:when test="${sessionScope.utente.ruolo == 'ADMIN'}">
                    <a class="btn btn-danger btn-lg me-2" href="admin/dashboard">Dashboard Admin</a>
                    <a class="btn btn-outline-danger btn-lg me-2" href="AdminAutoController?action=list">Gestione Auto</a>
                    <a class="btn btn-outline-danger btn-lg" href="AdminUtentiController?action=listVenditori">Gestione Venditori</a>
                </c:when>
            </c:choose>

        </div>
    </div>

    <hr class="my-5">

    <%-- SEZIONI COMUNI A TUTTI --%>
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