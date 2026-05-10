<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Detail - produit individuel</title>
    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/includes/header.jsp"/>

<div class="container my-5">
    <c:choose>
        <c:when test="${not empty produit}">
            <div class="row">
                <div class="col-md-5">
                    <c:if test="${not empty produit.image}">
                        <img src="${produit.image}" alt="${produit.nom}" class="img-fluid rounded mb-4" style="object-fit:cover; max-height:350px;">
                    </c:if>
                </div>
                <div class="col-md-7">
                    <h2 class="mb-3">${produit.nom}</h2>
                    <h5 class="text-muted mb-3">${produit.categorie}</h5>
                    <p>${produit.description}</p>
                    <p class="h4 text-success mb-4">
                        <fmt:formatNumber value="${produit.prix}" type="currency" currencySymbol="$"/>
                    </p>
                    <form method="get" action="${pageContext.request.contextPath}/panier">
                        <input type="hidden" name="action" value="ajouter">
                        <input type="hidden" name="id" value="${produit.id}">
                        <button type="submit" class="btn btn-primary">
                            <i class="bi bi-cart"></i> Ajouter au panier
                        </button>
                    </form>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-warning text-center">Produit non trouvé.</div>
        </c:otherwise>
    </c:choose>
</div>

<jsp:include page="/WEB-INF/includes/footer.jsp"/>

</body>
</html>
