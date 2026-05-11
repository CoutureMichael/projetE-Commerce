<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Mon profil</title>

    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>

<body class="bg-light">

<jsp:include page="/WEB-INF/includes/header.jsp"/>

<div class="container mt-5">

    <div class="row justify-content-center">

        <div class="col-md-6">

            <div class="card shadow">

                <div class="card-header bg-dark text-white">
                    <h3 class="mb-0">
                        Mon Profil
                    </h3>
                </div>

                <div class="card-body">

                    <div class="mb-3">
                        <label class="form-label fw-bold">
                            Nom:
                        </label>

                        <p class="form-control">
                            ${sessionScope.utilisateur.nom}
                        </p>
                    </div>

                    <div class="mb-3">
                        <label class="form-label fw-bold">
                            Email:
                        </label>

                        <p class="form-control">
                            ${sessionScope.utilisateur.email}
                        </p>
                    </div>

                    <div class="mb-3">
                        <label class="form-label fw-bold">
                            Rôle:
                        </label>

                        <p class="form-control">
                            ${sessionScope.utilisateur.role}
                        </p>
                    </div>

                    <div class="text-center mt-4">

                        <a href="${pageContext.request.contextPath}/catalogue"
                           class="btn btn-primary">

                            Retour au catalogue

                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
