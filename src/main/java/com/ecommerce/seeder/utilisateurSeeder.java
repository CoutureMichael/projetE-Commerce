package com.ecommerce.seeder;
import com.ecommerce.model.Commande;
import com.ecommerce.model.Utilisateur;
import com.ecommerce.service.AuthService;
import java.util.ArrayList;
import java.util.List;
public class utilisateurSeeder {
    public static void main(String[] args) {
        // Service auth
        AuthService authService = new AuthService();
        // ADMIN
        if (authService.findByEmail("admin@gmail.com") == null) {
            List<Commande> commandesAdmin =
                    new ArrayList<>();
            Utilisateur admin =
                    new Utilisateur(
                            0,
                            "Administrateur",
                            "admin@gmail.com",
                            "admin123",
                            Utilisateur.Role.ADMIN,
                            commandesAdmin
                    );
            authService.ajouter(admin);
            System.out.println("Admin créé !");
        }
        // USER
        if (authService.findByEmail("user@gmail.com") == null) {
            List<Commande> commandesUser =
                    new ArrayList<>();
            Utilisateur user =
                    new Utilisateur(
                            0,
                            "Michael",
                            "user@gmail.com",
                            "user123",
                            Utilisateur.Role.USER,
                            commandesUser
                    );
            authService.ajouter(user);
            System.out.println("Utilisateur créé !");
        }
    }
}