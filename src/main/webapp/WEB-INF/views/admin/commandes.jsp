<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>Gestion des commandes</title>
</head>
<body>
<jsp:include page="/WEB-INF/includes/header.jsp"/>

<div class="container my-5 pb-5">
    <h2 class="mb-4">Gestion des commandes</h2>

    <c:choose>
        <c:when test="${not empty commandes}">
            <table class="table table-striped table-bordered align-middle bg-white shadow-sm">
                <thead class="table-light">
                <tr>
                    <th>#</th>
                    <th>Utilisateur</th>
                    <th>Date</th>
                    <th>Total</th>
                    <th>Produits</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="commande" items="${commandes}">
                    <tr>
                        <td>${commande.id}</td>
                        <td>${commande.utilisateur.nom} (${commande.utilisateur.email})</td>
                        <td>${commande.dateCommande}</td>
                        <td>
                            <fmt:formatNumber value="${commande.prixTotal}" type="number"
                                              minFractionDigits="2" maxFractionDigits="2"/> $
                        </td>
                        <td>
                            <ul class="mb-0">
                                <c:forEach var="produit" items="${commande.produits}">
                                    <li>${produit.nom} — ${produit.prix} $</li>
                                </c:forEach>
                            </ul>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <div class="alert alert-info text-center">Aucune commande pour le moment.</div>
        </c:otherwise>
    </c:choose>
</div>

<jsp:include page="/WEB-INF/includes/footer.jsp"/>
</body>
</html>
