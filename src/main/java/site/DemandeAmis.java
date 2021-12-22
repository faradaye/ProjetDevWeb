package site;

import beans.Notification;
import beans.Utilisateur;
import dao.DaoFactory;
import dao.NotificationDao;
import dao.UtilisateurDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "demandeamis", value = "/demandeamis")
public class DemandeAmis extends HttpServlet {
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
        this.getServletContext().getRequestDispatcher("/WEB-INF/demandeamis.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginAmi = request.getParameter("loginami");

        if(loginAmi != null & !loginAmi.equals("")){
            Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
            List<Integer> amis = utilisateurDao.getAmis(utilisateur);
            utilisateur.setAmis(amis);

            if(utilisateurDao.loginUtilisateurExiste(loginAmi)){
                for(Utilisateur u : utilisateurDao.getAll()){
                    if(u.getLogin().equals(loginAmi)){
                        Notification n = createNotificationDemandeAmi(utilisateur, u);
                        notificationDao.ajouter(n);
                        break;
                    }
                }
            }
        }

        doGet(request,response);
    }

    private Notification createNotificationDemandeAmi(Utilisateur src, Utilisateur dest){
        String contenu = src.getPrenom() + " " + src.getNom() + " souhaite vous ajouter en ami.";
        return new Notification(0, dest.getId(), src.getId(), 1, contenu);
    }
}