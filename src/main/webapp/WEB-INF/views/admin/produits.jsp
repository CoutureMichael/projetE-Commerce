<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/includes/header.jsp"/>

<div class="container mt-5">
    <h2>Gestion des produits</h2>

    <!-- Bouton ajouter produit -->
    <div class="mb-3">
        <a href="${pageContext.request.contextPath}/admin/produit-form" class="btn btn-success">Ajouter un produit</a>
    </div>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Nom</th>
            <th>Description</th>
            <th>Prix</th>
            <th>Catégorie</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="produit" items="${produits}">
            <tr>
                <td>${produit.nom}</td>
                <td>${produit.description}</td>
                <td>${produit.prix}</td>
                <td>${produit.categorie}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/produit-form?id=${produit.id}" class="btn btn-primary btn-sm">Modifier</a>
                    <a href="${pageContext.request.contextPath}/admin/produit-delete?id=${produit.id}" class="btn btn-danger btn-sm"
                       onclick="return confirm('Voulez-vous vraiment supprimer ce produit ?');">Supprimer</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="/WEB-INF/includes/footer.jsp"/>
