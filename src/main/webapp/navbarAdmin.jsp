<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/admin">MotorsRent - Admin</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navAdmin">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navAdmin">
      <ul class="navbar-nav me-auto">
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/catalogo">Catalogo</a></li>
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/utenti">Utenti</a></li>
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/statistiche">Statistiche</a></li>
      </ul>
      <ul class="navbar-nav ms-auto">
        <li class="nav-item me-2">
          <span class="navbar-text text-white">Admin: <c:out value="${sessionScope.user.nome}"/></span>
        </li>
        <li class="nav-item"><a class="btn btn-outline-light" href="${pageContext.request.contextPath}/logout">Logout</a></li>
      </ul>
    </div>
  </div>
</nav>
