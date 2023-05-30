package WG.by.fpmibsu.service;

import WG.by.fpmibsu.dao.DaoException;
import WG.by.fpmibsu.dao.UserDao;
import WG.by.fpmibsu.entity.User;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegistrationService {
    private static final Logger LOGGER = LogManager.getLogger(RegistrationService.class);
    public static User registration(String name, String password) throws SQLException, DaoException {
        LOGGER.trace("Entering Registration Service.");
        UserDao userDao = new UserDao();
        int count = userDao.returnCount();
        User user = new User(count, name, password,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, false);
        User regChecker = userDao.readByName(name);
        if (regChecker == null) {
            userDao.create(user);
            return user;
        } else return null;
    }
}
