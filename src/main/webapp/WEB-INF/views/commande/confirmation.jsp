<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>

    <title>Confirmation</title>

    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">

</head>

<body class="bg-light">

<jsp:include page="/WEB-INF/includes/header.jsp"/>

<div class="container mt-5">

    <div class="card shadow text-center">

        <div class="card-body p-5">

            <h1 class="text-success mb-4">
                Commande confirmée !
            </h1>

            <p class="lead">

                Merci pour votre achat.

            </p>

            <p>

                Votre commande a été enregistrée avec succès.

            </p>

            <div class="mt-4">

                <a href="${pageContext.request.contextPath}/historique-commandes"
                   class="btn btn-primary me-2">

                    Voir mes commandes

                </a>

                <a href="${pageContext.request.contextPath}/catalogue"
                   class="btn btn-outline-secondary">

                    Retour au catalogue

                </a>

            </div>

        </div>

    </div>

</div>

<jsp:include page="/WEB-INF/includes/footer.jsp"/>

</body>

</html>
