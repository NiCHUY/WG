package WG.by.fpmibsu.service;

import WG.by.fpmibsu.dao.ConnectionCreator;
import WG.by.fpmibsu.dao.DaoException;
import WG.by.fpmibsu.dao.UserDao;
import WG.by.fpmibsu.entity.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

public class AnswerService {
    public static void post(HttpServletRequest request, HttpServletResponse response, int index) throws SQLException, DaoException {
        Connection connection = ConnectionCreator.createConnection();
        UserDao userDao = new UserDao(connection);
        User user = userDao.read(index, connection);
        request.setAttribute("username", user.getNickname());
        request.setAttribute("res1", user.getCompareFactsPercent());
        request.setAttribute("res2", user.getFlagsPercent());
        request.setAttribute("res3", user.getFactQuizPercent());
        request.setAttribute("res4", user.getMapPercent());
    }
}
