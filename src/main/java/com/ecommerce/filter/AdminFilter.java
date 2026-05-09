package com.ecommerce.filter;

import com.ecommerce.model.Utilisateur;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req =
                (HttpServletRequest) request;

        HttpServletResponse res =
                (HttpServletResponse) response;

        HttpSession session =
                req.getSession(false);

        // Vérifie utilisateur connecté
        Utilisateur utilisateur =
                (session != null)
                        ? (Utilisateur) session.getAttribute("utilisateur")
                        : null;

        // Vérifie rôle ADMIN
        if (utilisateur == null ||
                utilisateur.getRole()
                        != Utilisateur.Role.ADMIN) {

            res.sendRedirect(req.getContextPath() + "/catalogue"
            );

            return;
        }

        // Continuer
        chain.doFilter(request, response);
    }
}