package WG.by.fpmibsu.service;

import WG.by.fpmibsu.dao.ConnectionCreator;
import WG.by.fpmibsu.dao.DaoException;
import WG.by.fpmibsu.dao.UserDao;
import WG.by.fpmibsu.entity.User;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegistrationService {
    private static final Logger LOGGER = LogManager.getLogger(RegistrationService.class);
    public static User registration(String name, String password) throws SQLException, DaoException {
        LOGGER.trace("Entering Registration Service.");
        Connection connection = ConnectionCreator.createConnection();
        UserDao userDao = new UserDao(connection);
        connection = ConnectionCreator.createConnection();
        int count = userDao.returnCount(connection);
        User user = new User(count, name, password,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, false);
        connection = ConnectionCreator.createConnection();
        User regChecker = userDao.readByName(name, connection);
        if (regChecker == null) {
            connection = ConnectionCreator.createConnection();
            userDao.create(user, connection);
            return user;
        } else return null;
    }
}
