package WG.by.fpmibsu.service;

import WG.by.fpmibsu.dao.ConnectionCreator;
import WG.by.fpmibsu.dao.DaoException;
import WG.by.fpmibsu.dao.UserDao;
import WG.by.fpmibsu.entity.User;

import java.sql.Connection;
import java.sql.SQLException;

public class RegistrationService {
    public static User registration(String name, String password) throws SQLException, DaoException {
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
