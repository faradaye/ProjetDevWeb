package site;

import beans.Utilisateur;
import dao.DaoFactory;
import dao.LieuDao;
import dao.UtilisateurDao;
import utils.VerifDate;
import utils.VerifDouble;
import utils.VerifInt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet(name = "modifierLieu", value = "/modifierLieu")
public class ModifierLieu extends HttpServlet {
    LieuDao lieuDao;

    @Override
    public void init() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.lieuDao = daoFactory.getLieuDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("id")!=null && !request.getParameter("id").equals("") && VerifInt.isNumber(request.getParameter("id").toString())){
            int id =  Integer.parseInt(request.getParameter("id"));
            beans.Lieu lieu = lieuDao.getLieu(id);
            if(lieu==null){
                response.sendRedirect(request.getHeader("Referer"));
                return;
            }
            request.setAttribute("idLieu", request.getParameter("id"));
            request.setAttribute("lieu", lieu);
            this.getServletContext().getRequestDispatcher("/WEB-INF/modifierLieu.jsp").forward(request, response);
        }
        else{
            response.sendRedirect(request.getHeader("Referer"));
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("idLieu");
        String nom = request.getParameter("nom");
        String adresse = request.getParameter("adresse");
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");

        //verification remplissage formulaire
        if (id != null && !id.equals("") && nom != null && !nom.equals("") && adresse != null && !adresse.equals("") && latitude != null && !latitude.equals("") && longitude != null && !longitude.equals("") && VerifDouble.isDouble(latitude) && VerifDouble.isDouble(longitude)) {
            lieuDao.modifier(new beans.Lieu(Integer.parseInt(id), nom, adresse, Double.parseDouble(latitude), Double.parseDouble(longitude)));
            request.setAttribute("id", Integer.parseInt(id));
            response.sendRedirect(request.getContextPath() + "/lieu?id=" + id);
        }
        else {
            beans.Lieu lieu = new beans.Lieu(0, nom, adresse, VerifDouble.isDouble(latitude) ? Double.parseDouble(latitude) : 0.0, VerifDouble.isDouble(latitude) ? Double.parseDouble(longitude) : 0.0);
            String erreur = "Informations rentrées incorrectes";
            request.setAttribute("erreur", erreur);
            request.setAttribute("lieu", lieu);
            request.setAttribute("idLieu", id);
            this.getServletContext().getRequestDispatcher("/WEB-INF/modifierLieu.jsp").forward(request, response);
        }
    }

}