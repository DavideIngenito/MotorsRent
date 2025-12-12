<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>MotorsRent - Home</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

<jsp:include page="header.jsp" />

<div class="container">

    <h1>Benvenuto su MotorsRent</h1>
    <p>Esplora il nostro catalogo di auto e scopri le opportunità di leasing e preventivi.</p>

    <div class="actions">
        <a class="btn" href="CatalogoServlet">Vai al Catalogo</a>
        <a class="btn" href="login.jsp">Login</a>
        <a class="btn" href="registrazione.jsp">Registrati</a>
    </div>

    <hr>

    <section id="chisiamo">
        <h2>Chi Siamo</h2>
        <p>
            MotorsRent è una piattaforma moderna che facilita la consultazione del catalogo auto e
            la gestione delle richieste di preventivo e leasing.
        </p>
    </section>

    <section id="contatti">
        <h2>Contatti</h2>
        <p>Email: info@motorsrent.it</p>
        <p>Telefono: +39 333 1234567</p>
        <p>Indirizzo: Via Roma 10, Napoli (NA)</p>
    </section>

</div>

<jsp:include page="footer.jsp" />

</body>
</html>