<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Richiesta Leasing - MotorsRent</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

<jsp:include page="header.jsp"/>

<div class="container">
    <h2>Richiesta Leasing</h2>

    <c:if test="${not empty errore}">
        <div class="error-box" style="color: red; margin-bottom: 15px;">
                ${errore}
        </div>
    </c:if>

    <form action="richiestaLeasing" method="post" class="form-box">

        <input type="hidden" name="idAuto" value="${param.idAuto != null ? param.idAuto : idAuto}">

        <div class="form-group">
            <label>Anticipo (€):</label>
            <input type="number" name="anticipo" min="0" step="100" required>
        </div>

        <div class="form-group">
            <label>Durata (mesi):</label>
            <select name="durata" required>
                <option value="24">24 Mesi</option>
                <option value="36">36 Mesi</option>
                <option value="48">48 Mesi</option>
                <option value="60">60 Mesi</option>
            </select>
        </div>

        <div class="form-group">
            <label>Km annui:</label>
            <input type="number" name="kmAnnui" min="5000" step="1000" required>
        </div>

        <button type="submit" class="btn">Invia Richiesta Leasing</button>
    </form>
</div>

<jsp:include page="footer.jsp"/>

</body>
</html>