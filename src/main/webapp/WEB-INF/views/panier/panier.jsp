<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Panier</title>
</head>
<body>
<jsp:include page="/WEB-INF/includes/header.jsp"/>

<div class="container my-5 pb-5">
    <h2 class="mb-4">Mon panier</h2>

    <c:choose>
        <c:when test="${not empty panier.produits}">
            <table class="table table-bordered align-middle bg-white">
                <thead>
                <tr>
                    <th>Produit</th>
                    <th>Prix unitaire</th>
                    <th>Quantité</th>
                    <th>Total</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="entry" items="${panier.produits}">
                    <tr>
                        <td>
                            <strong>${entry.key.nom}</strong><br>
                            <span class="text-muted small">${entry.key.description}</span>
                        </td>
                        <td>
                            <fmt:formatNumber value="${entry.key.prix}" type="currency" currencySymbol="$"/>
                        </td>
                        <td>${entry.value}</td>
                        <td>
                            <fmt:formatNumber value="${entry.key.prix * entry.value}" type="currency" currencySymbol="$"/>
                        </td>
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

            <div class="text-end mb-4">
                <h4>
                    Total :
                    <span class="text-success">
                        <fmt:formatNumber value="${panier.total}" type="currency" currencySymbol="$"/>
                    </span>
                </h4>
            </div>

            <div class="d-flex justify-content-between">
                <a href="${pageContext.request.contextPath}/panier?action=vider" class="btn btn-outline-secondary">
                    Vider le panier
                </a>
                <a href="${pageContext.request.contextPath}/commande" class="btn btn-success">
                    Passer la commande
                </a>
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-info text-center my-5">
                Votre panier est vide.<br>
                <a href="${pageContext.request.contextPath}/catalogue" class="btn btn-primary mt-3">
                    Voir les produits
                </a>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<jsp:include page="/WEB-INF/includes/footer.jsp"/>
</body>
</html>
