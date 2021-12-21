package site;

import beans.Utilisateur;
import dao.DaoFactory;
import dao.LieuDao;
import dao.UtilisateurDao;
import utils.VerifInt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "lieu", value = "/lieu")
public class Lieu extends HttpServlet {
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
            request.setAttribute("lieu", lieu);
            this.getServletContext().getRequestDispatcher("/WEB-INF/lieu.jsp").forward(request, response);
        }
        else{
            response.sendRedirect(request.getHeader("Referer"));
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
