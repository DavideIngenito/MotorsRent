
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>${auto.marca} ${auto.modello} - MotorsRent</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

<jsp:include page="header.jsp" />

<div class="container">

    <div class="scheda-auto">

        <img class="auto-img" src="${auto.immagine}" alt="${auto.modello}">

        <div class="auto-info">
            <h1>${auto.marca} ${auto.modello}</h1>

            <p><b>Anno:</b> ${auto.anno}</p>
            <p><b>Prezzo:</b> € ${auto.prezzo}</p>
            <p><b>Chilometraggio:</b> ${auto.chilometraggio} km</p>
            <p><b>Descrizione:</b> ${auto.descrizione}</p>
        </div>
    </div>

    <hr>

    <h2>Richieste</h2>

    <div class="actions">
        <a class="btn" href="login.jsp">Richiedi Preventivo</a>
        <a class="btn" href="login.jsp">Richiedi Leasing</a>
    </div>

</div>

<jsp:include page="footer.jsp" />

</body>
</html>