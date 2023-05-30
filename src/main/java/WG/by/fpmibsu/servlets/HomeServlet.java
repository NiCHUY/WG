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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(HomeServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            LOGGER.trace("Entering Home Servlet.");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").getDeclaredConstructor().newInstance();
            HttpSession session = req.getSession();
            int index = (int) session.getAttribute("ID");
            HomeService.homePageConfig(req, index);
            getServletContext().getRequestDispatcher("/home.jsp").forward(req,resp);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                 ClassNotFoundException | SQLException | DaoException e) {
            LOGGER.error("Failed to execute Home Servlet.");
            throw new RuntimeException(e);
        }
    }
}
