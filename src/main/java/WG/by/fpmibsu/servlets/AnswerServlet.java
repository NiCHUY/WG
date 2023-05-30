package WG.by.fpmibsu.servlets;

import WG.by.fpmibsu.dao.DaoException;
import WG.by.fpmibsu.service.AnswerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/home1")
public class AnswerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            int index = (int) session.getAttribute("ID");
            AnswerService.post(request, response, index);
            getServletContext().getRequestDispatcher("/home.jsp").forward(request,response);
        } catch (SQLException | DaoException e) {
            throw new RuntimeException(e);
        }
    }
}
