package com.ecommerce.servlet;

import com.ecommerce.model.Panier;
import com.ecommerce.model.Produit;
import com.ecommerce.service.ProduitService;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;

@WebServlet("/panier")
public class PanierServlet extends HttpServlet {

    private ProduitService produitService = new ProduitService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // Récupérer le panier de la session
        Panier panier = (Panier) session.getAttribute("panier");

        // Si aucun panier existe, on le crée
        if (panier == null) {
            panier = new Panier();
            session.setAttribute("panier", panier);
        }

        // Récupérer l'action : ajouter, supprimer, modifier, vider
        String action = request.getParameter("action");

        if (action != null) {

            Produit produit = null;

            // Seulement "vider" n'a pas besoin d'id produit
            if (!action.equals("vider")) {

                String idParam = request.getParameter("id");

                if (idParam == null || idParam.isEmpty()) {
                    response.sendRedirect(request.getContextPath() + "/panier");
                    return;
                }

                Long idProduit = Long.parseLong(idParam);

                produit = produitService.findById(idProduit);

                if (produit == null) {
                    response.sendRedirect(request.getContextPath() + "/panier");
                    return;
                }
            }

            switch (action) {

                case "ajouter":

                    System.out.println("Ajout produit : " + produit.getNom());

                    panier.ajouterProduit(produit);

                    int quantiteTotale = 0;

                    for (Integer q : panier.getProduits().values()) {
                        quantiteTotale += q;
                    }

                    System.out.println("Nombre total d'articles dans le panier : " + quantiteTotale);

                    break;

                case "supprimer":
                    panier.supprimerProduit(produit);
                    break;

                case "modifier":
                    int quantite = Integer.parseInt(request.getParameter("quantite"));
                    panier.modifierQuantite(produit, quantite);
                    break;

                case "vider":
                    session.removeAttribute("panier");
                    panier = new Panier();
                    session.setAttribute("panier", panier);
                    break;
            }
        }

        // Envoyer le panier à la JSP
        request.setAttribute("panier", panier);

        request.getRequestDispatcher("/WEB-INF/views/panier/panier.jsp")
                .forward(request, response);
    }
}
