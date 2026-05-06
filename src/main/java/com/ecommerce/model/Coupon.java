package com.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
    @Table(name = "coupons")
    public class Coupon  implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @NotBlank
        @Column(nullable = false, unique = true)
        private String code;

        @Min(0) @Max(100)
        @Column(nullable = false)
        private double pourcentageReduction;

        @Column(nullable = false)
        private boolean actif = true;

        public Coupon() {}

        public Coupon(int id, String code, double pourcentageReduction, boolean actif) {
            this.code = code;
            this.pourcentageReduction = pourcentageReduction;
            this.actif = actif;
        }

        /// peut-etre à bouger
        // Applique la réduction du coupon, aussi non retourne le montant inchangé
        public double appliquerReduction(double montant) {
            if (!actif) return montant;
            return montant - (montant * pourcentageReduction / 100);
        }

        // Getters / Setters
        public int getId() { return id;}
        public void setId(int id) { this.id = id; }

        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }

        public double getPourcentageReduction() { return pourcentageReduction; }
        public void setPourcentageReduction(double pourcentageReduction) { this.pourcentageReduction = pourcentageReduction;}

        public boolean isActif() { return actif;}
        public void setActif(boolean actif) { this.actif = actif; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Coupon))return false;
            Coupon coupon = (Coupon) o;
            return Objects.equals(code, coupon.code);
        }

        @Override
        public int hashCode() {
            return Objects.hash(code);
        }
    }
