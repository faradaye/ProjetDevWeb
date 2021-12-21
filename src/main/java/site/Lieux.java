package site;

import dao.DaoFactory;
import dao.LieuDao;
import utils.VerifInt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "lieux", value = "/lieux")
public class Lieux extends HttpServlet {
    LieuDao lieuDao;

    @Override
    public void init() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.lieuDao = daoFactory.getLieuDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("lieux", lieuDao.getAll());
        this.getServletContext().getRequestDispatcher("/WEB-INF/lieux.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
