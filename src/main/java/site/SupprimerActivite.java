package site;

import beans.Utilisateur;
import beans.Activite;
import dao.ActiviteDao;
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

@WebServlet(name = "supprimerActivite", value = "/supprimerActivite")
public class SupprimerActivite extends HttpServlet {
    private ActiviteDao activiteDao;

    @Override
    public void init() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.activiteDao = daoFactory.getActiviteDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if((request.getParameter("id")!=null && !request.getParameter("id").equals(""))){
            int id = Integer.parseInt(request.getParameter("id"));
            Activite activite = activiteDao.getActivite(id);
            activiteDao.supprimer(activite);
        }
        if(request.getHeader("referer")==null || request.getHeader("referer").contains("/profile?id="))
            response.sendRedirect(request.getContextPath() + "/activites");
        else response.sendRedirect(request.getHeader("referer"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}