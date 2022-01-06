package site;

import beans.Notification;
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
import java.util.*;

@WebServlet(name = "positif", value = "/positif")
public class Positif extends HttpServlet {
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
        if(request.getSession().getAttribute("utilisateur")!=null){
            Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
            sendNotificationsPositif(utilisateur);
        }
        response.sendRedirect(request.getContextPath());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void sendNotificationsPositif(Utilisateur utilisateur){
        Set<Utilisateur> aNotifier = new HashSet<>();

        //on ajoute les amis
        List<Integer> amis = utilisateurDao.getAmis(utilisateur);
        for(int idAmi : amis){
            aNotifier.add(utilisateurDao.getUtilisateur(idAmi));
        }

        //on ajoute les personnes ayant fréquentées les mêmes lieux aux mêmes moments dans les 7 jours précédents
        aNotifier.addAll(utilisateurDao.getCasContacts(utilisateur));

        String prenom_nom_source = utilisateur.getPrenom() + " " + utilisateur.getNom();
        String contenu = prenom_nom_source + " est positif au covid, vous êtes cas contact.";

        for(Utilisateur u : aNotifier){
            if(u.getId() != utilisateur.getId()){
                Notification n = new Notification(0, u.getId(), utilisateur.getId(), 3, contenu, prenom_nom_source, false);
                notificationDao.ajouter(n);
            }
        }

    }
}