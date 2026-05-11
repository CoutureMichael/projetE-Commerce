package com.ecommerce.service;

import com.ecommerce.model.Utilisateur;
import com.ecommerce.util.PasswordHasher;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

public class AuthService {

    // Connexion JPA
    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("ecommercePU");

    // Trouver utilisateur par email
    public Utilisateur findByEmail(String email) {

        EntityManager em = emf.createEntityManager();

        try {

            return em.createQuery(
                            "SELECT u FROM Utilisateur u WHERE u.email = :email",
                            Utilisateur.class
                    )
                    .setParameter("email", email)
                    .getSingleResult();

        }
        // Si aucun utilisateur trouvé
        catch (NoResultException e) {

            return null;

        } finally {

            em.close();
        }
    }

    // Inscription utilisateur
    public boolean inscription(String nom,
                               String email,
                               String password) {

        // Vérifie si email existe déjà
        if (findByEmail(email) != null) {
            return false;
        }

        // Création utilisateur
        Utilisateur utilisateur = new Utilisateur();

        utilisateur.setNom(nom);
        utilisateur.setEmail(email);
        utilisateur.setPassword(PasswordHasher.hashPassword(password)); // Password hashed

        // Role par défaut
        utilisateur.setRole(Utilisateur.Role.USER);

        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();

            em.persist(utilisateur);

            em.getTransaction().commit();

            return true;

        } finally {

            em.close();
        }
    }

    // Modifier nom et email
    public boolean modifier(int id, String nom, String email) {
        Utilisateur existing = findByEmail(email);
        if (existing != null && existing.getId() != id) {
            return false; // email déjà pris par un autre
        }
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Utilisateur u = em.find(Utilisateur.class, id);
            u.setNom(nom);
            u.setEmail(email);
            em.getTransaction().commit();
            return true;
        } finally {
            em.close();
        }
    }

    // Ajouter utilisateur
    public void ajouter(Utilisateur utilisateur) {
        EntityManager em =
                emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(utilisateur);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Connexion utilisateur
    public Utilisateur connexion(String email, String password) {

        // Recherche utilisateur
        Utilisateur utilisateur = findByEmail(email);

        // Utilisateur inexistant
        if (utilisateur == null) {
            return null;
        }

        // Vérification entre le password hasher et le password de l'utilisateur
        if (!PasswordHasher.verifyPassword(
                password,
                utilisateur.getPassword()
        )) {

            return null;
        }

        return utilisateur;
    }
}
