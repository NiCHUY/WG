package WG.by.fpmibsu.servlets;

import WG.by.fpmibsu.dao.DaoException;
import WG.by.fpmibsu.entity.User;
import WG.by.fpmibsu.service.RegistrationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(RegistrationServlet.class);
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.trace("Entering Register Servlet.");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").getDeclaredConstructor().newInstance();
            User user = RegistrationService.registration(name, password);
            if (user != null){
                response.setContentType("text/html");
                response.setCharacterEncoding("UTF-8");
                response.sendRedirect("login.html");
            } else{
                out.println("You cant sign up with such Username or Password");
                Thread.sleep(1000);
                getServletContext().getRequestDispatcher("/registration.html").forward(request,response);
            }
            out.close();
        } catch (SQLException | DaoException | ClassNotFoundException | InvocationTargetException |
                 InstantiationException | IllegalAccessException | NoSuchMethodException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        LOGGER.trace("Exiting Register Servlet.");
    }
}