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

    private AuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();

        if (path.equals("/login")) {
            afficherLogin(request, response);
        }
        else if (path.equals("/inscription")) {
            request.getRequestDispatcher("/WEB-INF/views/auth/inscription.jsp")
                    .forward(request, response);
        }
        else if (path.equals("/profil")) {
            request.getRequestDispatcher("/WEB-INF/views/auth/profil.jsp")
                    .forward(request, response);
        }
        else if (path.equals("/logout")) {
            request.getSession().invalidate();
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();

        if (path.equals("/login")) {
            connecter(request, response);
        }
        else if (path.equals("/inscription")) {
            inscrire(request, response);
        }
        else if (path.equals("/profil")) {
            modifierProfil(request, response);
        }
    }

    private void afficherLogin(HttpServletRequest request,
                               HttpServletResponse response)
            throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("rememberEmail")) {
                    request.setAttribute("rememberEmail", cookie.getValue());
                }
            }
        }

        request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp")
                .forward(request, response);
    }

    private void connecter(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String remember = request.getParameter("rememberMe");

        Utilisateur utilisateur = authService.connexion(email, password);

        if (utilisateur == null) {
            request.setAttribute("erreur", "Email ou mot de passe invalide");
            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp")
                    .forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("utilisateur", utilisateur);
        session.setAttribute("role", utilisateur.getRole().name());

        if (remember != null) {
            Cookie cookie = new Cookie("rememberEmail", email);
            cookie.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(cookie);
        }

        if (utilisateur.getRole() == Utilisateur.Role.ADMIN) {
            response.sendRedirect(request.getContextPath() + "/admin/produits");
        } else {
            response.sendRedirect(request.getContextPath() + "/catalogue");
        }
    }

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
            request.getRequestDispatcher("/WEB-INF/views/auth/profil.jsp")
                    .forward(request, response);
            return;
        }

        utilisateur.setNom(nom);
        utilisateur.setEmail(email);
        session.setAttribute("utilisateur", utilisateur);

        request.setAttribute("succes", "Profil mis à jour avec succès.");
        request.getRequestDispatcher("/WEB-INF/views/auth/profil.jsp")
                .forward(request, response);
    }

    private void inscrire(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean success = authService.inscription(nom, email, password);

        if (!success) {
            request.setAttribute("erreur", "Cet email existe déjà");
            request.getRequestDispatcher("/WEB-INF/views/auth/inscription.jsp")
                    .forward(request, response);
            return;
        }

        EmailService emailService = new EmailService();
        emailService.envoyerEmail(
                email,
                "Bienvenue sur E-Commerce",
                "Votre compte a été créé avec succès!"
        );

        response.sendRedirect(request.getContextPath() + "/login");
    }
}
