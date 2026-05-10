<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/includes/header.jsp"/>

<div class="container mt-5">
    <h2>Gestion des commandes</h2>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID Commande</th>
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
                <td>${commande.prixTotal} €</td>
                <td>
                    <ul>
                        <c:forEach var="produit" items="${commande.produits}">
                            <li>${produit.nom} - ${produit.prix} €</li>
                        </c:forEach>
                    </ul>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="/WEB-INF/includes/footer.jsp"/>
