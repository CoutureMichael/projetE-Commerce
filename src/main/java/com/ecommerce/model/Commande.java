package com.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
    @Table(name = "commandes")
    public class Commande implements Serializable {

        @Id
        @GeneratedValue
        private int id;

        @Column(nullable = false)
        private LocalDateTime dateCommande = LocalDateTime.now();

        @Min(0)
        @Column(nullable = false)
        private double prixTotal;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "utilisateur_id", nullable = false)
        private Utilisateur utilisateur;

    }
