<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="it">
<head>
    <title>Catalogo Auto - MotorsRent</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&family=Playfair+Display:wght@700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/header.css"> <link rel="stylesheet" href="css/catalogo.css"> </head>

<body>
<jsp:include page="header.jsp" />

<div class="container main-layout">

    <aside class="sidebar-filters">
        <div class="filter-header">
            <h3>Filtra Veicoli</h3>
            <span class="filter-count">${autoList.size()} risultati</span>
        </div>

        <form action="catalogo" method="get">

            <div class="filter-group">
                <label class="filter-label">MARCHIO</label>
                <input type="text" name="marca" placeholder="Es. Audi, BMW..." class="filter-input">
            </div>

            <div class="filter-group">
                <label class="filter-label">PREZZO MASSIMO</label>
                <div class="range-wrapper">
                    <input type="number" name="prezzoMax" placeholder="€ 50.000" class="filter-input">
                </div>
            </div>

            <button type="submit" class="btn btn-filter">APP_LICA FILTRI</button>
            <a href="catalogo" class="reset-link">Resetta filtri</a>
        </form>
    </aside>

    <section class="catalog-results">

        <div class="catalog-grid">
            <c:forEach var="auto" items="${autoList}">
                <div class="auto-card">
                    <c:if test="${auto.stato == 'Nuova'}">
                        <span class="badge-new">NUOVO</span>
                    </c:if>

                    <div class="card-img-container">
                        <img src="${auto.immagine}" alt="${auto.modello}">
                    </div>

                    <div class="card-content">
                        <h3 class="card-brand">${auto.marca}</h3>
                        <h4 class="card-model">${auto.modello}</h4>

                        <div class="card-specs">
                            <span><i class="fa-regular fa-calendar"></i> ${auto.anno}</span>
                            <span>Automatico</span>
                        </div>

                        <div class="card-price">€ ${auto.prezzo}</div>

                        <div class="card-actions">
                            <a href="schedaAuto?id=${auto.idAuto}" class="btn btn-details">Dettagli</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <c:if test="${empty autoList}">
            <div class="no-results">
                <h3>Nessun veicolo trovato.</h3>
                <p>Prova a modificare i filtri di ricerca.</p>
            </div>
        </c:if>

    </section>

</div>

<jsp:include page="footer.jsp" />
</body>
</html>