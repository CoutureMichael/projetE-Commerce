<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>

    <title>Panier</title>

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">

</head>

<body class="bg-light">

<jsp:include page="/WEB-INF/includes/header.jsp"/>

<div class="container my-5 pb-5">

    <h2 class="mb-4">
        Mon panier
    </h2>

    <c:choose>

        <%-- PANIER NON VIDE --%>
        <c:when test="${not empty panier.produits}">

            <table class="table table-bordered align-middle bg-white shadow-sm">

                <thead class="table-light">

                <tr>

                    <th>Produit</th>
                    <th>Prix unitaire</th>
                    <th>Quantité</th>
                    <th>Total</th>
                    <th>Actions</th>

                </tr>

                </thead>

                <tbody>

                <c:forEach var="entry"
                           items="${panier.produits}">

                    <tr>

                            <%-- PRODUIT --%>
                        <td>

                            <div class="d-flex align-items-center">

                                <img src="${entry.key.image}"
                                     alt="${entry.key.nom}"
                                     width="80"
                                     height="80"
                                     class="rounded me-3"
                                     style="object-fit: cover;">

                                <div>

                                    <strong>
                                            ${entry.key.nom}
                                    </strong>

                                    <br>

                                    <span class="text-muted small">

                                            ${entry.key.description}

                                    </span>

                                </div>

                            </div>

                        </td>

                            <%-- PRIX UNITAIRE --%>
                        <td>

                            <fmt:formatNumber
                                    value="${entry.key.prix}"
                                    type="number"
                                    minFractionDigits="2"
                                    maxFractionDigits="2"/>

                            $

                        </td>

                            <%-- QUANTITE --%>
                        <td>

                                ${entry.value}

                        </td>

                            <%-- TOTAL LIGNE --%>
                        <td>

                            <fmt:formatNumber
                                    value="${entry.key.prix * entry.value}"
                                    type="number"
                                    minFractionDigits="2"
                                    maxFractionDigits="2"/>

                            $

                        </td>

                            <%-- ACTIONS --%>
                        <td>

                            <a href="${pageContext.request.contextPath}/panier?action=supprimer&id=${entry.key.id}"
                               class="btn btn-sm btn-outline-danger">

                                Supprimer

                            </a>

                        </td>

                    </tr>

                </c:forEach>

                </tbody>

            </table>

            <%-- TOTAL PANIER --%>
            <div class="text-end mb-4">

                <h4>

                    Total :

                    <span class="text-success">

                        <fmt:formatNumber
                                value="${panier.total}"
                                type="number"
                                minFractionDigits="2"
                                maxFractionDigits="2"/>

                        $

                    </span>

                </h4>

            </div>

            <%-- ACTIONS PANIER --%>
            <div class="d-flex justify-content-between align-items-center">

                    <%-- CONTINUER LES ACHATS --%>
                <a href="${pageContext.request.contextPath}/catalogue"
                   class="btn btn-primary">

                    Continuer les achats

                </a>

                <div>

                        <%-- VIDER PANIER --%>
                    <a href="${pageContext.request.contextPath}/panier?action=vider"
                       class="btn btn-outline-secondary me-2">

                        Vider le panier

                    </a>

                        <%-- CHECKOUT --%>
                    <a href="${pageContext.request.contextPath}/checkout"
                       class="btn btn-success">

                        Passer la commande

                    </a>

                </div>

            </div>

        </c:when>

        <%-- PANIER VIDE --%>
        <c:otherwise>

            <div class="alert alert-info text-center my-5">

                Votre panier est vide.

                <br>

                <a href="${pageContext.request.contextPath}/catalogue"
                   class="btn btn-primary mt-3">

                    Voir les produits

                </a>

            </div>

        </c:otherwise>

    </c:choose>

</div>

<jsp:include page="/WEB-INF/includes/footer.jsp"/>

</body>

</html>
