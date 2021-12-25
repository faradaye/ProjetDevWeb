package site;

import beans.Notification;
import beans.Utilisateur;
import dao.DaoFactory;
import dao.NotificationDao;
import dao.UtilisateurDao;
import utils.AppUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "authentification", value = "/authentification")
public class Authentification extends HttpServlet {
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
                session.setAttribute("notifications", notificationDao.getAllForUser(utilisateur));
                request.setAttribute("utilisateur", utilisateur);
                request.setAttribute("notifications", notificationDao.getAllForUser(utilisateur));
                setNotificationsNonLues(request,utilisateur);
                int redirectId = -1;
                try {
                    redirectId = Integer.parseInt(request.getParameter("redirectId"));
                } catch (Exception e) {
                }
                String requestUri = AppUtils.recupereUriRedirectDepuisIdRedirect(redirectId);
                if (requestUri != null) {
                    response.sendRedirect(requestUri);
                } else {
                    // Redirection par defaut
                    this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                }
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
        else {
            Utilisateur utilisateur = new Utilisateur(0, login, password, null, (String) null, null, false);
            String erreur = "Informations rentrées incorrectes";
            request.setAttribute("erreur", erreur);
            session.setAttribute("utilisateur", null);
            request.setAttribute("utilisateur", utilisateur);
            session.setAttribute("notifications", null);
            request.setAttribute("notifications", null);
            this.getServletContext().getRequestDispatcher("/WEB-INF/authentification.jsp").forward(request, response);
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