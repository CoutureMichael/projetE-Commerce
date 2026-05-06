package com.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.io.Serializable;

@Entity
@Table(name = "commandes_online")
public class CommandeOnline implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;

    @Min(1)
    @Column(nullable = false)
    private int quantite;

    @Min(0)
    @Column(nullable = false)
    private double montant;

    public CommandeOnline() {}

    public CommandeOnline(int id, Commande commande, Produit produit, int quantite, double montant) {
        this.id = id;
        this.commande = commande;
        this.produit = produit;
        this.quantite = quantite;
        this.montant = montant;
    }

    public double calculerSousTotal() { return montant * quantite;}

    public int getId() { return id;}
    public void setId(int id) { this.id = id; }

    public Commande getCommande() { return commande;}
    public void setCommande(Commande commande) { this.commande = commande; }

    public Produit getProduit() { return produit; }
    public void setProduit(Produit produit) { this.produit = produit; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }

    public double getMontant() { return montant; }
    public void setMontant(double montant) { this.montant = montant; }
}
