package WG.by.fpmibsu.servlets;

import WG.by.fpmibsu.dao.DaoException;
import WG.by.fpmibsu.service.HomeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").getDeclaredConstructor().newInstance();
            HttpSession session = req.getSession();
            int index = (int) session.getAttribute("ID");
            HomeService.homePageConfig(req, index);
            getServletContext().getRequestDispatcher("/home.jsp").forward(req,resp);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                 ClassNotFoundException | SQLException | DaoException e) {
            throw new RuntimeException(e);
        }
    }
}
