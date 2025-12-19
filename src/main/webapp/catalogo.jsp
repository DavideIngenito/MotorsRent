<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="it">
<head>
    <title>Catalogo - MotorsRent</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/catalogo.css">
</head>

<body>
<jsp:include page="header.jsp" />

<div class="container main-layout">

    <aside class="sidebar-filters">
        <h2 class="sidebar-title">${autoList.size()} veicoli</h2>

        <form action="catalogo" method="get" id="filterForm">

            <div class="filter-section active"> <div class="filter-header" onclick="toggleFilter(this)">
                MARCHIO <i class="fa-solid fa-chevron-down icon"></i>
            </div>
                <div class="filter-body">
                    <div class="checkbox-list">
                        <label class="custom-checkbox-label">
                            <span>Audi</span>
                            <input type="checkbox" name="marca" value="Audi">
                            <span class="checkmark"><i class="fa-solid fa-check"></i></span>
                        </label>
                        <label class="custom-checkbox-label">
                            <span>BMW</span>
                            <input type="checkbox" name="marca" value="BMW">
                            <span class="checkmark"><i class="fa-solid fa-check"></i></span>
                        </label>
                        <label class="custom-checkbox-label">
                            <span>Alfa Romeo</span>
                            <input type="checkbox" name="marca" value="Alfa Romeo">
                            <span class="checkmark"><i class="fa-solid fa-check"></i></span>
                        </label>
                        <label class="custom-checkbox-label">
                            <span>Toyota</span>
                            <input type="checkbox" name="marca" value="Toyota">
                            <span class="checkmark"><i class="fa-solid fa-check"></i></span>
                        </label>
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
                            <input type="checkbox" name="anno" value="2024">
                            <span class="checkmark"><i class="fa-solid fa-check"></i></span>
                        </label>
                        <label class="custom-checkbox-label">
                            <span>2023</span>
                            <input type="checkbox" name="anno" value="2023">
                            <span class="checkmark"><i class="fa-solid fa-check"></i></span>
                        </label>
                        <label class="custom-checkbox-label">
                            <span>2022</span>
                            <input type="checkbox" name="anno" value="2022">
                            <span class="checkmark"><i class="fa-solid fa-check"></i></span>
                        </label>
                        <label class="custom-checkbox-label">
                            <span>2021</span>
                            <input type="checkbox" name="anno" value="2021">
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
                        <input type="range" min="0" max="100000" step="1000" value="50000" class="range-slider" name="prezzoMax" id="priceRange" oninput="updatePriceLabel(this.value)">
                        <div class="range-values">
                            <span>€ 0</span>
                            <span id="priceLabel">€ 50.000</span>
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
                        <input type="range" min="0" max="200000" step="5000" value="100000" class="range-slider" name="kmMax" oninput="updateKmLabel(this.value)">
                        <div class="range-values">
                            <span>0 km</span>
                            <span id="kmLabel">100.000 km</span>
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
                            <span>Nuovo</span>
                            <input type="radio" name="stato" value="Nuova">
                            <span class="checkmark"><i class="fa-solid fa-check"></i></span>
                        </label>
                        <label class="custom-checkbox-label">
                            <span>Usato</span>
                            <input type="radio" name="stato" value="Usata">
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
                            <input type="checkbox" name="disponibilita" value="true" checked>
                            <span class="checkmark"><i class="fa-solid fa-check"></i></span>
                        </label>
                    </div>
                </div>
            </div>

            <button type="submit" class="btn-filter-apply">APPLICA FILTRI</button>
            <a href="catalogo" style="display:block; text-align:center; margin-top:15px; color:#545454; text-decoration:underline;">Resetta tutto</a>

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
                        <div class="card-price">€ ${auto.prezzo}</div>
                        <a href="schedaAuto?id=${auto.idAuto}" class="btn-details">Dettagli</a>
                    </div>
                </div>
            </c:forEach>
        </div>

        <c:if test="${empty autoList}">
            <p style="padding: 20px;">Nessun risultato trovato.</p>
        </c:if>
    </section>

</div>

<jsp:include page="footer.jsp" />

<script>
    // Funzione Accordion
    function toggleFilter(header) {
        // Trova il genitore .filter-section e aggiungi/rimuovi la classe 'active'
        header.parentElement.classList.toggle('active');
    }

    // Aggiorna etichetta Prezzo
    function updatePriceLabel(val) {
        // Formatta il numero con i punti (es. 50.000)
        document.getElementById('priceLabel').innerText = '€ ' + parseInt(val).toLocaleString('it-IT');
    }

    // Aggiorna etichetta Km
    function updateKmLabel(val) {
        document.getElementById('kmLabel').innerText = parseInt(val).toLocaleString('it-IT') + ' km';
    }
</script>

</body>
</html>