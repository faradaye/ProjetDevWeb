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
import java.util.List;

@WebServlet(name = "supprimerAmi", value = "/supprimerAmi")
public class SupprimerAmi extends HttpServlet {
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
        if((request.getParameter("id")!=null && !request.getParameter("id").equals(""))){
            int id = Integer.parseInt(request.getParameter("id"));
            Utilisateur ami = utilisateurDao.getUtilisateur(id);
            Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");

            utilisateurDao.supprimerAmi(utilisateur.getId(),ami.getId());
            utilisateur.setAmis(utilisateurDao.getAmis(utilisateur));
            request.setAttribute("amis", utilisateur.getAmis());

            notificationDao.ajouter(createNotificationSuppressionAmi(utilisateur,ami));
        }
        response.sendRedirect(request.getContextPath()+"/amis");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private Notification createNotificationSuppressionAmi(Utilisateur src, Utilisateur dest){
        String prenom_nom_source = src.getPrenom() + " " + src.getNom();
        String contenu = prenom_nom_source + " n'est plus votre ami.";
        return new Notification(0, dest.getId(), src.getId(), 2, contenu, prenom_nom_source, false);
    }
}