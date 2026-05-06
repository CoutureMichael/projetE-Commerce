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

        // récupérer panier
        Panier panier = (Panier) session.getAttribute("panier");

        if (panier == null) {
            panier = new Panier();
            session.setAttribute("panier", panier);
        }

        // action
        String action = request.getParameter("action");

        if (action != null) {

            int idProduit = 0;
            if (request.getParameter("id") != null) {
                idProduit = Integer.parseInt(request.getParameter("id"));
            }

            Produit produit = produitService.findById((long) idProduit);

            switch (action) {

                case "ajouter":
                    panier.ajouterProduit(produit);
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

        // envoyer au JSP
        request.setAttribute("panier", panier);

        request.getRequestDispatcher("/WEB-INF/views/panier/panier.jsp")
                .forward(request, response);
    }
}
