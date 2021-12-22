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

@WebServlet(name = "modifierUtilisateur", value = "/modifierUtilisateur")
public class ModifierUtilisateur extends HttpServlet {
    private UtilisateurDao utilisateurDao;

    @Override
    public void init() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.utilisateurDao = daoFactory.getUtilisateurDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utilisateur utilisateur;
        if((request.getParameter("id")!=null && !request.getParameter("id").equals(""))){
            request.setAttribute("idUtilisateur", request.getParameter("id"));
            utilisateur = utilisateurDao.getUtilisateur(Integer.parseInt(request.getParameter("id")));
        }
        else{
            utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
            request.setAttribute("idUtilisateur", utilisateur.getId());
        }

        request.setAttribute("utilisateur", utilisateur);

        this.getServletContext().getRequestDispatcher("/WEB-INF/modifierUtilisateur.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("idUtilisateur");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String date_naissance = request.getParameter("date_naissance");
        boolean administrateur = false;
        if(request.getParameter("administrateur")!=null && request.getParameter("administrateur").equals("1")){
            administrateur = true;
        }

        HttpSession session = request.getSession();

        //verification remplissage formulaire
        if (id != "" && id != null && login != "" && login != null && (password == "" || password == null) && nom != "" && nom != null && prenom != "" && prenom != null && date_naissance != "" && date_naissance != "0000-00-00" && date_naissance != null) {
            utilisateurDao.modifierSansModifMotDePasse(new Utilisateur(Integer.parseInt(id), login, nom, prenom, Date.valueOf(date_naissance), administrateur));
            request.setAttribute("id", Integer.parseInt(id));
            response.sendRedirect(request.getContextPath() + "/profile?id=" + id);
        }
        else if (id != "" && id != null && login != "" && login != null && password != "" && password != null && nom != "" && nom != null && prenom != "" && prenom != null && date_naissance != "" && date_naissance != "0000-00-00" && date_naissance != null) {
            utilisateurDao.modifier(new Utilisateur(Integer.parseInt(id), login, password, nom, prenom, Date.valueOf(date_naissance), administrateur));
            request.setAttribute("id", Integer.parseInt(id));
            response.sendRedirect(request.getContextPath() + "/profile?id=" + id);
        }
        else {
            Utilisateur utilisateur = new Utilisateur(0, login, password, nom, prenom, VerifDate.estValide(date_naissance) ? Date.valueOf(date_naissance) : Date.valueOf(LocalDate.now()), administrateur);
            String erreur = "Informations rentrées incorrectes";
            request.setAttribute("erreur", erreur);
            session.setAttribute("utilisateur", null);
            request.setAttribute("utilisateur", utilisateur);
            request.setAttribute("id", id);
            this.getServletContext().getRequestDispatcher("/WEB-INF/modifierUtilisateur.jsp").forward(request, response);
        }
    }

}