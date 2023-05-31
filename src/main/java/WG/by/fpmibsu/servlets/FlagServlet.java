package WG.by.fpmibsu.servlets;

import WG.by.fpmibsu.dao.DaoException;
import WG.by.fpmibsu.service.FlagService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/flag")
public class FlagServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(FlagServlet.class);
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            LOGGER.trace("Entering Flag Servlet.");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").getDeclaredConstructor().newInstance();
            if (FlagService.answer(request)) {
                getServletContext().getRequestDispatcher("/true.jsp").forward(request,response);
            } else getServletContext().getRequestDispatcher("/false.jsp").forward(request,response);
        }
        catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
               ClassNotFoundException | SQLException | DaoException e) {
            LOGGER.error("Failed to execute Flag Servlet.");
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            LOGGER.trace("Generating Flag Servlet.");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").getDeclaredConstructor().newInstance();
            FlagService.init(req);
            getServletContext().getRequestDispatcher("/flag.jsp").forward(req,resp);

        }
        catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
               ClassNotFoundException | SQLException | DaoException e) {
            throw new RuntimeException(e);
        }
    }
}