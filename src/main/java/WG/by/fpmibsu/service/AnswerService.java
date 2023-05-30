package WG.by.fpmibsu.service;

import WG.by.fpmibsu.dao.DaoException;
import WG.by.fpmibsu.dao.UserDao;
import WG.by.fpmibsu.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnswerService {
    private static final Logger LOGGER = LogManager.getLogger(AnswerService.class);
    public static void post(HttpServletRequest request, int index) throws SQLException, DaoException {
        LOGGER.trace("Redirecting from quiz");
        UserDao userDao = new UserDao();
        User user = userDao.read(index);
        request.setAttribute("username", user.getNickname());
        request.setAttribute("res1", user.getCompareFactsPercent());
        request.setAttribute("res2", user.getFlagsPercent());
        request.setAttribute("res3", user.getFactQuizPercent());
        request.setAttribute("res4", user.getMapPercent());
    }
}
