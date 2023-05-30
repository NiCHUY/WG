package WG.by.fpmibsu.dao;
import WG.by.fpmibsu.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends ConnectionInit implements VisitorDao<Integer, User> {
    final private String create = "INSERT INTO UserWG (ID, Nickname, Password, FlagPassed, MapPassed," +
            " FactQuizPassed, CompareFactsPassed, FlagFailed, MapFailed, FactQuizFailed," +
            " CompareFactsFailed, UserMark, IsAdmin) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    final private String read = "SELECT * FROM UserWG WHERE ID = ?";
    final private String update = "UPDATE UserWG SET Nickname = ?, Password = ?, FlagPassed = ?, MapPassed = ?," +
            " FactQuizPassed = ?, CompareFactsPassed = ?, FlagFailed = ?, MapFailed = ?," +
            " FactQuizFailed = ?, CompareFactsFailed = ?, UserMark = ?, IsAdmin = ? WHERE ID = ?";
    final private String readByName = "SELECT * FROM UserWG WHERE TRIM(Nickname) = ?";
    final private String count = "SELECT COUNT(*) AS quantity FROM UserWG;";
    final private String delete = "DELETE FROM UserWG WHERE ID = ?";
    final private String readAll = "SELECT * FROM UserWG";
    Connection connection;

    public UserDao() throws DaoException {
        super();
    }

    @Override
    public boolean create(User user) throws DaoException {
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(create);
            statement.setInt(1, user.getID());
            statement.setString(2, user.getNickname());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getFlagPassed());
            statement.setInt(5, user.getMapPassed());
            statement.setInt(6, user.getFactQuizPassed());
            statement.setInt(7, user.getCompareFactsPassed());
            statement.setInt(8, user.getFlagFailed());
            statement.setInt(9, user.getMapFailed());
            statement.setInt(10, user.getFactQuizFailed());
            statement.setInt(11, user.getCompareFactsFailed());
            statement.setDouble(12, user.getUserMark());
            statement.setBoolean(13, user.isAdmin());
            int rowsInserted = statement.executeUpdate();
            statement.close();
            return rowsInserted > 0;
        } catch (SQLException e) {
            throw new DaoException("Error creating user", e);
        }
    }

    @Override
    public User read(Integer id) throws DaoException {
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(read);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = createFromResultSet(resultSet);
            }
            resultSet.close();
            statement.close();
            if (user == null) {
                throw new DaoException("User not found with ID " + id);
            }
            return user;
        } catch (SQLException e) {
            throw new DaoException("Error reading user", e);
        }
    }

    public User readByName(String nickname) throws DaoException {
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(readByName);
            statement.setString(1, nickname);
            ResultSet resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = createFromResultSet(resultSet);
            }
            resultSet.close();
            statement.close();
            if (user == null) {
                return null;
            }
            return user;
        } catch (SQLException e) {
            throw new DaoException("Error reading user", e);
        }
    }

    @Override
    public User update(Integer id, User user) throws DaoException {
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(update);
            statement.setInt(1, user.getID());
            statement.setString(2, user.getNickname());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getFlagPassed());
            statement.setInt(5, user.getMapPassed());
            statement.setInt(6, user.getFactQuizPassed());
            statement.setInt(7, user.getCompareFactsPassed());
            statement.setInt(8, user.getFlagFailed());
            statement.setInt(9, user.getMapFailed());
            statement.setInt(10, user.getFactQuizFailed());
            statement.setInt(11, user.getCompareFactsFailed());
            statement.setDouble(12, user.getUserMark());
            statement.setBoolean(13, user.isAdmin());
            int rowsUpdated = statement.executeUpdate();
            statement.close();
            if (rowsUpdated == 0) {
                throw new DaoException("User not found with ID " + id);
            }
            return user;
        } catch (SQLException e) {
            throw new DaoException("Error updating user", e);
        }
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(delete);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            statement.close();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw new DaoException("Error deleting user", e);
        }
    }

    @Override
    public List<User> readAll() throws DaoException {
        try {
            connection = connectionPool.getConnection();
            List<User> users = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(readAll);
            while (resultSet.next()) {
                User user = createFromResultSet(resultSet);
                users.add(user);
            }
            resultSet.close();
            statement.close();
            return users;
        } catch (SQLException e) {
            throw new DaoException("Error reading all users", e);
        }
    }

    public int returnCount() throws DaoException {
        try {
            connection = connectionPool.getConnection();
            String query = count;
            int quantity = -1;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                String temp = resultSet.getString("quantity");
                quantity = Integer.parseInt(temp);

            }
            statement.close();
            connection.close();
            return quantity;
        } catch (SQLException e) {
            throw new DaoException("Error returning count", e);
        }
    }

    private User createFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("ID");
        String nickname = resultSet.getString("Nickname");
        String password = resultSet.getString("Password");
        int FlagPassed = resultSet.getInt("FlagPassed");
        int mapPassed = resultSet.getInt("MapPassed");
        int factQuizPassed = resultSet.getInt("FactQuizPassed");
        int compareFactsPassed = resultSet.getInt("CompareFactsPassed");
        int FlagFailed = resultSet.getInt("FlagFailed");
        int mapFailed = resultSet.getInt("MapFailed");
        int factQuizFailed = resultSet.getInt("FactQuizFailed");
        int compareFactsFailed = resultSet.getInt("CompareFactsFailed");
        double userMark = resultSet.getDouble("UserMark");
        boolean isAdmin = resultSet.getBoolean("IsAdmin");
        return new User(id, nickname, password, FlagPassed, mapPassed, factQuizPassed, compareFactsPassed, FlagFailed, mapFailed, factQuizFailed, compareFactsFailed, userMark, isAdmin);
    }
}
