<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">MotorsRent</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navCliente">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navCliente">
      <ul class="navbar-nav me-auto">
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/catalogo">Catalogo</a></li>
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/cliente/richieste">Le mie richieste</a></li>
      </ul>
      <ul class="navbar-nav ms-auto">
        <li class="nav-item me-2">
          <span class="navbar-text text-white">Benvenuto, <c:out value="${sessionScope.user.nome}"/></span>
        </li>
        <li class="nav-item"><a class="btn btn-outline-light" href="${pageContext.request.contextPath}/logout">Logout</a></li>
      </ul>
    </div>
  </div>
</nav>
