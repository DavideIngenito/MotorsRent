<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Catalogo Auto - MotorsRent</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>
<jsp:include page="header.jsp" />

<div class="container">
    <h1>Catalogo Auto</h1>

    <!-- FILTRI -->
    <form action="CatalogoServlet" method="get" class="filters">
        <label>Marca:</label>
        <input type="text" name="marca">

        <label>Prezzo massimo:</label>
        <input type="number" name="prezzoMax">

        <button type="submit" class="btn">Filtra</button>
    </form>

    <div class="catalogo-grid">
        <c:forEach var="auto" items="${listaAuto}">

            <div class="auto-card">
                <img src="${auto.immagine}" alt="${auto.modello}">
                <h3>${auto.marca} ${auto.modello}</h3>
                <p>Anno: ${auto.anno}</p>
                <p>Prezzo: € ${auto.prezzo}</p>

                <a class="btn" href="SchedaAutoServlet?id=${auto.idAuto}">
                    Visualizza dettagli
                </a>
            </div>

        </c:forEach>
    </div>

</div>

<jsp:include page="footer.jsp" />
</body>
</html>