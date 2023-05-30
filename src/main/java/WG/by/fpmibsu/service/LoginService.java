package WG.by.fpmibsu.service;

import WG.by.fpmibsu.dao.DaoException;
import WG.by.fpmibsu.dao.UserDao;
import WG.by.fpmibsu.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginService {
    private static final Logger LOGGER = LogManager.getLogger(LoginService.class);
    public static User login(String name, String password) throws DaoException, SQLException {
        LOGGER.trace("Entering login method Service.");
        UserDao userDao = new UserDao();
        User userLog = userDao.readByName(name);
        if (userLog != null) {
            if (Objects.equals(gPass(userLog.getPassword()), gPass(password))){
                return userLog;
            } else return null;
        } else return null;
    }
    public static void loginRedirecting(HttpServletRequest request, User user) {
        LOGGER.trace("Entering login redirect method Service.");
        HttpSession session = request.getSession();
        session.setAttribute("ID", user.getID());
        request.setAttribute("username", user.getNickname());
        request.setAttribute("res1", user.getCompareFactsPercent());
        request.setAttribute("res2", user.getFlagsPercent());
        request.setAttribute("res3", user.getFactQuizPercent());
        request.setAttribute("res4", user.getMapPercent());
    }
    public static int gPass(String idStr){
        int i = Integer.parseInt(idStr.replace(" ", ""));
        return i;
    }
}
