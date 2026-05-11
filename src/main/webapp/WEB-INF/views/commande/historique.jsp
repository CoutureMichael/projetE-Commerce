<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt"
           uri="jakarta.tags.fmt" %>

<html>

<head>

    <title>Historique des commandes</title>

    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">

</head>

<body class="bg-light">

<jsp:include page="/WEB-INF/includes/header.jsp"/>

<div class="container mt-5 mb-5 pb-5">

    <h2 class="mb-4 text-center">
        Mes commandes
    </h2>

    <%-- Aucune commande --%>
    <c:if test="${empty commandes}">

        <div class="alert alert-info text-center">

            Vous n'avez aucune commande.

        </div>

    </c:if>

    <%-- Liste des commandes --%>
    <c:forEach var="commande"
               items="${commandes}">

        <div class="card shadow mb-4">

            <div class="card-header bg-dark text-white">

                <div class="d-flex justify-content-between">

                    <span>

                        Commande #${commande.id}

                    </span>

                    <span>

                            ${commande.dateCommande}

                    </span>

                </div>

            </div>

            <div class="card-body">

                <h5 class="mb-3">
                    Produits commandés :
                </h5>

                <ul class="list-group mb-3">

                    <c:forEach var="produit"
                               items="${commande.produits}">

                        <li class="list-group-item d-flex justify-content-between align-items-center">

                            <div class="d-flex align-items-center">

                                <img src="${produit.image}"
                                     alt="${produit.nom}"
                                     width="70"
                                     height="70"
                                     class="rounded me-3"
                                     style="object-fit: cover;">

                                <div>

                                    <strong>

                                            ${produit.nom}

                                    </strong>

                                    <br>

                                    <small class="text-muted">

                                            ${produit.categorie}

                                    </small>

                                </div>

                            </div>

                            <strong>

                                <fmt:formatNumber
                                        value="${produit.prix}"
                                        type="number"
                                        minFractionDigits="2"
                                        maxFractionDigits="2"/>

                                $

                            </strong>

                        </li>

                    </c:forEach>

                </ul>

                <h5 class="text-end">

                    Total :

                    <span class="text-success">

                        <fmt:formatNumber
                                value="${commande.prixTotal}"
                                type="number"
                                minFractionDigits="2"
                                maxFractionDigits="2"/>

                        $

                    </span>

                </h5>

            </div>

        </div>

    </c:forEach>

    <%-- ACTIONS --%>
    <div class="text-center mt-5 mb-5">

        <a href="${pageContext.request.contextPath}/catalogue"
           class="btn btn-primary me-2">

            Continuer les achats

        </a>

        <a href="${pageContext.request.contextPath}/panier"
           class="btn btn-outline-dark">

            Voir mon panier

        </a>

    </div>

    <%-- ESPACE FOOTER --%>
    <div style="height: 120px;"></div>

</div>

<jsp:include page="/WEB-INF/includes/footer.jsp"/>

</body>

</html>
