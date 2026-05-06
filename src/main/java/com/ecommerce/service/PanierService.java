package com.ecommerce.service;

import com.ecommerce.model.Panier;
import com.ecommerce.model.Produit;

import jakarta.servlet.http.HttpSession;

public class PanierService {

    // récupérer ou créer panier
    public Panier getPanier(HttpSession session) {

        Panier panier = (Panier) session.getAttribute("panier");

        if (panier == null) {
            panier = new Panier();
            session.setAttribute("panier", panier);
        }

        return panier;
    }

    // ajouter produit
    public void ajouterProduit(HttpSession session, Produit produit) {
        Panier panier = getPanier(session);
        panier.ajouterProduit(produit);
    }

    // modifier quantité
    public void modifierQuantite(HttpSession session, Produit produit, int quantite) {
        Panier panier = getPanier(session);
        panier.modifierQuantite(produit, quantite);
    }

    // supprimer produit
    public void supprimerProduit(HttpSession session, Produit produit) {
        Panier panier = getPanier(session);
        panier.supprimerProduit(produit);
    }

    // vider panier
    public void viderPanier(HttpSession session) {
        session.removeAttribute("panier");
    }

    // total
    public double getTotal(HttpSession session) {
        Panier panier = getPanier(session);
        return panier.getTotal();
    }
}