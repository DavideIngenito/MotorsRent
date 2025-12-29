<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <title>Catalogo - MotorsRent</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

    <link rel="stylesheet" href="css/catalogo.css">
</head>

<body>
<jsp:include page="header.jsp" />

<div class="container main-layout">

    <aside class="sidebar-filters">
        <h2 class="sidebar-title">Filtri Ricerca</h2>

        <form action="catalogo" method="get" id="filterForm">

            <div class="filter-section active">
                <div class="filter-header" onclick="toggleFilter(this)">
                    MARCHIO <i class="fa-solid fa-chevron-down icon"></i>
                </div>
                <div class="filter-body">
                    <div class="search-input-wrapper">
                        <input type="text" name="marca" placeholder="Cerca marca..." class="search-input" value="${param.marca}">
                    </div>
                </div>
            </div>

            <div class="filter-section active">
                <div class="filter-header" onclick="toggleFilter(this)">
                    ANNO <i class="fa-solid fa-chevron-down icon"></i>
                </div>
                <div class="filter-body">
                    <div class="checkbox-list">

                        <label class="custom-checkbox-label">
                            <span>2024</span>
                            <input type="checkbox" name="anno" value="2024"
                                   <c:if test="${fn:contains(pageContext.request.queryString, 'anno=2024')}">checked</c:if>>
                            <span class="checkmark"><i class="fa-solid fa-check"></i></span>
                        </label>

                        <label class="custom-checkbox-label">
                            <span>2023</span>
                            <input type="checkbox" name="anno" value="2023"
                                   <c:if test="${fn:contains(pageContext.request.queryString, 'anno=2023')}">checked</c:if>>
                            <span class="checkmark"><i class="fa-solid fa-check"></i></span>
                        </label>

                        <label class="custom-checkbox-label">
                            <span>2022</span>
                            <input type="checkbox" name="anno" value="2022"
                                   <c:if test="${fn:contains(pageContext.request.queryString, 'anno=2022')}">checked</c:if>>
                            <span class="checkmark"><i class="fa-solid fa-check"></i></span>
                        </label>

                        <label class="custom-checkbox-label">
                            <span>2021</span>
                            <input type="checkbox" name="anno" value="2021"
                                   <c:if test="${fn:contains(pageContext.request.queryString, 'anno=2021')}">checked</c:if>>
                            <span class="checkmark"><i class="fa-solid fa-check"></i></span>
                        </label>

                        <label class="custom-checkbox-label">
                            <span>2020</span>
                            <input type="checkbox" name="anno" value="2020"
                                   <c:if test="${fn:contains(pageContext.request.queryString, 'anno=2020')}">checked</c:if>>
                            <span class="checkmark"><i class="fa-solid fa-check"></i></span>
                        </label>

                    </div>
                </div>
            </div>

            <div class="filter-section active">
                <div class="filter-header" onclick="toggleFilter(this)">
                    PREZZO <i class="fa-solid fa-chevron-down icon"></i>
                </div>
                <div class="filter-body">
                    <div class="range-container">
                        <input type="range" min="0" max="100000" step="1000"
                               value="${not empty param.prezzoMax ? param.prezzoMax : 100000}"
                               class="range-slider" name="prezzoMax"
                               oninput="updatePriceLabel(this.value)">
                        <div class="range-values">
                            <span>€ 0</span>
                            <span id="priceLabel">€ ${not empty param.prezzoMax ? param.prezzoMax : '100.000'}</span>
                        </div>
                    </div>
                </div>
            </div>

            <div class="filter-section">
                <div class="filter-header" onclick="toggleFilter(this)">
                    CHILOMETRAGGIO <i class="fa-solid fa-chevron-down icon"></i>
                </div>
                <div class="filter-body">
                    <div class="range-container">
                        <input type="range" min="0" max="250000" step="5000"
                               value="${not empty param.kmMax ? param.kmMax : 250000}"
                               class="range-slider" name="kmMax"
                               oninput="updateKmLabel(this.value)">
                        <div class="range-values">
                            <span>0 km</span>
                            <span id="kmLabel">${not empty param.kmMax ? param.kmMax : '250.000'} km</span>
                        </div>
                    </div>
                </div>
            </div>

            <div class="filter-section active">
                <div class="filter-header" onclick="toggleFilter(this)">
                    STATO <i class="fa-solid fa-chevron-down icon"></i>
                </div>
                <div class="filter-body">
                    <div class="checkbox-list">
                        <label class="custom-checkbox-label">
                            <span>Tutti</span>
                            <input type="radio" name="stato" value="" ${empty param.stato ? 'checked' : ''}>
                            <span class="checkmark"><i class="fa-solid fa-check"></i></span>
                        </label>
                        <label class="custom-checkbox-label">
                            <span>Nuova</span>
                            <input type="radio" name="stato" value="Nuova" ${param.stato == 'Nuova' ? 'checked' : ''}>
                            <span class="checkmark"><i class="fa-solid fa-check"></i></span>
                        </label>
                        <label class="custom-checkbox-label">
                            <span>Usata</span>
                            <input type="radio" name="stato" value="Usata" ${param.stato == 'Usata' ? 'checked' : ''}>
                            <span class="checkmark"><i class="fa-solid fa-check"></i></span>
                        </label>
                    </div>
                </div>
            </div>

            <div class="filter-section active">
                <div class="filter-header" onclick="toggleFilter(this)">
                    DISPONIBILITÀ <i class="fa-solid fa-chevron-down icon"></i>
                </div>
                <div class="filter-body">
                    <div class="checkbox-list">
                        <label class="custom-checkbox-label">
                            <span>Solo disponibili</span>
                            <input type="checkbox" name="disponibilita" value="true" ${param.disponibilita == 'true' ? 'checked' : ''}>
                            <span class="checkmark"><i class="fa-solid fa-check"></i></span>
                        </label>
                    </div>
                </div>
            </div>

            <button type="submit" class="btn-filter-apply">APPLICA FILTRI</button>
            <a href="catalogo" class="btn-reset" style="display:block; text-align:center; margin-top:15px; color:#545454; text-decoration:underline;">Resetta tutto</a>

        </form>
    </aside>

    <section class="catalog-results">
        <div class="catalog-grid">
            <c:forEach var="auto" items="${autoList}">
                <div class="auto-card">

                    <c:if test="${auto.stato == 'Nuova'}">
                        <span class="badge" style="position:absolute; top:10px; left:10px; background:#121212; color:white; padding:5px 10px; font-size:0.7rem; font-weight:bold; border-radius:4px;">NUOVO</span>
                    </c:if>

                    <div class="card-img-container">
                        <img src="${auto.immagine}" alt="${auto.modello}">
                    </div>

                    <div class="card-content">
                        <h3 class="card-brand">${auto.marca}</h3>
                        <h4 class="card-model">${auto.modello}</h4>

                        <div style="font-size:0.85rem; color:#666; margin-bottom:10px; display:flex; gap:10px;">
                            <span><i class="fa-regular fa-calendar"></i> ${auto.anno}</span>
                            <span><i class="fa-solid fa-road"></i> ${auto.chilometraggio} km</span>
                        </div>

                        <div class="card-price">€ ${auto.prezzo}</div>
                        <a href="schedaAuto?id=${auto.idAuto}" class="btn-details">Dettagli</a>
                    </div>
                </div>
            </c:forEach>
        </div>

        <c:if test="${empty autoList}">
            <div style="padding: 50px; text-align: center; color: #555;">
                <h3>Nessun veicolo trovato.</h3>
                <p>Prova a modificare i filtri di ricerca.</p>
            </div>
        </c:if>
    </section>

</div>

<jsp:include page="footer.jsp" />

<script>
    // Logica per aprire/chiudere i filtri (Accordion)
    function toggleFilter(header) {
        header.parentElement.classList.toggle('active');
    }

    // Aggiornamento visuale Prezzo
    function updatePriceLabel(val) {
        document.getElementById('priceLabel').innerText = '€ ' + parseInt(val).toLocaleString('it-IT');
    }

    // Aggiornamento visuale KM
    function updateKmLabel(val) {
        document.getElementById('kmLabel').innerText = parseInt(val).toLocaleString('it-IT') + ' km';
    }
</script>

</body>
</html>