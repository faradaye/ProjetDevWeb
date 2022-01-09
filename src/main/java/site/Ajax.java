package site;

import beans.Utilisateur;
import dao.ActiviteDao;
import dao.DaoFactory;
import dao.LieuDao;
import dao.UtilisateurDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ajax", value = "/ajax")
public class Ajax extends HttpServlet {
    private UtilisateurDao utilisateurDao;
    private ActiviteDao activiteDao;
    private LieuDao lieuDao;

    @Override
    public void init() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.utilisateurDao = daoFactory.getUtilisateurDao();
        this.activiteDao = daoFactory.getActiviteDao();
        this.lieuDao = daoFactory.getLieuDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categorie = request.getParameter("categorie");
        if (categorie != null && !categorie.equals("")) {
            if(categorie.equals("getListeUtilisateurs")){
                request.setAttribute("utilisateurs", utilisateurDao.getAll());
                this.getServletContext().getRequestDispatcher("/WEB-INF/listeUtilisateurs.jsp").forward(request, response);
            }
            else if(categorie.equals("displayListeActivites")){
                request.setAttribute("activites", activiteDao.getAll());
                this.getServletContext().getRequestDispatcher("/WEB-INF/listeActivites.jsp").forward(request, response);
            }
            else if(categorie.equals("getListeAmis")){
                Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
                if(utilisateur == null){
                    response.sendRedirect(request.getContextPath()+"/authentification");
                }else{
                    List<Utilisateur> amis = new ArrayList<>();
                    for(int id_ami : utilisateurDao.getAmis(utilisateur)){
                        Utilisateur ami = utilisateurDao.getUtilisateur(id_ami);
                        amis.add(ami);
                    }
                    request.setAttribute("amis", amis);
                    this.getServletContext().getRequestDispatcher("/WEB-INF/listeAmis.jsp").forward(request, response);
                }
            }
            else if(categorie.equals("getListeLieux")){
                request.setAttribute("lieux", lieuDao.getAll());
                this.getServletContext().getRequestDispatcher("/WEB-INF/listeLieux.jsp").forward(request, response);
            }
        }
    }

}
