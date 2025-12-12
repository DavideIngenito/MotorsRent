<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">MotorsRent</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navMain">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navMain">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/catalogo">Catalogo</a></li>
      </ul>
      <ul class="navbar-nav ms-auto">
        <li class="nav-item"><a class="btn btn-outline-primary me-2" href="${pageContext.request.contextPath}/login">Login</a></li>
        <li class="nav-item"><a class="btn btn-primary" href="${pageContext.request.contextPath}/register">Registrati</a></li>
      </ul>
    </div>
  </div>
</nav>
