package com.ecommerce.servlet;

import com.ecommerce.model.Produit;
import com.ecommerce.service.ProduitService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {
        "/admin/produits",
        "/admin/produit-form",
        "/admin/produit-save",
        "/admin/produit-delete"
})
public class AdminServlet extends HttpServlet {

    private ProduitService produitService =
            new ProduitService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();

        switch (path) {

            // Liste admin produits
            case "/admin/produits":

                List<Produit> produits =
                        produitService.findAll();

                request.setAttribute(
                        "produits",
                        produits
                );

                request.getRequestDispatcher(
                        "/WEB-INF/views/admin/produits.jsp"
                ).forward(request, response);

                break;

            // Formulaire ajout/modification
            case "/admin/produit-form":

                String idParam =
                        request.getParameter("id");

                if (idParam != null) {

                    Long id =
                            Long.parseLong(idParam);

                    Produit produit =
                            produitService.findById(id);

                    request.setAttribute(
                            "produit",
                            produit
                    );
                }

                request.getRequestDispatcher(
                        "/WEB-INF/views/admin/produit-form.jsp"
                ).forward(request, response);

                break;

            // Supprimer produit
            case "/admin/produit-delete":

                Long id =
                        Long.parseLong(
                                request.getParameter("id")
                        );

                produitService.supprimer(id);

                response.sendRedirect(
                        request.getContextPath()
                                + "/admin/produits"
                );

                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String nom =
                request.getParameter("nom");

        String description =
                request.getParameter("description");

        double prix =
                Double.parseDouble(
                        request.getParameter("prix")
                );

        String image =
                request.getParameter("image");

        String categorie =
                request.getParameter("categorie");

        Produit produit = new Produit(
                nom,
                description,
                prix,
                image,
                categorie
        );

        String idParam =
                request.getParameter("id");

        // MODIFIER
        if (idParam != null &&
                !idParam.isEmpty()) {

            produit.setId(
                    Long.parseLong(idParam)
            );

            produitService.modifier(produit);

        }
        // AJOUTER
        else {

            produitService.ajouter(produit);
        }

        response.sendRedirect(
                request.getContextPath()
                        + "/admin/produits"
        );
    }
}