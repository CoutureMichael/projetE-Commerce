package com.ecommerce.model;

import java.util.HashMap;
import java.util.Map;

public class Panier {

    private int id; // optionnel (pas utilisé en session)
    private Utilisateur utilisateur; // optionnel (si connecté)

    // Produit + quantité (comme ligne panier)
    private Map<Produit, Integer> produits = new HashMap<>();

    public Panier() {}

    public int getId() { return id; }

    public Utilisateur getUtilisateur() { return utilisateur;}

    public Map<Produit, Integer> getProduits() { return produits; }

    public void setId(int id) { this.id = id; }

    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur;}

    // ajouter produit
    public void ajouterProduit(Produit produit) {

        for (Produit p : produits.keySet()) {

            if (p.getId().equals(produit.getId())) {

                produits.put(
                        p,
                        produits.get(p) + 1
                );

                return;
            }
        }

        produits.put(produit, 1);
    }

    // modifier quantité
    public void modifierQuantite(Produit produit, int quantite) {
        if (quantite <= 0) {
            produits.remove(produit);
        } else {
            produits.put(produit, quantite);
        }
    }

    // supprimer produit
    public void supprimerProduit(Produit produit) {
        produits.remove(produit);
    }

    // total
    public double getTotal() {
        double total = 0;

        for (Map.Entry<Produit, Integer> entry : produits.entrySet()) {
            total += entry.getKey().getPrix() * entry.getValue();
        }

        return total;
    }
}

