package com.ecommerce.service;

import com.ecommerce.model.Produit;
import com.ecommerce.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class ProduitService {

    public Produit findById(Long id) {

        EntityManager em = JPAUtil.getEntityManager();

        Produit produit = em.find(Produit.class, id);

        em.close();

        return produit;
    }
}
