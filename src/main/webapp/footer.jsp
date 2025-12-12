</div> <!-- /container -->
<footer class="bg-dark text-white mt-4">
    <div class="container py-3">
        <div class="row">
            <div class="col-md-8">
                <small>© ${pageContext.request.serverName} - MotorsRent - <c:out value="${pageContext.request.contextPath}"/></small>
            </div>
            <div class="col-md-4 text-end">
                <a href="${pageContext.request.contextPath}/contatti" class="text-white me-2">Contatti</a>
                <a href="${pageContext.request.contextPath}/chisiamo" class="text-white">Chi siamo</a>
            </div>
        </div>
    </div>
</footer>

<!-- Bootstrap JS (Popper incluso) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
</body>
</html>
