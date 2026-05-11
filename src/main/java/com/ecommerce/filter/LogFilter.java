package com.ecommerce.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDateTime;

@WebFilter("/*")
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        HttpSession session = req.getSession(false);

        String utilisateur = "Visiteur";

        // Vérifie session utilisateur
        if (session != null && session.getAttribute("utilisateur") != null) {

            utilisateur = session.getAttribute("utilisateur").toString();
        }

        // Affichage console
        System.out.println(
                "[" + LocalDateTime.now() + "] "
                        + req.getMethod()
                        + " "
                        + req.getRequestURI()
                        + " | Utilisateur : "
                        + utilisateur
        );

        // Continuer requête
        chain.doFilter(request, response);
    }
}