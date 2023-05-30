package WG.by.fpmibsu.dao;

import WG.by.fpmibsu.entity.ModePrototype;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface ModeDao <ID, T extends ModePrototype>{
    boolean create(T t) throws DaoException, DaoException;
    T read(ID id) throws DaoException, DaoException;
    T update(T t) throws DaoException, DaoException;
    boolean delete(ID id) throws DaoException, DaoException;
    List<T> readAll() throws DaoException, DaoException;

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

