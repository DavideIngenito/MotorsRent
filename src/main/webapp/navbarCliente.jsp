<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar">
  <div class="logo">
    <a href="${pageContext.request.contextPath}/home">MotorsRent</a>
  </div>

  <ul class="nav-links">
    <li><a href="${pageContext.request.contextPath}/catalogo">Catalogo Auto</a></li>
    <li><a href="${pageContext.request.contextPath}/dashboardCliente">I Miei Ordini</a></li>

    <li>
      <a href="${pageContext.request.contextPath}/logout"
         style="color: red; font-weight: bold;">
        Esci
      </a>
    </li>
  </ul>
</nav>