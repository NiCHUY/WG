package WG.by.fpmibsu.dao;


import WG.by.fpmibsu.entity.Country;
import WG.by.fpmibsu.entity.FactQuiz;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FactQuizDao implements ModeDao<Integer, FactQuiz> {
    private final String create = "INSERT INTO FactQuiz (FactText, Answer, Variant1," +
            " Variant2, Variant3, Variant4) VALUES (?, ?, ?, ?, ?, ?)";
    private final String read = "SELECT * FROM FactQuiz WHERE FactQuiz_ID = ?";
    private final String update = "UPDATE FactQuiz SET FactText = ?, Answer = ?, Variant1 = ?," +
            " Variant2 = ?, Variant3 = ?, Variant4 = ? WHERE FactQuiz_ID  = ?";
    private final String delete = "DELETE FROM FactQuiz WHERE FactQuiz_ID  = ?";
    private final String readAll = "SELECT * FROM FactQuiz";

    Connection connection;

    public FactQuizDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(FactQuiz factQuiz, Connection connection) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(create)) {
            statement.setString(1, factQuiz.getFact());
            statement.setInt(2, factQuiz.getAnswer());
            statement.setInt(3, factQuiz.getFirstVariant().getID());
            statement.setInt(4, factQuiz.getSecondVariant().getID());
            statement.setInt(5, factQuiz.getThirdVariant().getID());
            statement.setInt(6, factQuiz.getFourthVariant().getID());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            throw new DaoException("Error creating FactQuiz", e);
        }
    }

    @Override
    public FactQuiz read(Integer id, Connection connection) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(read)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int factQuizID = resultSet.getInt("FactQuiz_ID ");
                String fact = resultSet.getString("FactText");
                int answer = resultSet.getInt("Answer");
                int variant1ID = resultSet.getInt("Variant1");
                int variant2ID = resultSet.getInt("Variant2");
                int variant3ID = resultSet.getInt("Variant3");
                int variant4ID = resultSet.getInt("Variant4");
                connection = ConnectionCreator.createConnection();
                Country variant1 = new CountryDao(connection).read(variant1ID, connection);
                connection = ConnectionCreator.createConnection();
                Country variant2 = new CountryDao(connection).read(variant2ID, connection);
                connection = ConnectionCreator.createConnection();
                Country variant3 = new CountryDao(connection).read(variant3ID, connection);
                connection = ConnectionCreator.createConnection();
                Country variant4 = new CountryDao(connection).read(variant4ID, connection);
                return new FactQuiz(factQuizID, fact, variant1, variant2, variant3, variant4, answer);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DaoException("Error reading FactQuiz", e);
        }
    }

    @Override
    public FactQuiz update(FactQuiz factQuiz, Connection connection) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(update)) {
            statement.setString(1, factQuiz.getFact());
            statement.setInt(2, factQuiz.getAnswer());
            connection = ConnectionCreator.createConnection();
            statement.setInt(3, factQuiz.getFirstVariant().getID());
            connection = ConnectionCreator.createConnection();
            statement.setInt(4, factQuiz.getSecondVariant().getID());
            connection = ConnectionCreator.createConnection();
            statement.setInt(5, factQuiz.getThirdVariant().getID());
            connection = ConnectionCreator.createConnection();
            statement.setInt(6, factQuiz.getFourthVariant().getID());
            statement.setInt(7, factQuiz.getFactQuizID());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                return factQuiz;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DaoException("Error updating FactQuiz", e);
        }
    }

    @Override
    public boolean delete(Integer id, Connection connection) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(delete)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw new DaoException("Error deleting FactQuiz", e);
        }
    }

    @Override
    public List<FactQuiz> readAll(Connection connection) throws DaoException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(readAll);
            List<FactQuiz> factQuizzes = new ArrayList<>();
            while (resultSet.next()) {
                int factQuizID = resultSet.getInt("FactQuiz_ID ");
                String fact = resultSet.getString("FactText");
                int answer = resultSet.getInt("Answer");
                int variant1ID = resultSet.getInt("Variant1");
                int variant2ID = resultSet.getInt("Variant2");
                int variant3ID = resultSet.getInt("Variant3");
                int variant4ID = resultSet.getInt("Variant4");
                connection = ConnectionCreator.createConnection();
                Country variant1 = new CountryDao(connection).read(variant1ID, connection);
                connection = ConnectionCreator.createConnection();
                Country variant2 = new CountryDao(connection).read(variant2ID, connection);
                connection = ConnectionCreator.createConnection();
                Country variant3 = new CountryDao(connection).read(variant3ID, connection);
                connection = ConnectionCreator.createConnection();
                Country variant4 = new CountryDao(connection).read(variant4ID, connection);
                factQuizzes.add(new FactQuiz(factQuizID, fact, variant1, variant2, variant3, variant4, answer));
            }
            return factQuizzes;
        } catch (SQLException e) {
            throw new DaoException("Error reading FactQuizzes", e);
        }
    }
}