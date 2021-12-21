package site;

import beans.Lieu;
import beans.Utilisateur;
import dao.DaoFactory;
import dao.LieuDao;
import dao.UtilisateurDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "supprimerLieu", value = "/supprimerLieu")
public class SupprimerLieu extends HttpServlet {
    LieuDao lieuDao;

    @Override
    public void init() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.lieuDao = daoFactory.getLieuDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if((request.getParameter("id")!=null && !request.getParameter("id").equals(""))){
            int id = Integer.parseInt(request.getParameter("id"));
            Lieu lieu = lieuDao.getLieu(id);
            Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
            if(utilisateur.isAdministrateur())
                lieuDao.supprimer(lieu);
        }

        response.sendRedirect(request.getHeader("referer"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}