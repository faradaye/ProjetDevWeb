package site;

import beans.Activite;
import beans.Utilisateur;
import dao.ActiviteDao;
import dao.DaoFactory;
import dao.LieuDao;
import utils.VerifDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Objects;

@WebServlet(name = "modifierActivite", value = "/modifierActivite")
public class ModifierActivite extends HttpServlet {
    private ActiviteDao activiteDao;
    private LieuDao lieuDao;

    @Override
    public void init() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.activiteDao = daoFactory.getActiviteDao();
        this.lieuDao = daoFactory.getLieuDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Activite activite = new Activite(0,null,null,null,null,0);
        if((request.getParameter("id")!=null && !request.getParameter("id").equals(""))){
            request.setAttribute("idActivite", request.getParameter("id"));
            activite = activiteDao.getActivite(Integer.parseInt(request.getParameter("id")));
        }
        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
        if(utilisateur == null){
            response.sendRedirect(request.getContextPath()+"/authentification");
        }else if(!utilisateur.isAdministrateur()){
            response.sendRedirect(request.getHeader("referer"));
        }else{
            request.setAttribute("lieux", lieuDao.getAll());
            request.setAttribute("activite", activite);

            this.getServletContext().getRequestDispatcher("/WEB-INF/modifierActivite.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("idActivite");
        String nom = request.getParameter("nom");
        String id_lieu = request.getParameter("id_lieu");
        String date = request.getParameter("date");
        String heure_debut = request.getParameter("heure_debut");
        String heure_fin = request.getParameter("heure_fin");
        request.setAttribute("lieux", lieuDao.getAll());

        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
        if(utilisateur == null){
            response.sendRedirect(request.getContextPath()+"/authentification");
        }else if(!utilisateur.isAdministrateur()) {
            response.sendRedirect(request.getHeader("referer"));
        }else{
            //verification remplissage formulaire
            if (!Objects.equals(id, "") && id != null && !Objects.equals(nom, "") && nom != null && !Objects.equals(date, "") && date != null && heure_debut != null && heure_fin!=null && id_lieu != null && !id_lieu.equals("0")) {
                //on vérifie que les heures sont au bon format
                if(heure_debut.matches("[0-9]+:[0-9]+:[0-9]+")){
                    heure_debut = heure_debut.substring(0,5);
                }
                if(heure_fin.matches("[0-9]+:[0-9]+:[0-9]+")){
                    heure_fin = heure_fin.substring(0,5);
                }
                Activite act = new Activite(Integer.parseInt(id), nom, Date.valueOf(date), Time.valueOf(heure_debut+":00"), Time.valueOf(heure_fin+":00"), Integer.parseInt(id_lieu));
                activiteDao.modifier(act);
                response.sendRedirect(request.getContextPath()+"/activites");
            }
        }
    }

}