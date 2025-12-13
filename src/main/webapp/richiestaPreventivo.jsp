<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Richiesta Preventivo - MotorsRent</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

<jsp:include page="header.jsp"/>

<div class="container">
    <h2>Richiedi un Preventivo</h2>

    <c:if test="${not empty errore}">
        <div class="error-box" style="color: red; margin-bottom: 15px;">
                ${errore}
        </div>
    </c:if>

    <form action="richiestaPreventivo" method="post" class="form-box">

        <input type="hidden" name="idAuto" value="${param.idAuto != null ? param.idAuto : idAuto}">

        <div class="form-group">
            <label>Auto Selezionata (ID):</label>
            <input type="text" value="${param.idAuto != null ? param.idAuto : idAuto}" disabled>
        </div>

        <div class="form-group">
            <label>Note aggiuntive per il venditore:</label><br>
            <textarea name="note" rows="5" style="width: 100%; padding: 10px;" placeholder="Scrivi qui eventuali domande..."></textarea>
        </div>

        <br>
        <button type="submit" class="btn">Invia Richiesta</button>
    </form>
</div>

<jsp:include page="footer.jsp"/>

</body>
</html>