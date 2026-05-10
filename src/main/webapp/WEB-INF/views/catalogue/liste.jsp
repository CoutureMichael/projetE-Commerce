<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Catalogue - liste des produits</title>
    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/includes/header.jsp"/>

    <div class="container my-5 pb-5">
    <h2>Catalogue des produits</h2>
    <div class="row row-cols-1 row-cols-md-3 g-4 mt-3">
        <c:forEach var="produit" items="${produits}">
            <div class="col">
                <div class="card h-100">
                    <c:if test="${not empty produit.image}">
                        <img src="${produit.image}" class="card-img-top" alt="${produit.nom}" style="height: 200px; object-fit: cover;">
                    </c:if>
                    <div class="card-body d-flex flex-column">
                        <h5 class="card-title">${produit.nom}</h5>
                        <p class="card-text">${fn:substring(produit.description, 0, 100)}...</p>
                        <div class="mt-auto">
                            <span class="fw-bold d-block mb-2">
                                <fmt:formatNumber value="${produit.prix}" type="currency" currencySymbol="$"/>
                            </span>
                            <div class="d-flex gap-2">
                                <a href="${pageContext.request.contextPath}/produit-detail?id=${produit.id}" class="btn btn-sm btn-outline-primary">
                                    Voir
                                </a>
                                <a href="${pageContext.request.contextPath}/panier?action=ajouter&id=${produit.id}" class="btn btn-sm btn-primary">
                                    Ajouter au panier
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
        <c:if test="${empty produits}">
            <div class="col-12">
                <div class="alert alert-info mt-5 text-center">
                    Aucun produit disponible.
                </div>
            </div>
        </c:if>
    </div>
        <nav aria-label="..." class="d-flex justify-content-center mt-4">
            <ul class="pagination">
                <li class="page-item"><a href="#" class="page-link">Previous</a></li>
                <li class="page-item active">
                    <a class="page-link" href="#" aria-current="page">1</a>
                </li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item"><a class="page-link" href="#">Next</a></li>
            </ul>
        </nav>
    </div>
<br>

<jsp:include page="/WEB-INF/includes/footer.jsp"/>
</body>
</html>
