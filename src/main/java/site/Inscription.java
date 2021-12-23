package site;

import beans.Utilisateur;
import dao.DaoFactory;
import dao.NotificationDao;
import dao.UtilisateurDao;
import utils.VerifDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet(name = "inscription", value = "/inscription")
public class Inscription extends HttpServlet {
    private UtilisateurDao utilisateurDao;
    private NotificationDao notificationDao;

    @Override
    public void init() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.utilisateurDao = daoFactory.getUtilisateurDao();
        this.notificationDao = daoFactory.getNotificationDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String date_naissance = request.getParameter("date_naissance");
        String email = request.getParameter("email");
        HttpSession session = request.getSession();

        //verification remplissage formulaire
        if (login != "" && login != null && password != "" && password != null && nom != "" && nom != null && prenom != "" && prenom != null && email!=null && date_naissance != "" && date_naissance != "0000-00-00" && date_naissance != null) {
            if(!utilisateurDao.loginUtilisateurExiste(login)){
                if(email=="" || !utilisateurDao.emailUtilisateurExiste(email)){
                    utilisateurDao.ajouter(new Utilisateur(0, login, password, nom, prenom, Date.valueOf(date_naissance), email, false));

                    Utilisateur utilisateur = utilisateurDao.authUtilisateur(login, password);

                    if(utilisateur!=null){
                        session.setAttribute("utilisateur", utilisateur);
                        request.setAttribute("utilisateur", utilisateur);
                        session.setAttribute("notifications", notificationDao.getAllForUser(utilisateur));
                        request.setAttribute("notifications", notificationDao.getAllForUser(utilisateur));
                        this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                    }
                    else{
                        utilisateur = new Utilisateur(0, login, password, null, null, null, false);
                        String erreur = "Login ou mot de passe invalide";
                        request.setAttribute("erreur", erreur);
                        session.setAttribute("utilisateur", null);
                        request.setAttribute("utilisateur", utilisateur);
                        session.setAttribute("notifications", null);
                        request.setAttribute("notifications", null);
                        this.getServletContext().getRequestDispatcher("/WEB-INF/authentification.jsp").forward(request, response);
                    }
                }
                else{
                    Utilisateur utilisateur = new Utilisateur(0, login, password, nom, prenom, Date.valueOf(date_naissance), email, false);
                    String erreur = "L'email est déjà pris";
                    request.setAttribute("erreur", erreur);
                    session.setAttribute("utilisateur", null);
                    request.setAttribute("utilisateur", utilisateur);
                    session.setAttribute("notifications", null);
                    request.setAttribute("notifications", null);
                    this.getServletContext().getRequestDispatcher("/WEB-INF/authentification.jsp").forward(request, response);
                }
            }
            else{
                Utilisateur utilisateur = new Utilisateur(0, login, password, nom, prenom, Date.valueOf(date_naissance), false);
                String erreur = "Le login est déjà pris";
                request.setAttribute("erreur", erreur);
                session.setAttribute("utilisateur", null);
                request.setAttribute("utilisateur", utilisateur);
                session.setAttribute("notifications", null);
                request.setAttribute("notifications", null);
                this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
            }
        }
        else {
            Utilisateur utilisateur = new Utilisateur(0, login, password, nom, prenom, VerifDate.estValide(date_naissance) ? Date.valueOf(date_naissance) : Date.valueOf(LocalDate.now()), false);
            String erreur = "Informations rentrées incorrectes";
            request.setAttribute("erreur", erreur);
            session.setAttribute("utilisateur", null);
            request.setAttribute("utilisateur", utilisateur);
            session.setAttribute("notifications", null);
            request.setAttribute("notifications", null);
            this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
        }
    }

}