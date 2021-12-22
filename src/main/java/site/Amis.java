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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "amis", value = "/amis")
public class Amis extends HttpServlet {
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
        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
        List<Utilisateur> amis = new ArrayList<>();
        for(int id_ami : utilisateurDao.getAmis(utilisateur)){
            Utilisateur ami = utilisateurDao.getUtilisateur(id_ami);
            amis.add(ami);
        }
        request.setAttribute("amis", amis);
        this.getServletContext().getRequestDispatcher("/WEB-INF/amis.jsp").forward(request, response);
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
        String prenom_nom_source = src.getPrenom() + " " + src.getNom();
        String contenu = prenom_nom_source + " souhaite vous ajouter en ami.";
        return new Notification(0, dest.getId(), src.getId(), 1, contenu, prenom_nom_source);
    }
}