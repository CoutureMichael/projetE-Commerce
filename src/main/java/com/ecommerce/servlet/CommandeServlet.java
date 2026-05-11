package com.ecommerce.servlet;

import com.ecommerce.model.Commande;
import com.ecommerce.model.Panier;
import com.ecommerce.model.Utilisateur;

import com.ecommerce.service.CommandeService;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {
        "/commande",
        "/historique-commandes",
        "/confirmation",
        "/checkout"
})
public class CommandeServlet extends HttpServlet {

    // Service commande
    private CommandeService commandeService = new CommandeService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();

        // HISTORIQUE
        if (path.equals("/historique-commandes")) {

            afficherHistorique(request, response);

        }
        // CONFIRMATION
        else if (path.equals("/confirmation")) {

<<<<<<< HEAD
            request.getRequestDispatcher("/WEB-INF/views/commande/confirmation.jsp")
                    .forward(request, response);
=======
            request.getRequestDispatcher(
                    "/WEB-INF/views/commande/confirmation.jsp"
            ).forward(request, response);
>>>>>>> 6299a66c75a8ae86ce7fbeb9035d805af23f4824
        }
        // CHECKOUT
        else if (path.equals("/checkout")) {

<<<<<<< HEAD
            request.getRequestDispatcher("/WEB-INF/views/commande/checkout.jsp")
                    .forward(request, response);
=======
            request.getRequestDispatcher(
                    "/WEB-INF/views/commande/checkout.jsp"
            ).forward(request, response);
>>>>>>> 6299a66c75a8ae86ce7fbeb9035d805af23f4824
        }
        // VALIDER COMMANDE
        else {
            validerCommande(request, response);
        }
    }

    // Valider panier et créer commande
    private void validerCommande(HttpServletRequest request,
                                 HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession();

        // Utilisateur connecté
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");

        // Panier session
        Panier panier = (Panier) session.getAttribute("panier");

        // Vérifie panier vide
        if (panier == null ||
                panier.getProduits().isEmpty()) {

<<<<<<< HEAD
            response.sendRedirect(request.getContextPath() + "/panier");
=======
            response.sendRedirect(
                    request.getContextPath()
                            + "/panier"
            );
>>>>>>> 6299a66c75a8ae86ce7fbeb9035d805af23f4824

            return;
        }

        // Création commande
<<<<<<< HEAD
        commandeService.creerCommande(utilisateur, panier);
=======
        commandeService.creerCommande(
                utilisateur,
                panier
        );
>>>>>>> 6299a66c75a8ae86ce7fbeb9035d805af23f4824

        // Vider panier
        session.removeAttribute("panier");

<<<<<<< HEAD
        response.sendRedirect(request.getContextPath() + "/confirmation");
=======
        response.sendRedirect(
                request.getContextPath()
                        + "/confirmation"
        );
>>>>>>> 6299a66c75a8ae86ce7fbeb9035d805af23f4824
    }

    // Afficher historique utilisateur
    private void afficherHistorique(HttpServletRequest request,
                                    HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // Utilisateur connecté
<<<<<<< HEAD
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
=======
        Utilisateur utilisateur =
                (Utilisateur) session.getAttribute("utilisateur");
>>>>>>> 6299a66c75a8ae86ce7fbeb9035d805af23f4824

        // Liste commandes
        List<Commande> commandes = commandeService.historique(utilisateur);

<<<<<<< HEAD
        request.setAttribute("commandes", commandes);

        request.getRequestDispatcher("/WEB-INF/views/commande/historique.jsp")
                .forward(request, response);
=======
        request.setAttribute(
                "commandes",
                commandes
        );

        request.getRequestDispatcher(
                "/WEB-INF/views/commande/historique.jsp"
        ).forward(request, response);
>>>>>>> 6299a66c75a8ae86ce7fbeb9035d805af23f4824
    }
}
