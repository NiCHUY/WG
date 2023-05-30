package WG.by.fpmibsu.service;

import WG.Main;
import WG.by.fpmibsu.dao.ConnectionCreator;
import WG.by.fpmibsu.dao.DaoException;
import WG.by.fpmibsu.dao.UserDao;
import WG.by.fpmibsu.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class LoginService {
    public static User login(String name, String password) throws DaoException, SQLException {
        Connection connection = ConnectionCreator.createConnection();
        UserDao userDao = new UserDao(connection);
        connection = ConnectionCreator.createConnection();
        User userLog = userDao.readByName(name, connection);
        if (userLog != null) {
            if (Objects.equals(gPass(userLog.getPassword()), gPass(password))){
                return userLog;
            } else return null;
        } else return null;
    }
    public static void loginRedirecting(HttpServletRequest request, User user) {
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
