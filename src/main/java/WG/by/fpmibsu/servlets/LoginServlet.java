package WG.by.fpmibsu.servlets;

import WG.by.fpmibsu.dao.DaoException;
import WG.by.fpmibsu.entity.User;
import WG.by.fpmibsu.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/home.html")
public class LoginServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(LoginServlet.class);
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.trace("Entering Login servlet.");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            User user  = LoginService.login(name, password);
            if (user != null) {
                response.setContentType("text/html");
                response.setCharacterEncoding("UTF-8");
                LoginService.loginRedirecting(request, user);
                if (user.isAdmin()){
                   response.sendRedirect("adminJob.html");
                }else getServletContext().getRequestDispatcher("/home.jsp").forward(request,response);
                response.sendRedirect("home.jsp");
            } else {
                out.println("You cant sign up with such Username or Password");
                Thread.sleep(1000);
                getServletContext().getRequestDispatcher("/login.html").forward(request,response);
            }
        } catch (SQLException | DaoException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        out.close();
        LOGGER.trace("Exiting Login servlet.");
    }
}