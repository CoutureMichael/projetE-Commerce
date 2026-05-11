package com.ecommerce.filter;
import com.ecommerce.model.Utilisateur;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;
@WebFilter(urlPatterns = {
        "/panier",
        "/commande",
        "/historique-commandes",
        "/admin/*"  // protège tout ce qui est admin
})
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        boolean connected =
                session != null &&
                        session.getAttribute("utilisateur") != null;
        // Non connecté → redirection login
        if (!connected) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        // Vérifie accès admin
        String path = req.getRequestURI();
        if (path.contains("/admin")) {
            Utilisateur user =
                    (Utilisateur) session.getAttribute("utilisateur");
            if (user.getRole() != Utilisateur.Role.ADMIN) {
                // Redirige vers produits si pas admin
                res.sendRedirect(req.getContextPath() + "/produits");
                return;
            }
        }
        // Tout est OK → continue
        chain.doFilter(request, response);
    }
}