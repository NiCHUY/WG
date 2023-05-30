package WG.by.fpmibsu.dao;

import WG.by.fpmibsu.entity.User;
import WG.by.fpmibsu.entity.VisitorPrototype;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface VisitorDao<ID, T extends VisitorPrototype> {
    boolean create(T t, Connection connection) throws DaoException, DaoException;

    T read(ID id, Connection connection) throws DaoException, DaoException;

    User update(Integer id, User user, Connection connection) throws DaoException, DaoException;

    boolean delete(ID id, Connection connection) throws DaoException, DaoException;

    List<T> readAll(Connection connection) throws DaoException, DaoException;

    default void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            // log
        }
    }

    default void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close(); // or connection return code to the pool
            }
        } catch (SQLException e) {
            // log
        }
    }
}


