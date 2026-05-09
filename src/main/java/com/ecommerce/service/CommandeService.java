package com.ecommerce.service;

import com.ecommerce.model.Commande;
import com.ecommerce.model.Panier;
import com.ecommerce.model.Produit;
import com.ecommerce.model.Utilisateur;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Map;

public class CommandeService {

    // Connexion JPA
    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("ecommercePU");

    // Créer une commande
    public void creerCommande(Utilisateur utilisateur,
                              Panier panier) {

        EntityManager em = emf.createEntityManager();

        try {

            // Nouvelle commande
            Commande commande = new Commande();

            // Utilisateur connecté
            commande.setUtilisateur(utilisateur);

            // Ajouter produits du panier
            for (Map.Entry<Produit, Integer> entry
                    : panier.getProduits().entrySet()) {

                Produit produit = entry.getKey();

                commande.getProduits().add(produit);
            }

            // Total commande
            commande.setPrixTotal(
                    panier.getTotal()
            );

            em.getTransaction().begin();

            // Sauvegarder DB
            em.persist(commande);

            em.getTransaction().commit();

        } finally {

            em.close();
        }
    }

    // Historique utilisateur
    public List<Commande> historique(Utilisateur utilisateur) {

        EntityManager em = emf.createEntityManager();

        try {

            return em.createQuery(
                            "SELECT c FROM Commande c " +
                                    "WHERE c.utilisateur.id = :id",
                            Commande.class
                    )
                    .setParameter(
                            "id",
                            utilisateur.getId()
                    )
                    .getResultList();

        } finally {

            em.close();
        }
    }
}