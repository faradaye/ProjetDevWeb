package site;

import beans.Lieu;
import beans.Utilisateur;
import dao.DaoFactory;
import dao.LieuDao;
import utils.VerifInt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "creerLieu", value = "/creerLieu")
public class CreerLieu extends HttpServlet {
    LieuDao lieuDao;

    @Override
    public void init() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.lieuDao = daoFactory.getLieuDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/creerLieu.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String adresse = request.getParameter("adresse");
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");

        //verification remplissage formulaire
        if (nom != null && !nom.equals("") && adresse != null && !adresse.equals("") && latitude != null && !latitude.equals("") && longitude != null && !longitude.equals("")) {
            if(!lieuDao.nomLieuExiste(nom)){
                Lieu lieu = new beans.Lieu(0, nom, adresse, Double.parseDouble(latitude), Double.parseDouble(longitude));
                lieuDao.ajouter(lieu);
                request.setAttribute("id", lieu.getId());
                response.sendRedirect(request.getContextPath() + "/lieu?id=" + lieu.getId());
            }
            else{
                beans.Lieu lieu = new beans.Lieu(0, nom, adresse, Double.parseDouble(latitude), Double.parseDouble(longitude));
                String erreur = "Le nom est déjà pris";
                request.setAttribute("erreur", erreur);
                request.setAttribute("lieu", lieu);
                this.getServletContext().getRequestDispatcher("/WEB-INF/creerLieu.jsp").forward(request, response);
            }
        }
        else {
            beans.Lieu lieu = new beans.Lieu(0, nom, adresse, Double.parseDouble(latitude), Double.parseDouble(longitude));
            String erreur = "Informations rentrées incorrectes";
            request.setAttribute("erreur", erreur);
            request.setAttribute("lieu", lieu);
            this.getServletContext().getRequestDispatcher("/WEB-INF/creerLieu.jsp").forward(request, response);
        }
    }

}