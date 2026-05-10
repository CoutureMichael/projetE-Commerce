package com.ecommerce.service;

import com.ecommerce.model.Produit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class ProduitService {

    // Connexion JPA
    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("ecommercePU");

    // Récupérer tous les produits
    public List<Produit> findAll() {

        EntityManager em = emf.createEntityManager();

        try {
            return em.createQuery(
                    "SELECT p FROM Produit p",
                    Produit.class
            ).getResultList();

        } finally {
            em.close();
        }
    }

    // Trouver un produit par son ID
    public Produit findById(Long id) {
        EntityManager em = emf.createEntityManager();

        try {
            return em.find(Produit.class, id);
        } finally {
            em.close();
        }
    }

    // Recherche par nom ou catégorie
    public List<Produit> rechercher(String motCle) {

        EntityManager em = emf.createEntityManager();

        try {

            return em.createQuery(
                            "SELECT p FROM Produit p " +
                                    "WHERE LOWER(p.nom) LIKE :motCle " +
                                    "OR LOWER(p.categorie) LIKE :motCle",
                            Produit.class
                    )
                    .setParameter("motCle",
                            "%" + motCle.toLowerCase() + "%")
                    .getResultList();

        } finally {
            em.close();
        }
    }

    // Ajouter produit
    public void ajouter(Produit produit) {

        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();

            em.persist(produit);

            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

    // Modifier produit
    public void modifier(Produit produit) {

        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();

            em.merge(produit);

            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

    // Supprimer produit
    public void supprimer(Long id) {

        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();

            Produit produit = em.find(Produit.class, id);

            if (produit != null) {
                em.remove(produit);
            }

            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }
}