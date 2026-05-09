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
        "/historique-commandes"
})
public class CommandeServlet extends HttpServlet {

    // Service commande
    private CommandeService commandeService =
            new CommandeService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String path =
                request.getServletPath();

        // HISTORIQUE
        if (path.equals("/historique-commandes")) {

            afficherHistorique(request, response);

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

        HttpSession session =
                request.getSession();

        // Utilisateur connecté
        Utilisateur utilisateur =
                (Utilisateur) session.getAttribute("utilisateur");

        // Panier session
        Panier panier =
                (Panier) session.getAttribute("panier");

        // Vérifie panier vide
        if (panier == null ||
                panier.getProduits().isEmpty()) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/panier"
            );

            return;
        }

        // Création commande
        commandeService.creerCommande(
                utilisateur,
                panier
        );

        // Vider panier
        session.removeAttribute("panier");

        response.sendRedirect(
                request.getContextPath()
                        + "/historique-commandes"
        );
    }

    // Afficher historique utilisateur
    private void afficherHistorique(HttpServletRequest request,
                                    HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession();

        // Utilisateur connecté
        Utilisateur utilisateur =
                (Utilisateur) session.getAttribute("utilisateur");

        // Liste commandes
        List<Commande> commandes =
                commandeService.historique(utilisateur);

        request.setAttribute(
                "commandes",
                commandes
        );

        request.getRequestDispatcher(
                "/WEB-INF/views/commande/historique.jsp"
        ).forward(request, response);
    }
}