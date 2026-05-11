<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/includes/header.jsp"/>

<div class="container mt-5">
    <div class="row justify-content-md-center">
        <div class="col-md-6">
            <div class="card p-4 shadow-sm">
                <h2 class="text-center mb-4">
                    <c:choose>
                        <c:when test="${produit != null && produit.id != null}">Modifier le produit</c:when>
                        <c:otherwise>Ajouter un produit</c:otherwise>
                    </c:choose>
                </h2>

                <!-- Message d'erreur -->
                <c:if test="${not empty erreur}">
                    <div class="alert alert-danger">${erreur}</div>
                </c:if>

                <form method="post" action="${pageContext.request.contextPath}/admin/produits">
                    <input type="hidden" name="id" value="${produit != null ? produit.id : ''}"/>

                    <div class="mb-3">
                        <label for="nom" class="form-label">Nom</label>
                        <input type="text" class="form-control" id="nom" name="nom"
                               value="${produit != null ? produit.nom : ''}" required>
                    </div>

                    <div class="mb-3">
                        <label for="description" class="form-label">Description</label>
                        <textarea class="form-control" id="description" name="description" rows="3">${produit != null ? produit.description : ''}</textarea>
                    </div>

                    <div class="mb-3">
                        <label for="prix" class="form-label">Prix</label>
                        <input type="number" class="form-control" id="prix" name="prix" step="0.01"
                               value="${produit != null ? produit.prix : ''}" required>
                    </div>

                    <div class="mb-3">
                        <label for="image" class="form-label">Image (URL)</label>
                        <input type="text" class="form-control" id="image" name="image"
                               value="${produit != null ? produit.image : ''}">
                    </div>

                    <div class="mb-3">
                        <label for="categorie" class="form-label">Catégorie</label>
                        <input type="text" class="form-control" id="categorie" name="categorie"
                               value="${produit != null ? produit.categorie : ''}" required>
                    </div>

                    <button type="submit" class="btn btn-primary w-100">
                        <c:choose>
                            <c:when test="${produit != null && produit.id != null}">Mettre à jour</c:when>
                            <c:otherwise>Ajouter</c:otherwise>
                        </c:choose>
                    </button>
                </form>

                <div class="mt-3 text-center">
                    <a href="${pageContext.request.contextPath}/admin/produits">Retour à la liste des produits</a>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/includes/footer.jsp"/>
