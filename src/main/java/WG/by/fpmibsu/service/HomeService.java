package WG.by.fpmibsu.service;

import WG.by.fpmibsu.dao.ConnectionCreator;
import WG.by.fpmibsu.dao.DaoException;
import WG.by.fpmibsu.dao.UserDao;
import WG.by.fpmibsu.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HomeService {
    private static final Logger LOGGER = LogManager.getLogger(HomeService.class);
    public static void homePageConfig(HttpServletRequest req, int index) throws SQLException, DaoException {
        LOGGER.trace("Entering Home Service.");
        Connection connection = ConnectionCreator.createConnection();
        UserDao userDao = new UserDao(connection);
        User user = userDao.read(index,connection);
        req.setAttribute("username", user.getNickname());
        req.setAttribute("res1", user.getCompareFactsPercent());
        req.setAttribute("res2", user.getFlagsPercent());
        req.setAttribute("res3", user.getFactQuizPercent());
        req.setAttribute("res4", user.getMapPercent());
    }
}
