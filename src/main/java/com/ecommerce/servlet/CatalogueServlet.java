package com.ecommerce.servlet;

import com.ecommerce.model.Produit;
import com.ecommerce.service.ProduitService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {
        "/catalogue",
        "/produit-detail"
})
public class CatalogueServlet extends HttpServlet {

<<<<<<< HEAD
    private ProduitService produitService = new ProduitService();
=======
    private ProduitService produitService =
            new ProduitService();
>>>>>>> 6299a66c75a8ae86ce7fbeb9035d805af23f4824

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();

        // DETAIL PRODUIT
        if (path.equals("/produit-detail")) {

<<<<<<< HEAD
            Long id = Long.parseLong(request.getParameter("id"));

            Produit produit = produitService.findById(id);

            request.setAttribute("produit", produit);

            request.getRequestDispatcher("/WEB-INF/views/catalogue/detail.jsp")
                    .forward(request, response);
=======
            Long id = Long.parseLong(
                    request.getParameter("id")
            );

            Produit produit =
                    produitService.findById(id);

            request.setAttribute("produit", produit);

            request.getRequestDispatcher(
                    "/WEB-INF/views/catalogue/detail.jsp"
            ).forward(request, response);
>>>>>>> 6299a66c75a8ae86ce7fbeb9035d805af23f4824

        }
        // LISTE PRODUITS
        else {

            String recherche = request.getParameter("recherche");

            List<Produit> produits;

            // Si recherche
<<<<<<< HEAD
            if (recherche != null && !recherche.isEmpty()) {
=======
            if (recherche != null &&
                    !recherche.isEmpty()) {
>>>>>>> 6299a66c75a8ae86ce7fbeb9035d805af23f4824

                produits = produitService.rechercher(recherche);

            } else {

                produits = produitService.findAll();
            }

            request.setAttribute("produits", produits);

            request.getRequestDispatcher(
                    "/WEB-INF/views/catalogue/liste.jsp"
            ).forward(request, response);
        }
    }
}