package site;

import beans.Activite;
import beans.Utilisateur;
import dao.ActiviteDao;
import dao.DaoFactory;
import dao.UtilisateurDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ajouterParticipation", value = "/ajouterParticipation")
public class AjouterParticipation extends HttpServlet {
    private UtilisateurDao utilisateurDao;
    private ActiviteDao activiteDao;

    @Override
    public void init() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.utilisateurDao = daoFactory.getUtilisateurDao();
        this.activiteDao = daoFactory.getActiviteDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("utilisateur")==null){
            response.sendRedirect(request.getContextPath()+"/authentification");
        }else{
            if((request.getParameter("id")!=null && !request.getParameter("id").equals(""))){
                int id = Integer.parseInt(request.getParameter("id"));
                Activite activite = activiteDao.getActivite(id);
                Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
                activiteDao.addParticipant(activite,utilisateur);

                request.setAttribute("activite", activite);
            }
            response.sendRedirect(request.getContextPath());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}