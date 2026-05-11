<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt"
           uri="jakarta.tags.fmt" %>

<html>

<head>

    <title>Checkout</title>

    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">

</head>

<body class="bg-light">

<jsp:include page="/WEB-INF/includes/header.jsp"/>

<div class="container mt-5">

    <h2 class="text-center mb-4">
        Validation de la commande
    </h2>

    <!-- Panier vide -->
    <c:if test="${empty sessionScope.panier.produits}">

        <div class="alert alert-warning text-center">

            Votre panier est vide.

        </div>

    </c:if>

    <!-- Produits -->
    <c:if test="${not empty sessionScope.panier.produits}">

        <div class="card shadow">

            <div class="card-body">

                <h4 class="mb-4">
                    Produits sélectionnés
                </h4>

                <ul class="list-group mb-4">

                    <c:forEach var="entry"
                               items="${sessionScope.panier.produits}">

                        <li class="list-group-item">

                            <div class="row align-items-center">

                                <!-- IMAGE -->
                                <div class="col-md-2 text-center">

                                    <img src="${entry.key.image}"
                                         alt="${entry.key.nom}"
                                         class="img-fluid rounded"
                                         style="max-height: 80px; object-fit: cover;">

                                </div>

                                <!-- INFOS -->
                                <div class="col-md-7">

                                    <h5 class="mb-1">
                                            ${entry.key.nom}
                                    </h5>

                                    <p class="text-muted mb-1">
                                            ${entry.key.categorie}
                                    </p>

                                    <small>
                                        Quantité :
                                            ${entry.value}
                                    </small>

                                </div>

                                <!-- PRIX -->
                                <div class="col-md-3 text-end">

                                    <strong>

                                        <fmt:formatNumber
                                                value="${entry.key.prix}"
                                                type="number"
                                                minFractionDigits="2"
                                                maxFractionDigits="2"/>

                                        $

                                    </strong>

                                </div>

                            </div>

                        </li>

                    </c:forEach>

                </ul>

                <!-- TOTAL -->
                <h4 class="text-end mb-4">

                    Total :

                    <fmt:formatNumber
                            value="${sessionScope.panier.total}"
                            type="number"
                            minFractionDigits="2"
                            maxFractionDigits="2"/>

                    $

                </h4>

                <!-- BUTTON -->
                <div class="text-center">

                    <a href="${pageContext.request.contextPath}/commande"
                       class="btn btn-success btn-lg">

                        Confirmer la commande

                    </a>

                </div>

            </div>

        </div>

    </c:if>

</div>

<jsp:include page="/WEB-INF/includes/footer.jsp"/>

</body>

</html>
