package site;

import dao.ActiviteDao;
import dao.DaoFactory;
import dao.LieuDao;
import utils.VerifInt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "activite", value = "/activite")
public class Activite extends HttpServlet {
    private ActiviteDao activiteDao;

    @Override
    public void init() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.activiteDao = daoFactory.getActiviteDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("utilisateur")==null){
            response.sendRedirect(request.getContextPath()+"/authentification");
        }else{
            if(request.getParameter("id")!=null && !request.getParameter("id").equals("") && VerifInt.isNumber(request.getParameter("id").toString())){
                int id =  Integer.parseInt(request.getParameter("id"));
                beans.Activite activite = activiteDao.getActivite(id);
                if(activite==null){
                    response.sendRedirect(request.getHeader("Referer"));
                    return;
                }
                request.setAttribute("activite", activite);
                this.getServletContext().getRequestDispatcher("/WEB-INF/activite.jsp").forward(request, response);
            }
            else{
                response.sendRedirect(request.getHeader("Referer"));
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
