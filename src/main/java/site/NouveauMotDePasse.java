package site;

import beans.Utilisateur;
import dao.DaoFactory;
import dao.UtilisateurDao;
import utils.TokenGeneration;
import utils.VerifInt;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@WebServlet(name = "nouveauMotDePasse", value = "/nouveauMotDePasse")
public class NouveauMotDePasse extends HttpServlet {
    private UtilisateurDao utilisateurDao;

    @Override
    public void init() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.utilisateurDao = daoFactory.getUtilisateurDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("token")!=null && !request.getParameter("token").equals("") ){
            String token =  request.getParameter("token");
            Utilisateur utilisateur = utilisateurDao.getUtilisateurDemandeRecupMotDePasse(token);
            if(utilisateur==null){
                request.setAttribute("token", null);
                request.setAttribute("utilisateur", null);
                request.setAttribute("erreur", "Erreur: aucune demande de nouveau mot de passe correspond à ce token");
                this.getServletContext().getRequestDispatcher("/WEB-INF/nouveauMotDePasse.jsp").forward(request, response);
                return;
            }
            request.setAttribute("token", request.getParameter("token"));
            request.setAttribute("utilisateur", utilisateur);
            request.setAttribute("erreur", "");
            this.getServletContext().getRequestDispatcher("/WEB-INF/nouveauMotDePasse.jsp").forward(request, response);
        }
        else{
            request.setAttribute("token", null);
            request.setAttribute("utilisateur", null);
            request.setAttribute("erreur", "Erreur: token mal formé");
            this.getServletContext().getRequestDispatcher("/WEB-INF/nouveauMotDePasse.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        String confPassword = request.getParameter("confPassword");
        String token = request.getParameter("token");

        if(token!=null && !token.equals("") ){
            Utilisateur utilisateur = utilisateurDao.getUtilisateurDemandeRecupMotDePasse(token);
            if(utilisateur==null){
                request.setAttribute("token", null);
                request.setAttribute("utilisateur", null);
                request.setAttribute("erreur", "Erreur: aucune demande de nouveau mot de passe correspond à ce token");
                this.getServletContext().getRequestDispatcher("/WEB-INF/nouveauMotDePasse.jsp").forward(request, response);
                return;
            }
            //verification remplissage formulaire
            if (password != "" && password != null && confPassword != "" && confPassword != null) {
                utilisateur.setPassword(password);
                if(password.equals(confPassword)){
                    utilisateurDao.recupMotDePasse(utilisateur, token);
                    this.getServletContext().getRequestDispatcher("/WEB-INF/authentification.jsp").forward(request, response);
                }
                else{
                    String erreur = "Les informations rentrées dans les champs ne correspondent pas";
                    request.setAttribute("token", token);
                    request.setAttribute("utilisateur", utilisateur);
                    request.setAttribute("confPassword", confPassword);
                    request.setAttribute("erreur", erreur);
                    this.getServletContext().getRequestDispatcher("/WEB-INF/nouveauMotDePasse.jsp").forward(request, response);
                }
            }
            else {
                String erreur = "Informations rentrées incorrectes";
                request.setAttribute("token", token);
                request.setAttribute("utilisateur", utilisateur);
                request.setAttribute("confPassword", confPassword);
                request.setAttribute("erreur", erreur);
                this.getServletContext().getRequestDispatcher("/WEB-INF/nouveauMotDePasse.jsp").forward(request, response);
            }
        }
        else{
            request.setAttribute("token", null);
            request.setAttribute("utilisateur", null);
            request.setAttribute("erreur", "Erreur: token mal formé");
            this.getServletContext().getRequestDispatcher("/WEB-INF/nouveauMotDePasse.jsp").forward(request, response);
        }
    }

}