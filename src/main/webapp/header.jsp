<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet"/>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/home">MotorsRent</a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">

                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/home">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/catalogo">Catalogo</a></li>

                <c:choose>
                    <%-- CASO 1: OSPITE --%>
                    <c:when test="${empty sessionScope.utente}">
                        <li class="nav-item"><a class="nav-link btn btn-outline-light ms-2" href="${pageContext.request.contextPath}/login.jsp">Login</a></li>
                        <li class="nav-item"><a class="nav-link btn btn-warning ms-2 text-dark" href="${pageContext.request.contextPath}/registrazione.jsp">Registrati</a></li>
                    </c:when>

                    <%-- CASO 2: CLIENTE --%>
                    <c:when test="${sessionScope.utente.ruolo == 'CLIENTE'}">
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/dashboardCliente">Area Personale</a></li>
                        <li class="nav-item"><a class="nav-link text-danger" href="${pageContext.request.contextPath}/logout">Logout</a></li>
                    </c:when>

                    <%-- CASO 3: VENDITORE --%>
                    <c:when test="${sessionScope.utente.ruolo == 'VENDITORE'}">
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/dashboardVenditore">Dashboard</a></li>
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/venditorePreventivi">Richieste Preventivi</a></li>
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/venditoreLeasing">Richieste Leasing</a></li>
                        <li class="nav-item"><a class="nav-link text-danger" href="${pageContext.request.contextPath}/logout">Logout</a></li>
                    </c:when>

                    <%-- CASO 4: ADMIN --%>
                    <c:when test="${sessionScope.utente.ruolo == 'ADMIN' || sessionScope.utente.ruolo == 'AMMINISTRATORE'}">
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/dashboardAdmin">Dashboard</a></li>
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/AdminUtentiController?action=listVenditori">Venditori</a></li>
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/AdminStatisticheServlet">Statistiche</a></li>
                        <li class="nav-item"><a class="nav-link text-danger" href="${pageContext.request.contextPath}/logout">Logout</a></li>
                    </c:when>
                </c:choose>

            </ul>
        </div>
    </div>
</nav>