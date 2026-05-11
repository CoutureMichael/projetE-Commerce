<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Mon profil</title>
</head>
<body class="bg-light">

<jsp:include page="/WEB-INF/includes/header.jsp"/>

<div class="container mt-5 pb-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow">

                <div class="card-header bg-dark text-white">
                    <h3 class="mb-0">Mon Profil</h3>
                </div>

                <div class="card-body">

                    <c:if test="${not empty succes}">
                        <div class="alert alert-success">${succes}</div>
                    </c:if>
                    <c:if test="${not empty erreur}">
                        <div class="alert alert-danger">${erreur}</div>
                    </c:if>

                    <form method="post" action="${pageContext.request.contextPath}/profil">

                        <div class="mb-3">
                            <label class="form-label fw-bold">Nom</label>
                            <input type="text" class="form-control" name="nom"
                                   value="${sessionScope.utilisateur.nom}" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label fw-bold">Email</label>
                            <input type="email" class="form-control" name="email"
                                   value="${sessionScope.utilisateur.email}" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label fw-bold">Rôle</label>
                            <p class="form-control bg-light">${sessionScope.utilisateur.role}</p>
                        </div>

                        <div class="d-flex justify-content-between mt-4">
                            <a href="${pageContext.request.contextPath}/catalogue"
                               class="btn btn-secondary">Retour</a>
                            <button type="submit" class="btn btn-primary">Enregistrer</button>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/includes/footer.jsp"/>
</body>
</html>
