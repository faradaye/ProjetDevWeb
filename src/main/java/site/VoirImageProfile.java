package site;

import beans.Utilisateur;
import dao.DaoFactory;
import dao.UtilisateurDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

@WebServlet(name = "voirImageProfile", value = "/voirImageProfile")
public class VoirImageProfile extends HttpServlet {
    UtilisateurDao utilisateurDao;

    @Override
    public void init() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.utilisateurDao = daoFactory.getUtilisateurDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String session = request.getParameter("session");
        Blob blob = null;
        if(request.getAttribute("imageProfile" + id)!=null && (session==null || !session.equals("1"))) blob = (Blob) request.getAttribute("imageProfile" + id);
        else if(request.getSession().getAttribute("imageProfile" + id)!=null)blob = (Blob) request.getSession().getAttribute("imageProfile" + id);
        else {
            Utilisateur utilisateur = utilisateurDao.getUtilisateur(Integer.parseInt(id));
            blob = utilisateur.getImageProfile();
            request.getSession().setAttribute("imageProfile" + id, utilisateur.getImageProfile());
        }

        int blobLength = 0;
        byte[] content = new byte[0];
        try {
            blobLength = (int) blob.length();
            content = blob.getBytes(1, blobLength);
            response.setContentType(getServletContext().getMimeType("imageProfile" + id));
            response.setContentLength(content.length);
            response.getOutputStream().write(content);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }catch (NullPointerException ignored){

        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}