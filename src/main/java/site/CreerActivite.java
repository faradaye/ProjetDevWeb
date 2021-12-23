package site;

import beans.Activite;
import beans.Lieu;
import beans.Utilisateur;
import dao.ActiviteDao;
import dao.DaoFactory;
import dao.LieuDao;
import utils.VerifDate;
import utils.VerifDouble;
import utils.VerifInt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

@WebServlet(name = "creerActivite", value = "/creerActivite")
public class CreerActivite extends HttpServlet {
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
        if(request.getSession().getAttribute("utilisateur")==null){
            response.sendRedirect(request.getContextPath()+"/authentification");
        }else{
            request.setAttribute("lieux", lieuDao.getAll());
            this.getServletContext().getRequestDispatcher("/WEB-INF/creerActivite.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("utilisateur")==null){
            response.sendRedirect(request.getContextPath()+"/authentification");
        }else {
            String nom = request.getParameter("nom");
            String date_activite = request.getParameter("date");
            String heure_debut = request.getParameter("heuredebut");
            String heure_fin = request.getParameter("heurefin");
            int id_lieu = Integer.parseInt(request.getParameter("lieu"));

            //verification remplissage formulaire
            if (nom != null && !nom.equals("") && date_activite != null && lieuDao.getLieu(id_lieu) != null) {
                Activite activite = new Activite(0, nom, Date.valueOf(date_activite), Time.valueOf(heure_debut + ":00"), Time.valueOf(heure_fin + ":00"), id_lieu);
                activiteDao.ajouter(activite);
                request.setAttribute("id", activite.getId());
                response.sendRedirect(request.getContextPath() + "/activite?id=" + activite.getId());
            } else {
                Activite activite = new Activite(0, nom, null, null, null, id_lieu);
                String erreur = "Informations rentrées incorrectes";
                request.setAttribute("erreur", erreur);
                request.setAttribute("activite", activite);
                this.getServletContext().getRequestDispatcher("/WEB-INF/creerActivite.jsp").forward(request, response);
            }
        }
    }

}