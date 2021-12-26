package site;

import beans.Notification;
import beans.Utilisateur;
import dao.DaoFactory;
import dao.NotificationDao;
import dao.UtilisateurDao;
import utils.VerifDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "inscription", value = "/inscription")
@MultipartConfig(maxFileSize = 16177215) // upload file up to 16MB
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

        InputStream inputStreamImage = null;
        Part filePart = request.getPart("imageProfile");
        if (filePart != null) {
            inputStreamImage = filePart.getInputStream();
        }

        //verification remplissage formulaire
        if (login != "" && login != null && password != "" && password != null && nom != "" && nom != null && prenom != "" && prenom != null && email!=null && date_naissance != "" && date_naissance != "0000-00-00" && date_naissance != null) {
            if(!utilisateurDao.loginUtilisateurExiste(login)){
                if(email=="" || !utilisateurDao.emailUtilisateurExiste(email)){
                    utilisateurDao.ajouter(new Utilisateur(0, login, password, nom, prenom, Date.valueOf(date_naissance), email, inputStreamImage, false));

                    Utilisateur utilisateur = utilisateurDao.authUtilisateur(login, password);

                    if(utilisateur!=null){
                        session.setAttribute("utilisateur", utilisateur);
                        request.setAttribute("utilisateur", utilisateur);
                        session.setAttribute("notifications", notificationDao.getAllForUser(utilisateur));
                        request.setAttribute("notifications", notificationDao.getAllForUser(utilisateur));
                        setNotificationsNonLues(request,utilisateur);
                        this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                    }
                    else{
                        utilisateur = new Utilisateur(0, login, password, null, (String) null, null, false);
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

    private void setNotificationsNonLues(HttpServletRequest request, Utilisateur utilisateur){
        List<Notification> nonLues = new ArrayList<>();
        for(Notification n : notificationDao.getAllForUser(utilisateur)){
            if(!n.isLue()){
                nonLues.add(n);
            }
        }
        request.getSession().setAttribute("notificationsNonLues", nonLues);
    }
}