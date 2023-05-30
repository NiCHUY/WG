package WG;

import WG.by.fpmibsu.dao.ConnectionCreator;
import WG.by.fpmibsu.dao.CountryDao;
import WG.by.fpmibsu.dao.DaoException;
import WG.by.fpmibsu.dao.UserDao;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, DaoException {
        Connection connection = ConnectionCreator.createConnection();
        UserDao countryDao = new UserDao(connection);
        System.out.println(countryDao.read(0, connection).getNickname());
    }
}