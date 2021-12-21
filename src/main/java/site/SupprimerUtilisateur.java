package site;

import beans.Utilisateur;
import dao.DaoFactory;
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

@WebServlet(name = "supprimerUtilisateur", value = "/supprimerUtilisateur")
public class SupprimerUtilisateur extends HttpServlet {
    private UtilisateurDao utilisateurDao;

    @Override
    public void init() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.utilisateurDao = daoFactory.getUtilisateurDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if((request.getParameter("id")!=null && !request.getParameter("id").equals(""))){
            int id = Integer.parseInt(request.getParameter("id"));
            Utilisateur utilisateur = utilisateurDao.getUtilisateur(id);
            if(!utilisateur.isAdministrateur() && ((Utilisateur) request.getSession().getAttribute("utilisateur")).isAdministrateur())
                utilisateurDao.supprimer(utilisateur);
        }

        response.sendRedirect(request.getHeader("referer"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}