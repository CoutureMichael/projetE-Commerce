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
<<<<<<< HEAD
    private AuthService authService = new AuthService();
=======
    private AuthService authService =
            new AuthService();
>>>>>>> 6299a66c75a8ae86ce7fbeb9035d805af23f4824

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

<<<<<<< HEAD
            request.getRequestDispatcher("/WEB-INF/views/auth/inscription.jsp")
                    .forward(request, response);
=======
            request.getRequestDispatcher(
                    "/WEB-INF/views/auth/inscription.jsp"
            ).forward(request, response);
>>>>>>> 6299a66c75a8ae86ce7fbeb9035d805af23f4824

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

<<<<<<< HEAD
            response.sendRedirect(request.getContextPath() + "/login"
=======
            response.sendRedirect(
                    request.getContextPath() + "/login"
>>>>>>> 6299a66c75a8ae86ce7fbeb9035d805af23f4824
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
<<<<<<< HEAD
        // MODIFIER PROFIL
        else if (path.equals("/profil")) {

            modifierProfil(request, response);
        }
=======
>>>>>>> 6299a66c75a8ae86ce7fbeb9035d805af23f4824
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

<<<<<<< HEAD
                if (cookie.getName().equals("rememberEmail")) {

                    request.setAttribute("rememberEmail", cookie.getValue());
=======
                if (cookie.getName()
                        .equals("rememberEmail")) {

                    request.setAttribute(
                            "rememberEmail",
                            cookie.getValue()
                    );
>>>>>>> 6299a66c75a8ae86ce7fbeb9035d805af23f4824
                }
            }
        }

<<<<<<< HEAD
        request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
=======
        request.getRequestDispatcher(
                "/WEB-INF/views/auth/login.jsp"
        ).forward(request, response);
>>>>>>> 6299a66c75a8ae86ce7fbeb9035d805af23f4824
    }

    // Connexion utilisateur
    private void connecter(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");

        String password = request.getParameter("password");

        String remember = request.getParameter("rememberMe");

        // Vérifie login
<<<<<<< HEAD
        Utilisateur utilisateur = authService.connexion(email, password);
=======
        Utilisateur utilisateur =
                authService.connexion(email, password);
>>>>>>> 6299a66c75a8ae86ce7fbeb9035d805af23f4824

        // Mauvais login
        if (utilisateur == null) {

<<<<<<< HEAD
            request.setAttribute("erreur", "Email ou mot de passe invalide");
=======
            request.setAttribute(
                    "erreur",
                    "Email ou mot de passe invalide"
            );
>>>>>>> 6299a66c75a8ae86ce7fbeb9035d805af23f4824

            request.getRequestDispatcher(
                    "/WEB-INF/views/auth/login.jsp"
            ).forward(request, response);

            return;
        }

        // Création session
        HttpSession session = request.getSession();

<<<<<<< HEAD
        session.setAttribute("utilisateur", utilisateur);
=======
        session.setAttribute(
                "utilisateur",
                utilisateur
        );
>>>>>>> 6299a66c75a8ae86ce7fbeb9035d805af23f4824

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

<<<<<<< HEAD
    // Modifier profil
    private void modifierProfil(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");

        String nom = request.getParameter("nom");
        String email = request.getParameter("email");

        boolean succes = authService.modifier(utilisateur.getId(), nom, email);

        if (!succes) {
            request.setAttribute("erreur", "Cet email est déjà utilisé par un autre compte.");
            request.getRequestDispatcher("/WEB-INF/views/auth/profil.jsp").forward(request, response);
            return;
        }

        // Mettre à jour la session
        utilisateur.setNom(nom);
        utilisateur.setEmail(email);
        session.setAttribute("utilisateur", utilisateur);

        request.setAttribute("succes", "Profil mis à jour avec succès.");
        request.getRequestDispatcher("/WEB-INF/views/auth/profil.jsp").forward(request, response);
    }

=======
>>>>>>> 6299a66c75a8ae86ce7fbeb9035d805af23f4824
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
