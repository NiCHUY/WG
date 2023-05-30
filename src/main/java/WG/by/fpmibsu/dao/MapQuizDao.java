package WG.by.fpmibsu.dao;
import WG.by.fpmibsu.entity.Country;
import WG.by.fpmibsu.entity.FlagQuiz;
import WG.by.fpmibsu.entity.MapQuiz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MapQuizDao implements ModeDao<Integer, MapQuiz> {
    final private String create = "INSERT INTO MapQuiz (MapQuiz_ID, Country_ID) VALUES (?, ?)";
    final private String read = "SELECT * FROM MapQuiz WHERE MapQuiz_ID = ?";
    final private String update = "UPDATE MapQuiz SET Country_ID = ? WHERE MapQuiz_ID = ?";
    final private String delete = "DELETE FROM MapQuiz WHERE MapQuiz_ID = ?";
    final private String readAll = "SELECT * FROM MapQuiz";
    final private String count = "SELECT COUNT(*) AS quantity FROM MapQuiz;";
    Connection connection;

    public MapQuizDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(MapQuiz mapQuiz, Connection connection) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(create);
            statement.setInt(1, mapQuiz.getMapQuizID());
            statement.setString(2, String.valueOf(mapQuiz.getCountry().getID()));
            int rowsInserted = statement.executeUpdate();
            statement.close();
            return rowsInserted > 0;
        } catch (SQLException e) {
            throw new DaoException("Error creating map quiz", e);
        }
    }

    @Override
    public MapQuiz read(Integer id, Connection connection) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(read)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int flagQuizID = resultSet.getInt("MapQuiz_ID");
                int answerCountryID = resultSet.getInt("Country_ID");
                connection = ConnectionCreator.createConnection();
                Country answerCountry = new CountryDao(connection).read(answerCountryID, connection);
                return new MapQuiz(flagQuizID, answerCountry);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DaoException("Error reading FlagQuiz", e);
        }
    }

    @Override
    public MapQuiz update(MapQuiz mapQuiz, Connection connection) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(update);
            statement.setString(1, String.valueOf(mapQuiz.getCountry().getCountyID()));
            statement.setInt(2, Integer.parseInt(String.valueOf(mapQuiz.getMapQuizID())));
            int rowsUpdated = statement.executeUpdate();
            statement.close();
            if (rowsUpdated == 0) {
                throw new DaoException("Map quiz not found with ID ");
            }
            return mapQuiz;
        } catch (SQLException e) {
            throw new DaoException("Error updating map quiz", e);
        }
    }
    public int returnCount(Connection connection) throws DaoException {
        try {
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

    @Override
    public boolean delete(Integer id, Connection connection) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(delete);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            statement.close();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw new DaoException("Error deleting map quiz", e);
        }
    }

    @Override
    public List<MapQuiz> readAll(Connection connection) throws DaoException {
        try {
            List<MapQuiz> mapQuizzes = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(readAll);
            while (resultSet.next()) {
                MapQuiz mapQuiz = createFromResultSet(resultSet);
                mapQuizzes.add(mapQuiz);
            }
            resultSet.close();
            statement.close();
            return mapQuizzes;
        } catch (SQLException e) {
            throw new DaoException("Error reading all map quizzes", e);
        }
    }

    @Override
    public void close(Statement statement) {
        ModeDao.super.close(statement);
    }

    @Override
    public void close(Connection connection) {
        ModeDao.super.close(connection);
    }

    private MapQuiz createFromResultSet(ResultSet resultSet) throws SQLException {
        int mapQuizID = resultSet.getInt("MapQuiz_ID");
        int countryCode = resultSet.getInt("Country_ID");
        Connection connection1 = ConnectionCreator.createConnection();
        CountryDao countryDao = new CountryDao(connection1);
        Country country = countryDao.read(countryCode, connection1);
        return new MapQuiz(mapQuizID, country);
    }
}