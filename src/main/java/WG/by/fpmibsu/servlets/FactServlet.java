package WG.by.fpmibsu.servlets;

import WG.by.fpmibsu.dao.DaoException;
import WG.by.fpmibsu.service.FactService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@WebServlet("/fact")
public class FactServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(FlagServlet.class);
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            LOGGER.trace("Entering Fact Servlet.");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").getDeclaredConstructor().newInstance();
            if (FactService.answer(request)) {
                getServletContext().getRequestDispatcher("/true.jsp").forward(request,response);
            } else getServletContext().getRequestDispatcher("/false.jsp").forward(request,response);
        }
        catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
               ClassNotFoundException | SQLException | DaoException e) {
            LOGGER.error("Failed to execute Fact Servlet.");
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            LOGGER.trace("Generating Fact Servlet.");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").getDeclaredConstructor().newInstance();
            FactService.init(req);
            getServletContext().getRequestDispatcher("/fact.jsp").forward(req,resp);
        }
        catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
               ClassNotFoundException | SQLException | DaoException e) {
            throw new RuntimeException(e);
        }
    }
}