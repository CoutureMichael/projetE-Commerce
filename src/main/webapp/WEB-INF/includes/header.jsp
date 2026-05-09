<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>navbar</title>
    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand" href="catalogue">E-commerce</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="catalogue">Produits</a>
                </li>
                <c:if test="${sessionScope.utilisateur != null}">
                    <li class="nav-item">
                        <a class="nav-link" href="historique-commandes">Mes commandes</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.utilisateur != null && sessionScope.utilisateur.role == 'ADMIN'}">
                    <li class="nav-item">
                        <a class="nav-link" href="admin/produits">Admin</a>
                    </li>
                </c:if>
            </ul>
            <ul class="navbar-nav align-items-center">
                <!-- Icône panier à droite -->
                <li class="nav-item me-2">
                    <a class="nav-link position-relative" href="${pageContext.request.contextPath}/panier" title="Panier">
                        <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="#495057" class="bi bi-cart3" viewBox="0 0 16 16">
                            <path d="M0 1.5A.5.5 0 0 1 .5 1h1a.5.5 0 0 1 .485.379L2.89 5H14.5a.5.5 0 0 1 .49.598l-1.5 7A.5.5 0 0 1 13 13H4a.5.5 0 0 1-.491-.408L1.01 2H.5a.5.5 0 0 1-.5-.5ZM3.102 6l1.313 6h8.17l1.313-6H3.102Z"/>
                            <circle cx="6" cy="15" r="1"/>
                            <circle cx="12" cy="15" r="1"/>
                        </svg>
                        <!-- Badge quantité panier -->
                        <c:if test="${not empty sessionScope.panier && sessionScope.panier.produits.size() > 0}">
                        <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                                ${sessionScope.panier.produits.size()}
                        </span>
                        </c:if>
                    </a>
                </li>
                <!-- Zone utilisateur -->
                <c:choose>
                    <c:when test="${sessionScope.utilisateur != null}">
                        <li class="nav-item">
                            <span class="navbar-text me-2">Bonjour, ${sessionScope.utilisateur.nom}</span>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="logout">Déconnexion</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link" href="login">Connexion</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>

<!-- Bootstrap bundle JS for navbar hamburger -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>