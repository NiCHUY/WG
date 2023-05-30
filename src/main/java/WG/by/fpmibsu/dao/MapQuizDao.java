package WG.by.fpmibsu.dao;
import WG.by.fpmibsu.entity.Country;
import WG.by.fpmibsu.entity.MapQuiz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MapQuizDao implements ModeDao<String, MapQuiz> {
    final private String create = "INSERT INTO MapQuiz (MapQuiz_ID, Country_ID, ClueFact," +
            " ClueArea, CluePopulation, ClueContinent) VALUES (?, ?, ?, ?, ?, ?)";
    final private String read = "SELECT * FROM MapQuiz WHERE MapQuiz_ID = ?";
    final private String update = "UPDATE MapQuiz SET Country_ID = ?, ClueFact = ?," +
            " ClueArea = ?, CluePopulation = ?, ClueContinent = ? WHERE MapQuiz_ID = ?";
    final private String delete = "DELETE FROM MapQuiz WHERE MapQuiz_ID = ?";
    final private String readAll = "SELECT * FROM MapQuiz";
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
            statement.setString(3, mapQuiz.getClueFact());
            statement.setInt(4, mapQuiz.getClueArea());
            statement.setInt(5, mapQuiz.getCluePopulation());
            statement.setString(6, mapQuiz.getClueContinent());
            int rowsInserted = statement.executeUpdate();
            statement.close();
            return rowsInserted > 0;
        } catch (SQLException e) {
            throw new DaoException("Error creating map quiz", e);
        }
    }

    @Override
    public MapQuiz read(String id, Connection connection) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(read);
            statement.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = statement.executeQuery();
            MapQuiz mapQuiz = null;
            if (resultSet.next()) {
                mapQuiz = createFromResultSet(resultSet);
            }
            resultSet.close();
            statement.close();
            if (mapQuiz == null) {
                throw new DaoException("Map quiz not found with ID " + id);
            }
            return mapQuiz;
        } catch (SQLException e) {
            throw new DaoException("Error reading map quiz", e);
        }
    }

    @Override
    public MapQuiz update(MapQuiz mapQuiz, Connection connection) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(update);
            statement.setString(1, String.valueOf(mapQuiz.getCountry().getCountyID()));
            statement.setString(2, mapQuiz.getClueFact());
            statement.setInt(3, mapQuiz.getClueArea());
            statement.setInt(4, mapQuiz.getCluePopulation());
            statement.setString(5, mapQuiz.getClueContinent());
            statement.setInt(6, Integer.parseInt(String.valueOf(mapQuiz.getMapQuizID())));
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

    @Override
    public boolean delete(String id, Connection connection) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(delete);
            statement.setInt(1, Integer.parseInt(id));
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
        String countryCode = resultSet.getString("Country_ID");
        Country country = new CountryDao(connection).read(Integer.parseInt(countryCode), connection);
        String clueFact = resultSet.getString("ClueFact");
        int clueArea = resultSet.getInt("ClueArea");
        int cluePopulation = resultSet.getInt("CluePopulation");
        String clueContinent = resultSet.getString("ClueContinent");
        return new MapQuiz(mapQuizID, country, "", clueFact, clueArea, cluePopulation, clueContinent);
    }
}