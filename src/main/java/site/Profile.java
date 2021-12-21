package site;

import beans.Utilisateur;
import dao.DaoFactory;
import dao.NotificationDao;
import dao.UtilisateurDao;
import utils.VerifInt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "profile", value = "/profile")
public class Profile extends HttpServlet {
    UtilisateurDao utilisateurDao;

    @Override
    public void init() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.utilisateurDao = daoFactory.getUtilisateurDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utilisateur utilisateur;
        if(request.getParameter("id")!=null && !request.getParameter("id").equals("") && VerifInt.isNumber(request.getParameter("id").toString())){
            request.setAttribute("id", request.getParameter("id"));
        }
        if(request.getAttribute("id")!=null && !request.getAttribute("id").equals("") && VerifInt.isNumber(request.getAttribute("id").toString())){
            int id =  Integer.parseInt(request.getAttribute("id").toString());
            utilisateur = utilisateurDao.getUtilisateur(id);
            if(utilisateur==null){
                this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                return;
            }
        }
        else{
            utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
        }

        request.setAttribute("utilisateur", utilisateur);

        this.getServletContext().getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("On est dans post");
    }

}
