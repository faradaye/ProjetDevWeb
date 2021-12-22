package site;


import beans.Utilisateur;
import utils.AppUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class SecuriteFiltre implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String path = req.getServletPath();

        if(path.equals("/authentification")){
            chain.doFilter(req, resp);
            return;
        }

        //Si la page est une page qui necessite de se connecter
        if(path.equals("/profile") || path.equals("/utilisateurs") || path.equals("/notifications") || path.equals("/modifierUtilisateur") || path.equals("/supprimerUtilisateur") || path.equals("/modifierLieu") || path.equals("/supprimerLieu")){
            Utilisateur utilisateur = (Utilisateur) req.getSession().getAttribute("utilisateur");
            if(utilisateur==null){
                String requestUri = req.getRequestURI();

                // Stockez la page en cours à rediriger après l'achèvement de la connexion.
                int redirectId = AppUtils.enregistreUriEtRetourneIdRedirect(requestUri);

                resp.sendRedirect(req.getContextPath() + "/authentification?redirectId=" + redirectId);
                return;
            }

            //Verification permissions
            //verification que l'utilisateur pas admin ne peut modifier que son compte
            if(!utilisateur.isAdministrateur() && path.equals("/modifierUtilisateur") && (req.getParameter("id")!=null && !req.getParameter("id").equals(""))){
                req.getRequestDispatcher("/WEB-INF/accesNonAutorise.jsp").forward(req, resp);
                return;
            }
            //Verification si la page est une page admin et si l'utilisateur est un admin
            if(!utilisateur.isAdministrateur() && (path.equals("/supprimerUtilisateur") || path.equals("/modifierLieu") || path.equals("/supprimerLieu"))){
                req.getRequestDispatcher("/WEB-INF/accesNonAutorise.jsp").forward(req, resp);
                return;
            }
        }

        chain.doFilter(req,resp);
    }

    @Override
    public void destroy() {

    }
}
