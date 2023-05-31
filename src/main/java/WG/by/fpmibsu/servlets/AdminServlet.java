package WG.by.fpmibsu.servlets;

import WG.by.fpmibsu.dao.DaoException;
import WG.by.fpmibsu.entity.Country;
import WG.by.fpmibsu.entity.User;
import WG.by.fpmibsu.service.AdminService;
import WG.by.fpmibsu.service.RegistrationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@WebServlet("/adm")
public class AdminServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(RegistrationServlet.class);
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.trace("Entering Admin Servlet.");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String area = request.getParameter("area");
        String population = request.getParameter("population");
        String continent = request.getParameter("continent");
        String fact = request.getParameter("fact");
        String flag = request.getParameter("flag");
        String territory = request.getParameter("territory");

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").getDeclaredConstructor().newInstance();
            Country country = AdminService.registration(name, Integer.parseInt(area), Integer.parseInt(population), continent, fact, flag, territory);
            if (country != null){
                response.setContentType("text/html");
                response.setCharacterEncoding("UTF-8");
                response.sendRedirect("login.html");
            } else{
                out.println("You cant sign up with such country or Password");
                Thread.sleep(1000);
                getServletContext().getRequestDispatcher("/registration.html").forward(request,response);
            }
            out.close();
        } catch (SQLException | DaoException | ClassNotFoundException | InvocationTargetException |
                 InstantiationException | IllegalAccessException | NoSuchMethodException | InterruptedException e) {
            LOGGER.error("Failed to registering.");
            throw new RuntimeException(e);
        }
        LOGGER.trace("Exiting Register Servlet.");
    }
}
