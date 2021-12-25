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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "accepterDemande", value = "/accepterDemande")
public class AccepterDemande extends HttpServlet {
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
            Notification notification = notificationDao.getNotif(id);
            utilisateurDao.ajouterAmi(notification.getId_utilisateur(),notification.getId_source());

            notification.setLue(true);
            notificationDao.modifier(notification);

            Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
            request.getSession().setAttribute("notifications", notificationDao.getAllForUser(utilisateur));
            setNotificationsNonLues(request,utilisateur);
        }
        response.sendRedirect(request.getContextPath());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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