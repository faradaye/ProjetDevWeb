package site;

import beans.Utilisateur;
import dao.DaoFactory;
import dao.UtilisateurDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "authentification", value = "/authentification")
public class Authentification extends HttpServlet {
    private UtilisateurDao utilisateurDao;

    @Override
    public void init() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.utilisateurDao = daoFactory.getUtilisateurDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/authentification.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        //verification remplissage formulaire
        if (login != "" && login != null && password != "" && password != null) {
            Utilisateur utilisateur = utilisateurDao.authUtilisateur(login, password);

            if(utilisateur!=null){
                session.setAttribute("utilisateur", utilisateur);
                request.setAttribute("utilisateur", utilisateur);
                this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }
            else{
                utilisateur = new Utilisateur(0, login, password, null, null, null, false);
                String erreur = "Login ou mot de passe invalide";
                request.setAttribute("erreur", erreur);
                session.setAttribute("utilisateur", null);
                request.setAttribute("utilisateur", utilisateur);
                this.getServletContext().getRequestDispatcher("/WEB-INF/authentification.jsp").forward(request, response);
            }
        }
        else {
            Utilisateur utilisateur = new Utilisateur(0, login, password, null, null, null, false);
            String erreur = "Informations rentrées incorrectes";
            request.setAttribute("erreur", erreur);
            session.setAttribute("utilisateur", null);
            request.setAttribute("utilisateur", utilisateur);
            this.getServletContext().getRequestDispatcher("/WEB-INF/authentification.jsp").forward(request, response);
        }
    }

}