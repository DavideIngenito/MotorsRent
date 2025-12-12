<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-success">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/venditore">MotorsRent - Venditore</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navVenditore">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navVenditore">
            <ul class="navbar-nav me-auto">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/venditore/richieste">Richieste</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/venditore/catalogo">Catalogo</a></li>
            </ul>
            <ul class="navbar-nav ms-auto">
                <li class="nav-item me-2">
                    <span class="navbar-text text-white">Ciao, <c:out value="${sessionScope.user.nome}"/></span>
                </li>
                <li class="nav-item"><a class="btn btn-outline-light" href="${pageContext.request.contextPath}/logout">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>
