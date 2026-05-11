<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!-- Bootstrap 5 CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap bundle JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<nav class="navbar navbar-expand-lg navbar-light bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/catalogue">E-commerce</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <!-- Liens ADMIN -->
                <c:if test="${sessionScope.utilisateur != null
                             && sessionScope.utilisateur.role == 'ADMIN'}">
<<<<<<< HEAD
=======

>>>>>>> 6299a66c75a8ae86ce7fbeb9035d805af23f4824
                    <li class="nav-item">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/catalogue">
                            Catalogue
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/admin/produits">
                            Gérer les produits
                        </a>
                    </li>
<<<<<<< HEAD
=======

>>>>>>> 6299a66c75a8ae86ce7fbeb9035d805af23f4824
                    <li class="nav-item">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/admin/commandes">
                            Voir les commandes
                        </a>
                    </li>
                </c:if>
<<<<<<< HEAD
                <!-- Liens USER -->
                <c:if test="${sessionScope.utilisateur != null
                             && sessionScope.utilisateur.role != 'ADMIN'}">
                    <li class="nav-item">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/catalogue">
                            Catalogue
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/historique-commandes">
                            Mes commandes
                        </a>
                    </li>
                </c:if>
            </ul>
            <ul class="navbar-nav align-items-center">
                <c:if test="${sessionScope.utilisateur != null && sessionScope.utilisateur.role != 'ADMIN'}">
                <li class="nav-item me-2">
                    <a class="nav-link" href="${pageContext.request.contextPath}/profil" title="Mon profil">
                        <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="#495057" class="bi bi-person-circle" viewBox="0 0 16 16">
                            <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
                            <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8m8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1"/>
                        </svg>
                    </a>
                </li>
                </c:if>
=======
            </ul>
            <ul class="navbar-nav align-items-center">
>>>>>>> 6299a66c75a8ae86ce7fbeb9035d805af23f4824
                <li class="nav-item me-2">
                    <a class="nav-link position-relative" href="${pageContext.request.contextPath}/panier" title="Panier">
                        <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="#495057" class="bi bi-cart3" viewBox="0 0 16 16">
                            <path d="M0 1.5A.5.5 0 0 1 .5 1h1a.5.5 0 0 1 .485.379L2.89 5H14.5a.5.5 0 0 1 .49.598l-1.5 7A.5.5 0 0 1 13 13H4a.5.5 0 0 1-.491-.408L1.01 2H.5a.5.5 0 0 1-.5-.5ZM3.102 6l1.313 6h8.17l1.313-6H3.102Z"/>
                            <circle cx="6" cy="15" r="1"/>
                            <circle cx="12" cy="15" r="1"/>
                        </svg>
                        <c:if test="${not empty sessionScope.panier && sessionScope.panier.produits.size() > 0}">
                        <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                                ${sessionScope.panier.produits.size()}
                        </span>
                        </c:if>
                    </a>
                </li>
                <c:choose>

                    <c:when test="${sessionScope.utilisateur != null}">
                        <li class="nav-item">
                            <span class="navbar-text me-2">
                                Bonjour, ${sessionScope.utilisateur.nom}
                            </span>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link"
                               href="${pageContext.request.contextPath}/logout">
                                Déconnexion
                            </a>
                        </li>
                    </c:when>

                    <c:otherwise>
                    <li class="nav-item">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/login">
                            Connexion
                        </a>
                    </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>
