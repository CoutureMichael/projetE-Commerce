package com.ecommerce.servlet;

import com.ecommerce.model.Utilisateur;
import com.ecommerce.service.AuthService;
import com.ecommerce.service.EmailService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(urlPatterns = {
        "/login",
        "/inscription",
        "/logout",
        "/profil"
})

public class AuthServlet extends HttpServlet {

    // Service auth
    private AuthService authService =
            new AuthService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();

        // PAGE LOGIN
        if (path.equals("/login")) {

            afficherLogin(request, response);

        }
        // PAGE INSCRIPTION
        else if (path.equals("/inscription")) {

            request.getRequestDispatcher(
                    "/WEB-INF/views/auth/inscription.jsp"
            ).forward(request, response);

        }
        // PAGE PROFIL
        else if (path.equals("/profil")) {

            request.getRequestDispatcher(
                    "/WEB-INF/views/auth/profil.jsp"
            ).forward(request, response);
        }
        // PAGE LOGOUT
        else if (path.equals("/logout")) {

            // Détruit session
            request.getSession().invalidate();

            response.sendRedirect(
                    request.getContextPath() + "/login"
            );
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();

        // CONNEXION
        if (path.equals("/login")) {

            connecter(request, response);

        }
        // INSCRIPTION
        else if (path.equals("/inscription")) {

            inscrire(request, response);
        }
    }

    // Afficher login
    private void afficherLogin(HttpServletRequest request,
                               HttpServletResponse response)
            throws ServletException, IOException {

        // Lire cookies
        Cookie[] cookies = request.getCookies();

        // Vérifie si cookie existe
        if (cookies != null) {

            for (Cookie cookie : cookies) {

                if (cookie.getName()
                        .equals("rememberEmail")) {

                    request.setAttribute(
                            "rememberEmail",
                            cookie.getValue()
                    );
                }
            }
        }

        request.getRequestDispatcher(
                "/WEB-INF/views/auth/login.jsp"
        ).forward(request, response);
    }

    // Connexion utilisateur
    private void connecter(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");

        String password = request.getParameter("password");

        String remember = request.getParameter("rememberMe");

        // Vérifie login
        Utilisateur utilisateur =
                authService.connexion(email, password);

        // Mauvais login
        if (utilisateur == null) {

            request.setAttribute(
                    "erreur",
                    "Email ou mot de passe invalide"
            );

            request.getRequestDispatcher(
                    "/WEB-INF/views/auth/login.jsp"
            ).forward(request, response);

            return;
        }

        // Création session
        HttpSession session = request.getSession();

        session.setAttribute(
                "utilisateur",
                utilisateur
        );

        session.setAttribute(
                "role",
                utilisateur.getRole().name()
        );

        // Cookie remember-me
        if (remember != null) {

            Cookie cookie = new Cookie("rememberEmail", email);

            // 7 jours
            cookie.setMaxAge(60 * 60 * 24 * 7);

            response.addCookie(cookie);
        }

        // Redirection selon le rôle
        if (utilisateur.getRole() == Utilisateur.Role.ADMIN) {
            response.sendRedirect(
                    request.getContextPath() + "/admin/produits"
            );
        } else {
            response.sendRedirect(
                    request.getContextPath() + "/catalogue"
            );
        }
    }

    // Inscription utilisateur
    private void inscrire(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String nom = request.getParameter("nom");

        String email = request.getParameter("email");

        String password = request.getParameter("password");

        // Création compte
        boolean success =
                authService.inscription(
                        nom,
                        email,
                        password
                );

        // Email existe déjà
        if (!success) {

            request.setAttribute(
                    "erreur",
                    "Cet email existe déjà"
            );

            request.getRequestDispatcher(
                    "/WEB-INF/views/auth/inscription.jsp"
            ).forward(request, response);

            return;
        }

        // Email service (Envoi Email)
        EmailService emailService = new EmailService();

        emailService.envoyerEmail(
                email,
                "Bienvenue sur E-Commerce",
                "Votre compte a été créé avec succès!"
        );

        // Retour login
        response.sendRedirect(
                request.getContextPath()
                        + "/login"
        );
    }
}
